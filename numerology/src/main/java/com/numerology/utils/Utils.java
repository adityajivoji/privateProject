package com.numerology.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Utils {
    public static int reduceToSingleDigit(int number) {
        while (number > 9) {
            number = sumOfDigits(number);
        }
        return number;
    }

    public static int sumOfDigits(int number) {
        int sum = 0;
        while (number != 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }
    
    public static List<Character> getDigitsFromInput(Date dob, int D, int C, int K) {
        List<Character> digits = new ArrayList<>();
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dob);
        
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        if (!((day >= 1 && day <= 9) || day == 10 || day == 20 || day == 30)) {
            for (char digit : String.valueOf(day).toCharArray()) {
                digits.add(digit);
            }
        }

        int month = calendar.get(Calendar.MONTH) + 1; // Months are 0-based in Calendar
        for (char digit : String.valueOf(month).toCharArray()) {
            digits.add(digit);
        }
        
        int year = calendar.get(Calendar.YEAR);
        for (char digit : String.valueOf(year).toCharArray()) {
            digits.add(digit);
        }
        
        for (char digit : String.valueOf(D).toCharArray()) {
            digits.add(digit);
        }

        for (char digit : String.valueOf(C).toCharArray()) {
            digits.add(digit);
        }

        for (char digit : String.valueOf(K).toCharArray()) {
            digits.add(digit);
        }

        return digits;
    }
    
    public static Object[] createFrequencyMatrix(List<Character> digits) {
        int[][] meaningMatrix = {
            {4, 9, 2},
            {3, 5, 7},
            {8, 1, 6}
        };

        int[][] frequencyMatrix = new int[3][3];
        String[][] digitMatrix = new String[3][3];

        // Initialize digitMatrix with empty strings
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                digitMatrix[i][j] = "";
            }
        }

        for (char digitChar : digits) {
            int digit = Character.getNumericValue(digitChar);
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (meaningMatrix[i][j] == digit) {
                        frequencyMatrix[i][j]++;
                        digitMatrix[i][j] += digit; // Concatenate the digit
                        break;
                    }
                }
            }
        }

        return new Object[]{meaningMatrix, frequencyMatrix, digitMatrix};
    }
    
    public static Map<String, Integer> findFreq(String[][] digitMatrix) {
        int[][] meaningMatrix = {
            {4, 9, 2},
            {3, 5, 7},
            {8, 1, 6}
        };

        Map<String, Integer> freq = new HashMap<>();

        for (int i = 0; i < digitMatrix.length; i++) {
            for (int j = 0; j < digitMatrix[i].length; j++) {
                String num1 = digitMatrix[i][j];
                int num2 = meaningMatrix[i][j];

                if (num1.length() > 0) {
                    freq.put(String.valueOf(num2), num1.length());
                } else {
                    freq.put(String.valueOf(num2), 0);
                }
            }
        }

        return freq;
    }
    
    public static List<Integer> findPresentSets(String[][] digitMatrix) {
        int[][] meaningMatrix = {
            {4, 9, 2},
            {3, 5, 7},
            {8, 1, 6}
        };

        List<Integer> present = new ArrayList<>();

        for (int i = 0; i < digitMatrix.length; i++) {
            for (int j = 0; j < digitMatrix[i].length; j++) {
                if (digitMatrix[i][j].length() > 0) {
                    present.add(meaningMatrix[i][j]);
                }
            }
        }

        return present;
    }
    

    public static Map<String, Object> addCharacteristicsToDoc(String[][] digitMatrix) {
        Map<List<Integer>, String> characteristics = new HashMap<>();
        characteristics.put(Arrays.asList(3, 4, 8), "Thought Plane:\nVisualization power is strong, Prudent, Planner Cunning, political, (Might be unethical, chance of becoming selfish, out of box thought to get work done), Correlate with time thought can be good / bad");
        characteristics.put(Arrays.asList(1, 5, 9), "Will Plane:\nVery Strong will power, Good Decision maker, Can adjust with any situation, Make single plan and execute it (Ant mentality single minded perseverance of aim) Do not be distracted. Can give good advice to others. 5 is the game changer to activate this plane can make quick decisions and stick to the decisions.");
        characteristics.put(Arrays.asList(2, 6, 7), "Action Plane:\nPerson with action, Quick approach, Always grab the opportunities, Very good for sports, Physical fitness inclined, Good or Bad actions will be decided by the time");
        characteristics.put(Arrays.asList(2, 4, 9), "Mental Plane:\nVery sharp memory, Very Intelligent, will do things different from others, 4 is common to thought and mental planes. Deep research and gets to the bottom of any matter. Easily grasp and remember things. Usually excel in field they choose.");
        characteristics.put(Arrays.asList(3, 5, 7), "Emotional Plane:\nVery emotional, Golden Heart, Spiritual, Religious, god believing, Compassionate. Helpful Not good businessman. Should choose profession as per numbers (Teacher, Doctor, Religious Teacher)");
        characteristics.put(Arrays.asList(1, 6, 8), "Practical Plane:\nPerson with practical approach. Goes into depth on issues. Arrow of prosperity (if this plane exists). Should / can Works independently. Materialistic approach Good Financial understanding. Long term planning Future looking - when returns will fructify.");
        characteristics.put(Arrays.asList(4, 5, 6), "Golden Plane:\nProgress after birth of a child/ marriage. Aggressive, balance and rich. They know purpose of life. Might entirely change their life path for a chosen path and become popular & powerful. If activated by girl child she should give a silver gift before leaving house after marriage.");
        characteristics.put(Arrays.asList(2, 5, 8), "Silver Plane:\nEarth Elements. Real estate property / stability in life. Very patient wait for right moment. Never lose sight of the goal. Achieve success at early age. Determined keep moving towards goal. No show off Very focused.");

        Map<List<Integer>, String> crossRelations = new HashMap<>();
        crossRelations.put(Arrays.asList(7, 9), "Keep balance in situation");
        crossRelations.put(Arrays.asList(1, 7), "May go into depression");
        crossRelations.put(Arrays.asList(1, 3), "Spiritual Knowledge");
        crossRelations.put(Arrays.asList(3, 9), "Litigations, Argumentative");

        Map<String, Object> result = new HashMap<>();
        List<String> characteristicsList = new ArrayList<>();
        List<String> crossRelationsList = new ArrayList<>();

        List<Integer> present = findPresentSets(digitMatrix); // Ensure this function exists and works correctly

        // Adding Characteristics to the result
        for (List<Integer> key : characteristics.keySet()) {
            if (isTupleInList(key, present)) {
                characteristicsList.add(characteristics.get(key));
            }
        }
        if (characteristicsList.isEmpty()) {
            characteristicsList.add("No Planes Present");
        }
        result.put("Characteristics", characteristicsList);

        // Adding Cross Relations to the result
        for (List<Integer> key : crossRelations.keySet()) {
            if (isTupleInList(key, present)) {
                crossRelationsList.add(crossRelations.get(key));
            }
        }
        result.put("Cross Relations", crossRelationsList);

        return result;
    }

    public static boolean isTupleInList(List<Integer> tup, List<Integer> lst) {
        return tup.stream().allMatch(lst::contains);
    }

//    public static List<Integer> findPresentSets(String[][] digitMatrix) {
//        int[][] meaningMatrix = {
//            {4, 9, 2},
//            {3, 5, 7},
//            {8, 1, 6}
//        };
//
//        List<Integer> present = new ArrayList<>();
//
//        for (int i = 0; i < digitMatrix.length; i++) {
//            for (int j = 0; j < digitMatrix[i].length; j++) {
//                if (digitMatrix[i][j].length() > 0) {
//                    present.add(meaningMatrix[i][j]);
//                }
//            }
//        }
//
//        return present;
//    }
    public static Map<String, Object> getRelationships(int C, int D) {
        // Defining friends relationships
        Map<Integer, Map<String, List<Integer>>> friends = new HashMap<>();
        friends.put(1, createFriendMap(Arrays.asList(9, 2, 3, 5, 6, 1), Arrays.asList(8), Arrays.asList(4, 7)));
        friends.put(2, createFriendMap(Arrays.asList(1, 5, 3, 2, 7), Arrays.asList(8, 4, 9), Arrays.asList(6, 7)));
        friends.put(3, createFriendMap(Arrays.asList(1, 5, 3, 7), Arrays.asList(6), Arrays.asList(2, 4, 7, 8, 9)));
        friends.put(4, createFriendMap(Arrays.asList(7, 1, 5, 6), Arrays.asList(2, 4, 8, 9), Arrays.asList(3)));
        friends.put(5, createFriendMap(Arrays.asList(1, 2, 3, 6, 5), new ArrayList<>(), Arrays.asList(4, 7, 8, 9)));
        friends.put(6, createFriendMap(Arrays.asList(1, 7, 4, 6, 5), Arrays.asList(3), Arrays.asList(2, 8, 9)));
        friends.put(7, createFriendMap(Arrays.asList(4, 6, 1, 5, 3), new ArrayList<>(), Arrays.asList(2, 3, 8, 9, 7)));
        friends.put(8, createFriendMap(Arrays.asList(5, 3, 4, 6, 7), Arrays.asList(1, 2, 4, 8), Arrays.asList(9, 6, 7)));
        friends.put(9, createFriendMap(Arrays.asList(1, 5), Arrays.asList(2, 4), Arrays.asList(3, 7, 6, 8, 9)));

        // Set of non-friends for C and D
        Set<Integer> nonfriendsC = new HashSet<>(friends.get(C).get("NonFriends"));
        Set<Integer> nonfriendsD = new HashSet<>(friends.get(D).get("NonFriends"));

        // All non-friends and anti-numbers
        Set<Integer> allNonfriends = new HashSet<>(nonfriendsC);
        allNonfriends.addAll(nonfriendsD);
        List<Integer> antiNumbers = new ArrayList<>(nonfriendsD);

        // Intersection of friends of C and D
        Set<Integer> friendsC = new HashSet<>(friends.get(C).get("friend"));
        Set<Integer> friendsD = new HashSet<>(friends.get(D).get("friend"));
        List<Integer> commonFriends = new ArrayList<>(friendsC);
        commonFriends.retainAll(friendsD);
        commonFriends.removeAll(allNonfriends);

        // Common neutral numbers
        Set<Integer> allNumbers = new HashSet<>();
        for (int i = 1; i <= 9; i++) {
            allNumbers.add(i);
        }
        List<Integer> commonNeutral = new ArrayList<>(allNumbers);
        commonNeutral.removeAll(commonFriends);
        commonNeutral.removeAll(allNonfriends);

        // Prepare result for JSON
        Map<String, Object> result = new HashMap<>();
        result.put("commonFriends", commonFriends);
        result.put("commonNeutral", commonNeutral);
        result.put("allNonfriends", allNonfriends);
        result.put("antiNumbers", antiNumbers);

        return result;
    }

    private static Map<String, List<Integer>> createFriendMap(List<Integer> friends, List<Integer> nonFriends, List<Integer> neutral) {
        Map<String, List<Integer>> friendMap = new HashMap<>();
        friendMap.put("friend", friends);
        friendMap.put("NonFriends", nonFriends);
        friendMap.put("Neutral", neutral);
        return friendMap;
    }

}
