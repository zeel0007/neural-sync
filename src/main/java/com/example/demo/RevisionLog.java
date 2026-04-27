package com.example.demo;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "revision_logs")
public class RevisionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_problem_id", nullable = false)
    private UserProblem userProblem;

    @Column(name = "quality_score", nullable = false)
    private int qualityScore;

    @Column(name = "time_taken_seconds")
    private Integer timeTakenSeconds;

    @Column(name = "revision_date")
    private LocalDateTime revisionDate;

    @PrePersist
    protected void onCreate() {
        revisionDate = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserProblem getUserProblem() {
        return userProblem;
    }

    public void setUserProblem(UserProblem userProblem) {
        this.userProblem = userProblem;
    }

    public int getQualityScore() {
        return qualityScore;
    }

    public void setQualityScore(int qualityScore) {
        this.qualityScore = qualityScore;
    }

    public Integer getTimeTakenSeconds() {
        return timeTakenSeconds;
    }

    public void setTimeTakenSeconds(Integer timeTakenSeconds) {
        this.timeTakenSeconds = timeTakenSeconds;
    }

    public LocalDateTime getRevisionDate() {
        return revisionDate;
    }
}
