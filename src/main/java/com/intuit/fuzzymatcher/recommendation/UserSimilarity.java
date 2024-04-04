package com.intuit.fuzzymatcher.recommendation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.intuit.fuzzymatcher.component.MatchService;
import com.intuit.fuzzymatcher.domain.Match;
import com.intuit.fuzzymatcher.domain.Document;
import com.intuit.fuzzymatcher.domain.Element;
import com.intuit.fuzzymatcher.domain.ElementType;


public class UserSimilarity {
    private List<User> users;
    private MatchService matchService;

    public UserSimilarity(List<User> users) {
        this.users = users;
        this.matchService = new MatchService();
    }

    public List<UserSimilarityResult> findTopSimilarUsers(User user, int topN) {
        List<Document> documents = convertUsersToDocuments(users);
        Document userDocument = convertUserToDocument(user);
        if (userDocument == null) {
            return Collections.emptyList();
        }

        Map<String, List<Match<Document>>> matchesByDocId = matchService.applyMatchByDocId(userDocument, documents);
        List<Match<Document>> matches = matchesByDocId.getOrDefault(userDocument.getKey(), Collections.emptyList());
        if (matches.isEmpty()) {
            return Collections.emptyList();
        }

        List<UserSimilarityResult> sortedUsers = matches.stream()
                .filter(match -> !match.getMatchedWith().getKey().equals(userDocument.getKey())) // Exclude the target user
                .map(match -> new UserSimilarityResult(convertDocumentToUser(match.getMatchedWith()), match.getScore().getResult()))
                .sorted(Comparator.comparingDouble(UserSimilarityResult::getScore).reversed())
                .collect(Collectors.toList());

        int availableSimilarUsers = sortedUsers.size();
        if (topN >= availableSimilarUsers) {
            return sortedUsers;
        } else {
            return sortedUsers.subList(0, topN);
        }
    }

    private List<Document> convertUsersToDocuments(List<User> users) {
        return users.stream()
                .map(this::convertUserToDocument)
                .collect(Collectors.toList());
    }

    private Document convertUserToDocument(User user) {
        if (user.getAge() == null && user.getGender() == null && user.getOccupation() == null) {
            return null;
        }
    
        return new Document.Builder(String.valueOf(user.getUserId()))
                .addElement(user.getAge())
                .addElement(user.getGender())
                .addElement(user.getOccupation())
                .createDocument();
    }
    private User convertDocumentToUser(Document document) {
        int userId = Integer.parseInt(document.getKey());
        Element<Integer> age = document.getElements().stream()
                .filter(element -> element.getElementClassification().getElementType().equals(ElementType.AGE))
                .findFirst()
                .orElse(null);
        Element<String> gender = document.getElements().stream()
                .filter(element -> element.getElementClassification().getElementType().equals(ElementType.GENDER))
                .findFirst()
                .orElse(null);
        Element<String> occupation = document.getElements().stream()
                .filter(element -> element.getElementClassification().getElementType().equals(ElementType.OCCUPATION))
                .findFirst()
                .orElse(null);
        return new User(userId, age, gender, occupation);
    }

    
}
