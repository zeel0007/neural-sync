package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/problems")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @GetMapping
    public List<Problem> getAllProblems() {
        return problemService.getAllProblems();
    }

    @GetMapping("/user/{userId}")
    public List<UserProblem> getUserProblems(@PathVariable Long userId) {
        return problemService.getUserProblems(userId);
    }

    @PostMapping
    public Problem addProblem(@RequestBody Problem problem, @RequestParam(required = false) Long userId) {
        return problemService.addProblem(problem, userId);
    }

    @GetMapping("/topic/{topic}")
    public List<Problem> getByTopic(@PathVariable String topic) {
        return problemService.getProblemsByTopic(topic);
    }
}
