package com.siva.Moderate;

import java.util.*;

public class ExcelSpreadSheet {

    public static void main(String[] arg) {
        SpreadSheet<Integer> sheet = new SpreadSheet(10);
        System.out.println("Current RowSiZe " + sheet.sheetMaxRow);
        sheet.populateData(1, 1,1);
        System.out.println(sheet.getData(1,1));
        sheet.populateData(2, 1,1);
        System.out.println(sheet.getData(1,1));

        for(int rowIndex = 1; rowIndex < 12; rowIndex++) {
            sheet.populateData(rowIndex, rowIndex,1);
        }

        for(int rowIndex = 1; rowIndex < 12; rowIndex++) {
            System.out.println(sheet.getData(rowIndex,1));
        }

        System.out.println("Current RowSiZe " + sheet.sheetMaxRow);
    }
}


class Cell<T> {
    public T content;
}

class SpreadSheet<T> {

    Cell<T>[][] sheet;
    public int sheetMaxRow;
    int populatedCells;
    final int maxColSize =26;

    public SpreadSheet(int rows) {
        this.sheetMaxRow = rows;
        sheet = new Cell[rows][maxColSize];


    }

    public String computeFormula(String formula ) {
        return "";
    }


    public T getData(int row, int col) {
        int rowIndex = row -1;
        int colIndex = col-1;

        if(colIndex < 0 || colIndex > maxColSize || rowIndex < 0 || rowIndex > sheetMaxRow)
            throw new IllegalArgumentException("invalid column location");



        return sheet[rowIndex][colIndex].content;
    }

    public void populateData(T data, int row, int col) {

        int rowIndex = row -1;
        int colIndex = col-1;

        if(colIndex < 0 || colIndex > maxColSize)
            throw new IllegalArgumentException("invalid column location");

        if(rowIndex >= sheetMaxRow) {
            this.cloneSpreadSheet();
        }



        if(null == sheet[rowIndex][colIndex]) {
            sheet[rowIndex][colIndex] = new Cell<T>();
            populatedCells++;
        }
        sheet[rowIndex][colIndex].content = data;

        isCloningRequired();



    }

    private void isCloningRequired() {
        int totalCells = sheetMaxRow *  sheet[0].length;
        if(populatedCells > totalCells/2)
            this.cloneSpreadSheet();
    }

    private void cloneSpreadSheet() {

        int newRowCount = sheetMaxRow + sheetMaxRow *3/4;
        Cell<T>[][] cloneSheet = new Cell[newRowCount][maxColSize];
        for(int rowIndex =0; rowIndex < sheetMaxRow; rowIndex++) {
            for(int colIndex =0; colIndex < maxColSize; colIndex++) {
                cloneSheet[rowIndex][colIndex] = sheet[rowIndex][colIndex];
            }
        }
        this.sheet = cloneSheet;
        this.sheetMaxRow = newRowCount;
    }
}

class workBook {

    List<SpreadSheet> spreadSheetList;
}
