package com.intuit.fuzzymatcher.recommendation;

import com.intuit.fuzzymatcher.domain.Element;

public class User {
    private int userId;
    private Element<Integer> age;
    private Element<String> gender;
    private Element<String> occupation;

    public User(int userId, Element<Integer> age, Element<String> gender, Element<String> occupation) {
        this.userId = userId;
        this.age = age;
        this.gender = gender;
        this.occupation = occupation;
    }

    public int getUserId() {
        return userId;
    }

    public Element<Integer> getAge() {
        return age;
    }

    public Element<String> getGender() {
        return gender;
    }

    public Element<String> getOccupation() {
        return occupation;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", age=" + age.getPreProcessedValue() +
                ", gender=" + gender.getPreProcessedValue() +
                ", occupation=" + occupation.getPreProcessedValue() +
                '}';
    }
}
