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
        List<UserSimilarityResult> similarUsers = userSimilarity.findTopSimilarUsers(user, 5); // Find top 5 similar users

        Map<Integer, Double> cellphoneScores = new HashMap<>();

        for (UserSimilarityResult similarUser : similarUsers) {
            List<UserCellphoneRelationship> similarUserRatings = userCellphoneRelationships.stream()
                    .filter(ucr -> ucr.getUserId() == similarUser.getUser().getUserId())
                    .collect(Collectors.toList());

            for (UserCellphoneRelationship rating : similarUserRatings) {
                int cellphoneId = rating.getCellphoneId();
                double score = rating.getRating() * similarUser.getScore(); // Use the rating multiplied by the similarity score
                cellphoneScores.put(cellphoneId, cellphoneScores.getOrDefault(cellphoneId, 0.0) + score);
            }
        }

        List<Map.Entry<Integer, Double>> sortedCellphones = new ArrayList<>(cellphoneScores.entrySet());
        sortedCellphones.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
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