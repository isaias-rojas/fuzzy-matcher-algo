package com.intuit.fuzzymatcher.recommendation;

public class CellphoneRecommendation {
    private int cellphoneId;
    private double score;

    public CellphoneRecommendation(int cellphoneId, double score) {
        this.cellphoneId = cellphoneId;
        this.score = score;
    }

    public int getCellphoneId() {
        return cellphoneId;
    }

    public double getScore() {
        return score;
    }
}
