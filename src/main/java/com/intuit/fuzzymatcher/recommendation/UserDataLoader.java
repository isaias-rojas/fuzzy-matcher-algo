package com.intuit.fuzzymatcher.recommendation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.intuit.fuzzymatcher.domain.Element;
import com.intuit.fuzzymatcher.domain.ElementType;

public class UserDataLoader {

    private static final String USER_DATA_FILE = "/users_data.csv";

    public List<User> loadUsers() {
        List<User> users = new ArrayList<>();

        try (InputStream inputStream = getClass().getResourceAsStream(USER_DATA_FILE);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            boolean isFirstLine = true; 
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] userData = line.split(",");
                if (userData.length == 4) {
                    int userId = Integer.parseInt(userData[0]);
                    int age = Integer.parseInt(userData[1]);
                    String gender = userData[2];
                    String occupation = userData[3];

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

                    User user = new User(userId, ageElement, genderElement, occupationElement);
                    users.add(user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }
    
}
