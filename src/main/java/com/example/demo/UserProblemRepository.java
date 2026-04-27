package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserProblemRepository extends JpaRepository<UserProblem, Long> {
    List<UserProblem> findByUser(User user);

    Optional<UserProblem> findByUserAndProblem(User user, Problem problem);

    List<UserProblem> findByUserAndNextRevisionDateLessThanEqual(User user, LocalDate date);
}
