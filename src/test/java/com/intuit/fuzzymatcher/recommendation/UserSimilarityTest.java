package com.intuit.fuzzymatcher.recommendation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.intuit.fuzzymatcher.domain.Element;
import com.intuit.fuzzymatcher.domain.ElementType;

public class UserSimilarityTest {
    private UserSimilarity userSimilarity;
    private List<User> users;

    @Before
    public void setUp() {
        User user1 = createUser(1, 25, "Male", "Engineer");
        User user2 = createUser(2, 30, "Female", "Teacher");
        User user3 = createUser(3, 35, "Male", "Doctor");
        User user4 = createUser(4, 28, "Male", "Engineer");
        User user5 = createUser(5, 32, "Female", "Scientist");
        User user6 = createUser(6, 27, "Male", "Programmer");
        users = Arrays.asList(user1, user2, user3, user4, user5, user6);
        userSimilarity = new UserSimilarity(users);
    }

    @Test
    public void testFindTopSimilarUsers() {
        User user = createUser(7, 26, "Male", "Engineer");
        int topN = 3;
        List<UserSimilarityResult> topSimilarUsers = userSimilarity.findTopSimilarUsers(user, topN);
        assertEquals(topN, topSimilarUsers.size());
        assertTrue(topSimilarUsers.stream().anyMatch(result -> result.getUser().getUserId() == 1));
        assertTrue(topSimilarUsers.stream().anyMatch(result -> result.getUser().getUserId() == 4));
        assertTrue(topSimilarUsers.stream().anyMatch(result -> result.getUser().getUserId() == 6));
        // Additional assertions for similarity scores if needed
    }

    @Test
    public void testFindTopSimilarUsers_NoSimilarUsers() {
        User user = createUser(8, 40, "Female", "Lawyer");
        int topN = 3;
        List<UserSimilarityResult> topSimilarUsers = userSimilarity.findTopSimilarUsers(user, topN);
        assertTrue(topSimilarUsers.isEmpty());
    }

    private User createUser(int userId, Integer age, String gender, String occupation) {
        Element<Integer> ageElement = age != null ? new Element.Builder<Integer>()
                .setType(ElementType.AGE)
                .setValue(age)
                .createElement() : null;
        Element<String> genderElement = gender != null ? new Element.Builder<String>()
                .setType(ElementType.GENDER)
                .setValue(gender)
                .createElement() : null;
        Element<String> occupationElement = occupation != null ? new Element.Builder<String>()
                .setType(ElementType.OCCUPATION)
                .setValue(occupation)
                .createElement() : null;
        return new User(userId, ageElement, genderElement, occupationElement);
    }
}