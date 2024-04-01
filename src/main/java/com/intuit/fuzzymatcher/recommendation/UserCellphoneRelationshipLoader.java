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

    public Map<Integer, List<Integer>> loadUserCellphoneRelationships() {
        Map<Integer, List<Integer>> userCellphoneMap = new HashMap<>();

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

                    // Add the cellphone ID to the list of cellphones associated with the user
                    userCellphoneMap.computeIfAbsent(userId, k -> new ArrayList<>()).add(cellphoneId);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userCellphoneMap;
    }
}
