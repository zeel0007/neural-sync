package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RevisionLogRepository extends JpaRepository<RevisionLog, Long> {
    List<RevisionLog> findByUserProblem(UserProblem userProblem);
}
