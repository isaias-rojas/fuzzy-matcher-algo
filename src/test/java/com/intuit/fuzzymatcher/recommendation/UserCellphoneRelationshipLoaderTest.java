package com.intuit.fuzzymatcher.recommendation;

import java.util.List;

public class UserCellphoneRelationshipLoaderTest {
    public static void main(String[] args) {
        UserCellphoneRelationshipLoader relationshipLoader = new UserCellphoneRelationshipLoader();
        List<UserCellphoneRelationship> userCellphoneRelationships = relationshipLoader.loadUserCellphoneRelationships();

        // Print the user-cellphone relationships
        for (UserCellphoneRelationship relationship : userCellphoneRelationships) {
            int userId = relationship.getUserId();
            int cellphoneId = relationship.getCellphoneId();
            int rating = relationship.getRating();
            System.out.println("User ID: " + userId);
            System.out.println("Cellphone ID: " + cellphoneId);
            System.out.println("Rating: " + rating);
            System.out.println();
        }
    }
}