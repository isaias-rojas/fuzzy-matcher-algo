package com.intuit.fuzzymatcher.Algoritmo;

import com.intuit.fuzzymatcher.component.MatchService;
import com.intuit.fuzzymatcher.domain.Document;
import com.intuit.fuzzymatcher.domain.Element;
import com.intuit.fuzzymatcher.domain.ElementType;
import com.intuit.fuzzymatcher.domain.Match;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LevenshteinDistanceTest {
    @Test
    public void wordsWithMultipleLetterDifferencesTest() {
        Document ref = new Document.Builder("unique")
                .addElement(new Element.Builder<String>()
                        .setValue("playing")
                        .setType(ElementType.TEXT_LEVENTH)
                        .createElement())
                .createDocument();

        String[] input = {"playing", "praying"};

        List<Document> documentList = IntStream.range(0, input.length).mapToObj(index ->
                        new Document.Builder(String.valueOf(index + 1))
                                .addElement(new Element.Builder<String>()
                                        .setValue(input[index])
                                        .setType(ElementType.TEXT_LEVENTH)
                                        .createElement())
                                .createDocument())
                .collect(Collectors.toList());

        MatchService matchService = new MatchService();
        Map<String, List<Match<Document>>> result = matchService.applyMatchByDocId(ref, documentList);

        double score = result.values().stream()
                .flatMap(List::stream)
                .findFirst()
                .map(match -> match.getScore().getResult())
                .orElse(0.0);

        Assert.assertTrue("Score should be less than perfect", score < 1.0);
    }

    @Test
    public void singleWordsTestScore() {
        Document ref = new Document.Builder("unique")
                .addElement(new Element.Builder<String>()
                        .setValue("Casa")
                        .setType(ElementType.TEXT_LEVENTH)
                        .createElement())
                .createDocument();

        String[] input = {"cass", "Casa"};

        List<Document> documentList = IntStream.range(0, input.length).mapToObj(index -> {
            return new Document.Builder(String.valueOf(index + 1))
                    .addElement(new Element.Builder<String>()
                            .setValue(input[index])
                            .setType(ElementType.TEXT_LEVENTH)
                            .createElement())
                    .createDocument();
        }).collect(Collectors.toList());

        MatchService matchService = new MatchService();
        Map<String, List<Match<Document>>> result = matchService.applyMatchByDocId(ref, documentList);

        double score = result.values().stream()
                .flatMap(List::stream)
                .findFirst()
                .map(match -> match.getScore().getResult())
                .orElse(1.0);

        Assert.assertEquals(1.0, score, 0.0001);
    }


    @Test
    public void specialCharactersRemovalTest() {
        Document ref = new Document.Builder("refDoc")
                .addElement(new Element.Builder<String>()
                        .setValue("Ex@mple")
                        .setType(ElementType.TEXT_LEVENTH)
                        .createElement())
                .createDocument();

        String[] inputs = {"Ex@mple", "Example"};

        List<Document> documentList = IntStream.range(0, inputs.length)
                .mapToObj(index ->
                        new Document.Builder(String.valueOf(index))
                                .addElement(new Element.Builder<String>()
                                        .setValue(inputs[index])
                                        .setType(ElementType.TEXT_LEVENTH)
                                        .createElement())
                                .createDocument())
                .collect(Collectors.toList());

        MatchService matchService = new MatchService();
        Map<String, List<Match<Document>>> result = matchService.applyMatchByDocId(ref, documentList);

        double score = result.values().stream()
                .flatMap(List::stream)
                .findFirst()
                .map(match -> match.getScore().getResult())
                .orElse(1.0);

        Assert.assertEquals("Score should be perfect after removing special characters", 1.0, score, 0.0001);
    }
    @Test
    public void identicalUsersTest() {
        Document refUser = new Document.Builder("1")
                .addElement(new Element.Builder<String>()
                        .setValue("username123")
                        .setType(ElementType.NAME)
                        .createElement())
                .createDocument();

        List<Document> usersToCompare = Arrays.asList(
                new Document.Builder("2")
                        .addElement(new Element.Builder<String>()
                                .setValue("username1234234")
                                .setType(ElementType.NAME)
                                .createElement())
                        .createDocument()
        );

        MatchService matchService = new MatchService();
        Map<String, List<Match<Document>>> matchResults = matchService.applyMatchByDocId(refUser, usersToCompare);

        double score = matchResults.values().stream()
                .flatMap(List::stream)
                .findFirst()
                .map(match -> match.getScore().getResult())
                .orElse(0.0); // Default en caso de no encontrar coincidencias

        Assert.assertEquals("Score should be perfect for identical users", 1.0, score, 0.0);
    }
    @Test
    public void dateComparisonTest() {
        Document ref = new Document.Builder("unique")
                .addElement(new Element.Builder<String>()
                        .setValue("2023-01-01")
                        .setType(ElementType.RELEASE_DATE)
                        .createElement())
                .createDocument();

        String[] input = {"2023-01-02", "2025-01-01"};
        List<Document> documentList = IntStream.range(0, input.length)
                .mapToObj(index -> new Document.Builder(String.valueOf(index + 1))
                        .addElement(new Element.Builder<String>()
                                .setValue(input[index])
                                .setType(ElementType.RELEASE_DATE)
                                .createElement())
                        .createDocument())
                .collect(Collectors.toList());

        MatchService matchService = new MatchService();
        Map<String, List<Match<Document>>> result = matchService.applyMatchByDocId(ref, documentList);

        List<Double> scores = result.values().stream()
                .flatMap(List::stream)
                .map(match -> match.getScore().getResult())
                .collect(Collectors.toList());

        if (!scores.isEmpty()) {
            Assert.assertTrue("Score for a close date should be high", scores.get(0) > 0.5);

            if (scores.size() > 1) {
                Assert.assertTrue("Score for a distant date should be low", scores.get(1) < 0.5);
            }
        } else {
            Assert.fail("No matching scores were generated.");
        }
    }

}