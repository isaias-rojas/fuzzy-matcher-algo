package com.intuit.fuzzymatcher.recommendation;

import java.util.List;

public class CellphoneDataLoaderTest {
        public static void main(String[] args) {
        CellphoneDataLoader cellphoneDataLoader = new CellphoneDataLoader();
        List<Cellphone> cellphones = cellphoneDataLoader.loadCellphones();

        for (Cellphone cellphone : cellphones) {
            System.out.println(cellphone);
        }
    }
}
