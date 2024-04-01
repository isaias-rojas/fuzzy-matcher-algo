package com.intuit.fuzzymatcher.recommendation;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import com.intuit.fuzzymatcher.domain.Element;
import com.intuit.fuzzymatcher.domain.ElementType;

public class UserSimilarityRandomDataTest {
        private UserSimilarity userSimilarity;
    private List<User> users;

    @Before
    public void setUp() {
        users = generateRandomUsers(100); // Generate 100 random users
        userSimilarity = new UserSimilarity(users);
    }

    @Test
    public void testFindTopSimilarUsers_RandomData() {
        Random random = new Random();

        for (int i = 0; i < 10; i++) { // Test 10 random target users
            User targetUser = users.get(random.nextInt(users.size()));
            int topN = random.nextInt(10) + 1; // Random value between 1 and 10

            List<User> topSimilarUsers = userSimilarity.findTopSimilarUsers(targetUser, topN);

            int availableSimilarUsers = users.stream()
                    .filter(u -> !u.equals(targetUser))
                    .collect(Collectors.toList())
                    .size();

            int expectedSimilarUsers = Math.min(availableSimilarUsers, topN);

            assertEquals(expectedSimilarUsers, topSimilarUsers.size());
        }
    }

    private List<User> generateRandomUsers(int count) {
        List<User> randomUsers = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            int userId = i;
            int age = random.nextInt(61) + 20; // Random age between 20 and 80
            String gender = random.nextBoolean() ? "Male" : "Female";
            String occupation = generateRandomOccupation(random);

            User user = createUser(userId, age, gender, occupation);
            randomUsers.add(user);
        }

        return randomUsers;
    }

    private String generateRandomOccupation(Random random) {
        String[] occupations = {"Engineer", "Doctor", "Teacher", "Scientist", "Programmer", "Manager", "Accountant", "Nurse", "Analyst", "Consultant"};
        return occupations[random.nextInt(occupations.length)];
    }

    private User createUser(int userId, int age, String gender, String occupation) {
        Element<Integer> ageElement = new Element.Builder<Integer>()
                .setType(ElementType.AGE)
                .setValue(age)
                .createElement();

        Element<String> genderElement = new Element.Builder<String>()
                .setType(ElementType.GENDER)
                .setValue(gender)
                .createElement();

        Element<String> occupationElement = new Element.Builder<String>()
                .setType(ElementType.OCCUPATION)
                .setValue(occupation)
                .createElement();

        return new User(userId, ageElement, genderElement, occupationElement);
    }
}
