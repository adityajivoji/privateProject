package com.numerology.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.numerology.utils.*;
@Service
public class NumerologyService {
		
	public Map<String, Object> reduceToSingleDigit(int number) {

        Map<String, Object> Result = new HashMap<>();
        Result.put("NumberAfterReduction", Utils.reduceToSingleDigit(number));

        return Result;
    }
	
	public Map<String, Object> sumOfDigits(int number) {

        Map<String, Object> Result = new HashMap<>();
        Result.put("SumOfNumber", Utils.sumOfDigits(number));

        return Result;
    }
	
	public Map<String, Object> getDigitMatrix(Date dob, int D, int C, int K) {

        Map<String, Object> Result = new HashMap<>();
        List<Character> digits = Utils.getDigitsFromInput(dob, D, C, K);

        // Step 2: Create frequency matrix
        Object[] frequencyData = Utils.createFrequencyMatrix(digits);
        Result.put("Digit Matrix", frequencyData[2]);

        return Result;
    }
	
	public Map<String, Object> findCharacteristic(Date dob, int D, int C, int K) {
        // Step 1: Get digits from DOB and other inputs
        List<Character> digits = Utils.getDigitsFromInput(dob, D, C, K);

        // Step 2: Create frequency matrix
        Object[] frequencyData = Utils.createFrequencyMatrix(digits);
//        int[][] meaningMatrix = (int[][]) frequencyData[0];
//        int[][] frequencyMatrix = (int[][]) frequencyData[1];
        String[][] digitMatrix = (String[][]) frequencyData[2];

        Map<String, Object> characteristicsResult = Utils.addCharacteristicsToDoc(digitMatrix);

        return characteristicsResult;
    }
	
	public Map<String, Object> getRelationships(Date dob, int D, int C, int K) {

        return Utils.getRelationships(C, D);
    }
	
	public Map<String, Object> getDCAffect(int D, int C) {

        return Utils.getDCAffect(D, C);
    }
	
	public Map<String, String> getDcCharacteristic(int D, int C) {

        return Utils.getDcCharacteristic(D, C);
    }
	
	public List<Map<String, String>> FreqEffect(Date dob, int D, int C, int K) {
		List<Character> digits = Utils.getDigitsFromInput(dob, D, C, K);
		Object[] frequencyData = Utils.createFrequencyMatrix(digits);
		int[][] meaningMatrix = (int[][]) frequencyData[0];
		int[][] frequencyMatrix = (int[][]) frequencyData[1];
//		int[][] digitMatrix = (int[][]) frequencyData[2];
        return Utils.outputBasedOnFreq(frequencyMatrix, meaningMatrix);
    }
	
	public static void printIntMatrix(int[][] matrix, String title) {
	    System.out.println(title + ":");
	    for (int[] row : matrix) {
	        for (int elem : row) {
	            System.out.print(elem + "\t");  // Tab-separated for alignment
	        }
	        System.out.println();  // New line after each row
	    }
	    System.out.println();
	}
	
	public static void printStringMatrix(String[][] matrix, String title) {
	    System.out.println(title + ":");
	    for (String[] row : matrix) {
	        for (String elem : row) {
	            System.out.print((elem != null ? elem : "") + "\t");  // Handles null values
	        }
	        System.out.println();  // New line after each row
	    }
	    System.out.println();
	}

	
	public Map<String, Object> missingNumber(Date dob, int D, int C, int K) {
        // Step 1: Get digits from DOB and other inputs
        List<Character> digits = Utils.getDigitsFromInput(dob, D, C, K);

        // Step 2: Create frequency matrix
        Object[] frequencyData = Utils.createFrequencyMatrix(digits);
        int[][] meaningMatrix = (int[][]) frequencyData[0];
        int[][] frequencyMatrix = (int[][]) frequencyData[1];
        String[][] digitMatrix = (String[][]) frequencyData[2];
//        printIntMatrix(meaningMatrix, "Meaning Matrix");
//        printIntMatrix(frequencyMatrix, "Frequency Matrix");
//        printStringMatrix(digitMatrix, "Digit Matrix");

        return Utils.remedies(frequencyMatrix, meaningMatrix);
    }
	
	public Map<String, Object> getDirections(int K) {
        return Utils.getDirections(K);
    }
	
	public Map<String, Object> personalYearEffect() {
        return Utils.personalYearEffect();
    }
	
	public Map<String, Object> calculatePersonalYear(Date dob, int year) {
        return Utils.calculatePersonalYear(dob, year);
    }
	
	public Map<String, Object> calculatePersonalMonth(Date dob, int year, int month) {
        return Utils.calculatePersonalMonth(dob, year, month);
    }
	
	public String[][] createPYTable(int year, Date dob, int D, int C, int K) {
        return Utils.createPersonalYearTable(year, dob, D, C, K);
    }
	
	public Map<String, Object> getProfession(int D, int C) {

        return Utils.getProfession(D, C);
    }
	
	public Map<String, Object> calculateNameSum(String name) {

        return Utils.calculateNameSum(name);
    }
}
