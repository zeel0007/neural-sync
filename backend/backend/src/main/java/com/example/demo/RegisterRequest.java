package com.example.demo;

public class RegisterRequest {
    private String email;
    private String password;
    private String name;
    private int goal;

    // Getters and Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getGoal() { return goal; }
    public void setGoal(int goal) { this.goal = goal; }
}
