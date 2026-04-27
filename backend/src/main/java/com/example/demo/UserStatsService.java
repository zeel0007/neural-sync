package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

@Service
public class UserStatsService {

    @Autowired
    private UserStatsRepository userStatsRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public UserStats getUserStats(Long userId) {
        return userStatsRepository.findById(userId)
                .orElseGet(() -> createInitialStats(userId));
    }

    @Transactional
    public UserStats createInitialStats(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserStats stats = new UserStats();
        stats.setUser(user);
        stats.setUserId(userId);
        stats.setCurrentStreak(0);
        stats.setMaxStreak(0);
        return userStatsRepository.save(stats);
    }

    @Transactional
    public void updateStreak(Long userId) {
        UserStats stats = getUserStats(userId);
        LocalDate today = LocalDate.now();
        LocalDate lastStudy = stats.getLastStudyDate();

        if (lastStudy == null) {
            stats.setCurrentStreak(1);
        } else if (lastStudy.equals(today.minusDays(1))) {
            stats.setCurrentStreak(stats.getCurrentStreak() + 1);
        } else if (!lastStudy.equals(today)) {
            stats.setCurrentStreak(1);
        }

        if (stats.getCurrentStreak() > stats.getMaxStreak()) {
            stats.setMaxStreak(stats.getCurrentStreak());
        }

        stats.setLastStudyDate(today);
        userStatsRepository.save(stats);
    }
}
