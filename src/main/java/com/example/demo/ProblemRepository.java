package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProblemRepository extends JpaRepository<Problem, Long> {
    List<Problem> findByTopic(String topic);

    List<Problem> findByDifficulty(Difficulty difficulty);
}
