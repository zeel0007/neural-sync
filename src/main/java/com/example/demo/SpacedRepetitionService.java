package com.example.demo;

import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class SpacedRepetitionService {

    /**
     * Updates the spaced repetition fields of a UserProblem based on the SM-2
     * algorithm.
     * 
     * @param userProblem The problem being reviewed
     * @param quality     A score from 0 to 5 reflecting how well the user
     *                    remembered the problem
     *                    5: perfect response
     *                    4: correct response after a hesitation
     *                    3: correct response recalled with serious difficulty
     *                    2: incorrect response; where the correct one seemed easy
     *                    to recall
     *                    1: incorrect response; the correct one remembered
     *                    0: complete blackout.
     * @return The updated UserProblem
     */
    public UserProblem updateRevisionSchedule(UserProblem userProblem, int quality) {
        if (quality < 0 || quality > 5) {
            throw new IllegalArgumentException("Quality must be between 0 and 5");
        }

        float easeFactor = userProblem.getEaseFactor();
        int interval = userProblem.getIntervalDays();
        int count = userProblem.getRevisionCount();

        if (quality >= 3) {
            // Correct response
            if (count == 0) {
                interval = 1;
            } else if (count == 1) {
                interval = 6;
            } else {
                interval = Math.round(interval * easeFactor);
            }
            count++;
        } else {
            // Incorrect response
            count = 0;
            interval = 1;
        }

        // Update Ease Factor (EF)
        // EF':=EF+(0.1-(5-q)*(0.08+(5-q)*0.02))
        easeFactor = easeFactor + (0.1f - (5 - quality) * (0.08f + (5 - quality) * 0.02f));
        if (easeFactor < 1.3f) {
            easeFactor = 1.3f;
        }

        userProblem.setEaseFactor(easeFactor);
        userProblem.setIntervalDays(interval);
        userProblem.setRevisionCount(count);
        userProblem.setLastRevisedDate(LocalDate.now());
        userProblem.setNextRevisionDate(LocalDate.now().plusDays(interval));

        // Update status based on mastery
        if (quality == 5 && count >= 5) {
            userProblem.setStatus(ProblemStatus.MASTERED);
        } else if (count > 0) {
            userProblem.setStatus(ProblemStatus.LEARNING);
        }

        userProblem.setMasteryLevel(quality);

        return userProblem;
    }
}
