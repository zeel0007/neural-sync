package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class RevisionService {

    @Autowired
    private UserProblemRepository userProblemRepository;

    @Autowired
    private RevisionLogRepository revisionLogRepository;

    @Autowired
    private SpacedRepetitionService spacedRepetitionService;

    @Autowired
    private UserStatsService userStatsService;

    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    private UserRepository userRepository;

    public List<UserProblem> getTodayTasks(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userProblemRepository.findByUserAndNextRevisionDateLessThanEqual(user, LocalDate.now());
    }

    @Transactional
    public UserProblem solveProblem(Long userId, Long problemId, int quality) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Problem problem = problemRepository.findById(problemId)
                .orElseThrow(() -> new RuntimeException("Problem not found"));

        UserProblem userProblem = userProblemRepository.findByUserAndProblem(user, problem)
                .orElseGet(() -> {
                    UserProblem up = new UserProblem();
                    up.setUser(user);
                    up.setProblem(problem);
                    return up;
                });

        // Update SR fields
        spacedRepetitionService.updateRevisionSchedule(userProblem, quality);
        userProblemRepository.save(userProblem);

        // Update Streaks
        userStatsService.updateStreak(userId);

        // Log the revision
        RevisionLog log = new RevisionLog();
        log.setUserProblem(userProblem);
        log.setQualityScore(quality);
        revisionLogRepository.save(log);

        return userProblem;
    }
}
