package com.intuit.fuzzymatcher.recommendation;

public class UserSimilarityResult {
    private User user;
    private double score;

    public UserSimilarityResult(User user, double score) {
        this.user = user;
        this.score = score;
    }

    public User getUser() {
        return user;
    }

    public double getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "UserSimilarityResult{" +
                "user=" + user +
                ", score=" + score +
                '}';
    }
}
