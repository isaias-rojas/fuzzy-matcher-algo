package com.intuit.fuzzymatcher.recommendation;

import java.util.List;
import java.util.Map;

public class UserCellphoneRelationshipLoaderTest {
        public static void main(String[] args) {
        UserCellphoneRelationshipLoader relationshipLoader = new UserCellphoneRelationshipLoader();
        Map<Integer, List<Integer>> userCellphoneMap = relationshipLoader.loadUserCellphoneRelationships();

        // Print the user-cellphone relationships
        for (Map.Entry<Integer, List<Integer>> entry : userCellphoneMap.entrySet()) {
            int userId = entry.getKey();
            List<Integer> cellphoneIds = entry.getValue();
            System.out.println("User ID: " + userId);
            System.out.println("Cellphone IDs: " + cellphoneIds);
            System.out.println();
        }
    }
}
