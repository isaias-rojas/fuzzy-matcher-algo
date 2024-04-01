package com.intuit.fuzzymatcher.recommendation;

import com.intuit.fuzzymatcher.domain.Element;

public class Cellphone {
    private int cellphoneId;
    private Element<String> brand;
    private Element<String> model;
    private Element<String> operatingSystem;
    private Element<Integer> internalMemory;
    private Element<Integer> ram;
    private Element<Double> performance;
    private Element<Integer> mainCamera;
    private Element<Integer> selfieCamera;
    private Element<Integer> batterySize;
    private Element<Double> screenSize;
    private Element<Integer> weight;
    private Element<Double> price;
    private Element<String> releaseDate;

    // Constructor
    public Cellphone(int cellphoneId, Element<String> brand, Element<String> model, Element<String> operatingSystem,
                    Element<Integer> internalMemory, Element<Integer> ram, Element<Double> performance,
                    Element<Integer> mainCamera, Element<Integer> selfieCamera, Element<Integer> batterySize,
                    Element<Double> screenSize, Element<Integer> weight, Element<Double> price,
                    Element<String> releaseDate) {
        this.cellphoneId = cellphoneId;
        this.brand = brand;
        this.model = model;
        this.operatingSystem = operatingSystem;
        this.internalMemory = internalMemory;
        this.ram = ram;
        this.performance = performance;
        this.mainCamera = mainCamera;
        this.selfieCamera = selfieCamera;
        this.batterySize = batterySize;
        this.screenSize = screenSize;
        this.weight = weight;
        this.price = price;
        this.releaseDate = releaseDate;
    }

    // Getters
    public int getCellphoneId() {
        return cellphoneId;
    }

    public Element<String> getBrand() {
        return brand;
    }

    public Element<String> getModel() {
        return model;
    }

    public Element<String> getOperatingSystem() {
        return operatingSystem;
    }

    public Element<Integer> getInternalMemory() {
        return internalMemory;
    }

    public Element<Integer> getRam() {
        return ram;
    }

    public Element<Double> getPerformance() {
        return performance;
    }

    public Element<Integer> getMainCamera() {
        return mainCamera;
    }

    public Element<Integer> getSelfieCamera() {
        return selfieCamera;
    }

    public Element<Integer> getBatterySize() {
        return batterySize;
    }

    public Element<Double> getScreenSize() {
        return screenSize;
    }

    public Element<Integer> getWeight() {
        return weight;
    }

    public Element<Double> getPrice() {
        return price;
    }

    public Element<String> getReleaseDate() {
        return releaseDate;
    }

    // toString method
    @Override
    public String toString() {
        return "Cellphone{" +
                "cellphoneId=" + cellphoneId +
                ", brand=" + brand.getPreProcessedValue() +
                ", model=" + model.getPreProcessedValue() +
                ", operatingSystem=" + operatingSystem.getPreProcessedValue() +
                ", internalMemory=" + internalMemory.getPreProcessedValue() +
                ", ram=" + ram.getPreProcessedValue() +
                ", performance=" + performance.getPreProcessedValue() +
                ", mainCamera=" + mainCamera.getPreProcessedValue() +
                ", selfieCamera=" + selfieCamera.getPreProcessedValue() +
                ", batterySize=" + batterySize.getPreProcessedValue() +
                ", screenSize=" + screenSize.getPreProcessedValue() +
                ", weight=" + weight.getPreProcessedValue() +
                ", price=" + price.getPreProcessedValue() +
                ", releaseDate=" + releaseDate.getPreProcessedValue() +
                '}';
    }
}
