package com.intuit.fuzzymatcher.recommendation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserCellphoneRelationshipLoader {
    private static final String USER_CELLPHONE_RELATIONSHIP_FILE = "/user_cellphone_ratings.csv";

    public List<UserCellphoneRelationship> loadUserCellphoneRelationships() {
        List<UserCellphoneRelationship> relationships = new ArrayList<>();

        try (InputStream inputStream = getClass().getResourceAsStream(USER_CELLPHONE_RELATIONSHIP_FILE);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            boolean isFirstLine = true;
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] relationshipData = line.split(",");
                if (relationshipData.length == 3) {
                    int userId = Integer.parseInt(relationshipData[0]);
                    int cellphoneId = Integer.parseInt(relationshipData[1]);
                    int rating = Integer.parseInt(relationshipData[2]);
                    UserCellphoneRelationship relationship = new UserCellphoneRelationship(userId, cellphoneId, rating);
                    relationships.add(relationship);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return relationships;
    }
}
