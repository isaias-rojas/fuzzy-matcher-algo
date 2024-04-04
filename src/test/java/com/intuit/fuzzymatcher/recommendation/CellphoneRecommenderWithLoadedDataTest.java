package com.intuit.fuzzymatcher.recommendation;

import java.util.List;

public class CellphoneRecommenderWithLoadedDataTest {
    private UserSimilarity userSimilarity;
    private List<User> users;
    private List<UserCellphoneRelationship> userCellphoneRelationships;
    private List<Cellphone> cellphones;

    public void main() {
        UserDataLoader userDataLoader = new UserDataLoader();
        users = userDataLoader.loadUsers();

        UserCellphoneRelationshipLoader relationshipLoader = new UserCellphoneRelationshipLoader();
        userCellphoneRelationships = relationshipLoader.loadUserCellphoneRelationships();

        CellphoneDataLoader cellphoneDataLoader = new CellphoneDataLoader();
        cellphones = cellphoneDataLoader.loadCellphones();

        userSimilarity = new UserSimilarity(users);

        testGenerateRecommendations();
    }

    private void testGenerateRecommendations() {
        User targetUser = users.get(0);
        int topK = 5;

        CellphoneRecommender recommender = new CellphoneRecommender(userSimilarity);
        List<CellphoneRecommendation> recommendations = recommender.generateRecommendations(targetUser, userCellphoneRelationships, topK);

        System.out.println("Top " + topK + " recommendations for user:");
        System.out.println("User ID: " + targetUser.getUserId());
        System.out.println("Age: " + targetUser.getAge().getPreProcessedValue());
        System.out.println("Gender: " + targetUser.getGender().getPreProcessedValue());
        System.out.println("Occupation: " + targetUser.getOccupation().getPreProcessedValue());
        System.out.println();

        System.out.println("Recommended Cellphones:");
        for (CellphoneRecommendation recommendation : recommendations) {
            int cellphoneId = recommendation.getCellphoneId();
            Cellphone cellphone = findCellphoneById(cellphoneId);
            if (cellphone != null) {
                System.out.println("Cellphone ID: " + cellphone.getCellphoneId());
                System.out.println("Brand: " + cellphone.getBrand().getPreProcessedValue());
                System.out.println("Model: " + cellphone.getModel().getPreProcessedValue());
                System.out.println("Price: " + cellphone.getPrice().getPreProcessedValue());
                System.out.println("Score: " + recommendation.getScore());
                System.out.println();
            }
        }
    }

    private Cellphone findCellphoneById(int cellphoneId) {
        for (Cellphone cellphone : cellphones) {
            if (cellphone.getCellphoneId() == cellphoneId) {
                return cellphone;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        CellphoneRecommenderWithLoadedDataTest test = new CellphoneRecommenderWithLoadedDataTest();
        test.main();
    }
}
