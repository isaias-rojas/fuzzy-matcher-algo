package com.intuit.fuzzymatcher.recommendation;

public class UserCellphoneRelationship {
    private int userId;
    private int cellphoneId;
    private int rating;

    public UserCellphoneRelationship(int userId, int cellphoneId, int rating) {
        this.userId = userId;
        this.cellphoneId = cellphoneId;
        this.rating = rating;
    }

    public int getUserId() {
        return userId;
    }

    public int getCellphoneId() {
        return cellphoneId;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "UserCellphoneRelationship{" +
                "userId=" + userId +
                ", cellphoneId=" + cellphoneId +
                ", rating=" + rating +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserCellphoneRelationship that = (UserCellphoneRelationship) o;

        if (userId != that.userId) return false;
        if (cellphoneId != that.cellphoneId) return false;
        return rating == that.rating;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setCellphoneId(int cellphoneId) {
        this.cellphoneId = cellphoneId;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    
    
}
