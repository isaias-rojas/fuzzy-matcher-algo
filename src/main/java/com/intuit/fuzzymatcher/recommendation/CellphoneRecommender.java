package com.intuit.fuzzymatcher.recommendation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CellphoneRecommender {
        private UserSimilarity userSimilarity;

    public CellphoneRecommender(UserSimilarity userSimilarity) {
        this.userSimilarity = userSimilarity;
    }

    public List<CellphoneRecommendation> generateRecommendations(User user, List<UserCellphoneRelationship> userCellphoneRelationships, int topK) {
        List<User> similarUsers = userSimilarity.findTopSimilarUsers(user, 5); // Find top 5 similar users

        Map<Integer, Double> cellphoneScores = new HashMap<>();

        for (User similarUser : similarUsers) {
            List<UserCellphoneRelationship> similarUserRatings = userCellphoneRelationships.stream()
                    .filter(ucr -> ucr.getUserId() == similarUser.getUserId())
                    .collect(Collectors.toList());

            for (UserCellphoneRelationship rating : similarUserRatings) {
                int cellphoneId = rating.getCellphoneId();
                double score = rating.getRating(); // Use the rating as the score

                // Update the score if the cellphone is already in the map
                cellphoneScores.put(cellphoneId, cellphoneScores.getOrDefault(cellphoneId, 0.0) + score);
            }
        }

        // Sort the cellphones by their recommendation scores in descending order
        List<Map.Entry<Integer, Double>> sortedCellphones = new ArrayList<>(cellphoneScores.entrySet());
        sortedCellphones.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // Select the top K cellphones as recommendations
        List<CellphoneRecommendation> recommendations = sortedCellphones.stream()
                .limit(topK)
                .map(entry -> {
                    int cellphoneId = entry.getKey();
                    double score = entry.getValue();
                    return new CellphoneRecommendation(cellphoneId, score);
                })
                .collect(Collectors.toList());

        return recommendations;
    }
}
