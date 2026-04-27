package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class ProblemService {

    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    private UserProblemRepository userProblemRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Problem> getAllProblems() {
        return problemRepository.findAll();
    }

    public Problem addProblem(Problem problem, Long userId) {
        Problem savedProblem = problemRepository.save(problem);

        if (userId != null) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            UserProblem userProblem = new UserProblem();
            userProblem.setUser(user);
            userProblem.setProblem(savedProblem);
            userProblem.setStatus(ProblemStatus.TODO);
            userProblem.setNextRevisionDate(LocalDate.now()); // Due immediately

            userProblemRepository.save(userProblem);
        }

        return savedProblem;
    }

    public List<UserProblem> getUserProblems(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userProblemRepository.findByUser(user);
    }

    public List<Problem> getProblemsByTopic(String topic) {
        return problemRepository.findByTopic(topic);
    }
}
