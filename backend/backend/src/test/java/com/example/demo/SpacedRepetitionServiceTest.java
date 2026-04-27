package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class SpacedRepetitionServiceTest {

    private SpacedRepetitionService service;
    private UserProblem userProblem;

    @BeforeEach
    public void setup() {
        service = new SpacedRepetitionService();
        userProblem = new UserProblem();
        userProblem.setRevisionCount(0);
        userProblem.setEaseFactor(2.5f);
        userProblem.setIntervalDays(0);
    }

    @Test
    public void testFirstCorrectReview() {
        // Quality 4: Correct response after a hesitation
        UserProblem updated = service.updateRevisionSchedule(userProblem, 4);

        assertEquals(1, updated.getIntervalDays());
        assertEquals(1, updated.getRevisionCount());
        assertEquals(LocalDate.now().plusDays(1), updated.getNextRevisionDate());
        assertEquals(ProblemStatus.LEARNING, updated.getStatus());
    }

    @Test
    public void testSecondCorrectReview() {
        userProblem.setRevisionCount(1);
        userProblem.setIntervalDays(1);

        // Quality 4
        UserProblem updated = service.updateRevisionSchedule(userProblem, 4);

        assertEquals(6, updated.getIntervalDays());
        assertEquals(2, updated.getRevisionCount());
        assertEquals(LocalDate.now().plusDays(6), updated.getNextRevisionDate());
    }

    @Test
    public void testMastery() {
        userProblem.setRevisionCount(4);
        userProblem.setIntervalDays(15);
        userProblem.setEaseFactor(2.5f);

        // Quality 5: Perfect response
        UserProblem updated = service.updateRevisionSchedule(userProblem, 5);

        assertTrue(updated.getIntervalDays() >= 37); // 15 * 2.5 = 37.5
        assertEquals(5, updated.getRevisionCount());
        assertEquals(ProblemStatus.MASTERED, updated.getStatus());
    }

    @Test
    public void testIncorrectReview() {
        userProblem.setRevisionCount(3);
        userProblem.setIntervalDays(10);

        // Quality 1: Incorrect response
        UserProblem updated = service.updateRevisionSchedule(userProblem, 1);

        assertEquals(1, updated.getIntervalDays());
        assertEquals(0, updated.getRevisionCount());
        assertEquals(ProblemStatus.TODO, updated.getStatus());
    }
}
