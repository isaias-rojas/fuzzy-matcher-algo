package com.intuit.fuzzymatcher.recommendation;

import java.util.List;

public class UserDataLoaderTest {
        public static void main(String[] args) {
        UserDataLoader userDataLoader = new UserDataLoader();
        List<User> users = userDataLoader.loadUsers();

        for (User user : users) {
            System.out.println(user);
        }
    
    }
}