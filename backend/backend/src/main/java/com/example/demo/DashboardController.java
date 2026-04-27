package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private UserStatsService userStatsService;

    @Autowired
    private RevisionService revisionService;

    @GetMapping("/stats/{userId}")
    public UserStats getStats(@PathVariable Long userId) {
        return userStatsService.getUserStats(userId);
    }

    @GetMapping("/tasks/{userId}")
    public List<UserProblem> getTodayTasks(@PathVariable Long userId) {
        return revisionService.getTodayTasks(userId);
    }

    @PostMapping("/solve")
    public UserProblem solveProblem(
            @RequestParam Long userId,
            @RequestParam Long problemId,
            @RequestParam int quality) {
        return revisionService.solveProblem(userId, problemId, quality);
    }
}
