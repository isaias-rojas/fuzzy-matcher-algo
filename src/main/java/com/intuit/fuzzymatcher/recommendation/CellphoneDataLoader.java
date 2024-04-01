package com.intuit.fuzzymatcher.recommendation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.intuit.fuzzymatcher.domain.Element;
import com.intuit.fuzzymatcher.domain.ElementType;

public class CellphoneDataLoader {
        private static final String CELLPHONE_DATA_FILE = "/cellphone_data.csv";

    public List<Cellphone> loadCellphones() {
        List<Cellphone> cellphones = new ArrayList<>();

        try (InputStream inputStream = getClass().getResourceAsStream(CELLPHONE_DATA_FILE);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            boolean isFirstLine = true;
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] cellphoneData = line.split(",");
                if (cellphoneData.length == 14) {
                    int cellphoneId = Integer.parseInt(cellphoneData[0]);
                    Element<String> brand = new Element.Builder<String>()
                            .setType(ElementType.NAME)
                            .setValue(cellphoneData[1])
                            .createElement();
                    Element<String> model = new Element.Builder<String>()
                            .setType(ElementType.NAME)
                            .setValue(cellphoneData[2])
                            .createElement();
                    Element<String> operatingSystem = new Element.Builder<String>()
                            .setType(ElementType.TEXT)
                            .setValue(cellphoneData[3])
                            .createElement();
                    Element<Integer> internalMemory = new Element.Builder<Integer>()
                            .setType(ElementType.NUMBER)
                            .setValue(Integer.parseInt(cellphoneData[4]))
                            .createElement();
                    Element<Integer> ram = new Element.Builder<Integer>()
                            .setType(ElementType.NUMBER)
                            .setValue(Integer.parseInt(cellphoneData[5]))
                            .createElement();
                    Element<Double> performance = new Element.Builder<Double>()
                            .setType(ElementType.NUMBER)
                            .setValue(Double.parseDouble(cellphoneData[6]))
                            .createElement();
                    Element<Integer> mainCamera = new Element.Builder<Integer>()
                            .setType(ElementType.NUMBER)
                            .setValue(Integer.parseInt(cellphoneData[7]))
                            .createElement();
                    Element<Integer> selfieCamera = new Element.Builder<Integer>()
                            .setType(ElementType.NUMBER)
                            .setValue(Integer.parseInt(cellphoneData[8]))
                            .createElement();
                    Element<Integer> batterySize = new Element.Builder<Integer>()
                            .setType(ElementType.NUMBER)
                            .setValue(Integer.parseInt(cellphoneData[9]))
                            .createElement();
                    Element<Double> screenSize = new Element.Builder<Double>()
                            .setType(ElementType.NUMBER)
                            .setValue(Double.parseDouble(cellphoneData[10]))
                            .createElement();
                    Element<Integer> weight = new Element.Builder<Integer>()
                            .setType(ElementType.NUMBER)
                            .setValue(Integer.parseInt(cellphoneData[11]))
                            .createElement();
                    Element<Double> price = new Element.Builder<Double>()
                            .setType(ElementType.NUMBER)
                            .setValue(Double.parseDouble(cellphoneData[12]))
                            .createElement();
                    Element<String> releaseDate = new Element.Builder<String>()
                            .setType(ElementType.RELEASE_DATE)
                            .setValue(cellphoneData[13])
                            .createElement();

                    Cellphone cellphone = new Cellphone(cellphoneId, brand, model, operatingSystem,
                            internalMemory, ram, performance, mainCamera, selfieCamera, batterySize,
                            screenSize, weight, price, releaseDate);
                    cellphones.add(cellphone);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cellphones;
    }
    
}
