package com.numerology.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.numerology.utils.*;
@Service
public class NumerologyService {
	

	public Map<String, Object> findCharacteristic(Date dob, int D, int C, int K) {
        // Step 1: Get digits from DOB and other inputs
        List<Character> digits = Utils.getDigitsFromInput(dob, D, C, K);

        // Step 2: Create frequency matrix
        Object[] frequencyData = Utils.createFrequencyMatrix(digits);
//        int[][] meaningMatrix = (int[][]) frequencyData[0];
//        int[][] frequencyMatrix = (int[][]) frequencyData[1];
        String[][] digitMatrix = (String[][]) frequencyData[2];

        // Step 3: Add characteristics to document
        Map<String, Object> characteristicsResult = Utils.addCharacteristicsToDoc(digitMatrix);

        // Step 4: Return the result
        return characteristicsResult;
    }
}
