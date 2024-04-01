package com.intuit.fuzzymatcher.recommendation;

import java.util.List;
import java.util.Map;

import com.intuit.fuzzymatcher.component.MatchService;
import com.intuit.fuzzymatcher.domain.Match;

import com.intuit.fuzzymatcher.domain.Document;
import com.intuit.fuzzymatcher.domain.ElementType;
import com.intuit.fuzzymatcher.domain.Element;
import java.util.stream.Collectors;


public class CellphoneSimilarity {
        private final MatchService matchService;
    private final Map<Integer, Cellphone> cellphoneMap;

    public CellphoneSimilarity(List<Cellphone> cellphones) {
        this.matchService = new MatchService();
        this.cellphoneMap = cellphones.stream()
                .collect(Collectors.toMap(Cellphone::getCellphoneId, cellphone -> cellphone));    }

    public double getSimilarityScore(Cellphone cellphone1, Cellphone cellphone2) {
        Document doc1 = convertCellphoneToDocument(cellphone1);
        Document doc2 = convertCellphoneToDocument(cellphone2);

        Map<String, List<Match<Document>>> matchesByDocId = matchService.applyMatchByDocId(doc1, List.of(doc2));
        List<Match<Document>> matches
        if (matches.isEmpty()) {
            return 0.0;
        }

        return matches.get(0).getScore().getResult()    }

    public Cellphone getCellphoneById(int cellphoneId) {
        return cellphoneMap.get(cellphoneId);
    }

    private Document convertCellphoneToDocument(Cellphone cellphone) {
        return new Document.Builder(String.valueOf(cellphone.getCellphoneId()))
                .addElement(new Element.Builder<String>()
                        .setType(ElementType.TEXT)
                        .setValue(cellphone.getBrand())
                        .createElement())
                .addElement(new Element.Builder<String>()
                        .setType(ElementType.TEXT)
                        .setValue(cellphone.getModel())                        .createElement())
                .addElement(new Element.Builder<String>()
                        .setType(ElementType.TEXT)
                        .setValue(cellphone.getOperatingSystem())
                        .createElement())
                .addElement(new Element.Builder<Integer>()
                        .setType(ElementType.NUMBER)
                        .setValue(cellphone.getInternalMemory())
                        .createElement())
                .addElement(new Element.Builder<Integer>()
                        .setType(ElementType.NUMBER)
                        .setValue(cellphone.getRam())
                        .createElement())
                .addElement(new Element.Builder<Double>()
                        .setType(ElementType.NUMBER)
                        .setValue(cellphone.getPerformance())
                        .createElement())
                .addElement(new Element.Builder<Integer>()
                        .setType(ElementType.NUMBER)
                        .setValue(cellphone.getMainCamera())
                        .createElement())
                .addElement(new Element.Builder<Integer>()
                        .setType(ElementType.NUMBER)
                        .setValue(cellphone.getSelfieCamera())
                        .createElement())
                .addElement(new Element.Builder<Integer>()
                        .setType(ElementType.NUMBER)
                        .setValue(cellphone.getBatterySize())
                        .createElement())
                .addElement(new Element.Builder<Double>()
                        .setType(ElementType.NUMBER)
                        .setValue(cellphone.getScreenSize())
                        .createElement())
                .addElement(new Element.Builder<Integer>()
                        .setType(ElementType.NUMBER)
                        .setValue(cellphone.getWeight())
                        .createElement())
                .addElement(new Element.Builder<Integer>()
                        .setType(ElementType.NUMBER)
                        .setValue(cellphone.getPrice())
                        .createElement())
                .createDocument();
    }
}
