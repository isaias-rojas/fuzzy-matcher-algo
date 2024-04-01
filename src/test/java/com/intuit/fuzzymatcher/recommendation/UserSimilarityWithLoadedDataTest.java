package com.intuit.fuzzymatcher.recommendation;

import java.util.List;

public class UserSimilarityWithLoadedDataTest {
    
    private UserSimilarity userSimilarity;
    private List<User> users;

    public void main() {
        UserDataLoader userDataLoader = new UserDataLoader();
        users = userDataLoader.loadUsers();
        userSimilarity = new UserSimilarity(users);
        testFindTopSimilarUsersWithLoadedData();
    }

    private void testFindTopSimilarUsersWithLoadedData() {
        User targetUser = users.get(0);
        int topN = 5;
        List<User> topSimilarUsers = userSimilarity.findTopSimilarUsers(targetUser, topN);
        System.out.println("Top " + topN + " similar users for user " + targetUser.getUserId() + ":");
        for (User user : topSimilarUsers) {
            System.out.println(user);
        }
    }

    public static void main(String[] args) {
        UserSimilarityWithLoadedDataTest test = new UserSimilarityWithLoadedDataTest();
        test.main();
    }
}
