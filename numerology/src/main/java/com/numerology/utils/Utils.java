package com.numerology.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
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
        	System.out.println(number);
        	System.out.println(sum);
        	
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
    

    public static Map<String, Object> getDCAffect(int D, int C) {
        Map<List<Integer>, Object[]> numerologyData = new HashMap<>();

        numerologyData.put(Arrays.asList(1, 1), new Object[]{4, "Fortunes Favorite"});
        numerologyData.put(Arrays.asList(1, 2), new Object[]{4, "Best for Navy (2 represents water, moon)"});
        numerologyData.put(Arrays.asList(1, 3), new Object[]{3.5, "Best for Occult"});
        numerologyData.put(Arrays.asList(1, 4), new Object[]{3, "Politics (1 Sun (King) & 4 – Rahu)"});
        numerologyData.put(Arrays.asList(1, 5), new Object[]{4, "Banking & Finance"});
        numerologyData.put(Arrays.asList(1, 6), new Object[]{3.5, "Luxury - Glamour"});
        numerologyData.put(Arrays.asList(1, 7), new Object[]{3, "Best for Occult, Education, Research"});
        numerologyData.put(Arrays.asList(1, 8), new Object[]{"?", "Struggle / Marriage? / Police / Politics"});
        numerologyData.put(Arrays.asList(1, 9), new Object[]{5, "Super Successful"});
        
        numerologyData.put(Arrays.asList(2, 1), new Object[]{3.5, "Successful"});
        numerologyData.put(Arrays.asList(2, 2), new Object[]{2, "Best Water Related Works/ Navy / Sweets / Cold Drinks"});
        numerologyData.put(Arrays.asList(2, 3), new Object[]{2.5, "Occult Education / Healer / Teacher"});
        numerologyData.put(Arrays.asList(2, 4), new Object[]{1.5, "Struggle / Depression"});
        numerologyData.put(Arrays.asList(2, 5), new Object[]{3, "Best for Property/ Real Estate / Finance MBA/ Banking"});
        numerologyData.put(Arrays.asList(2, 6), new Object[]{2.5, "Best for Sweets (2 is (Moon) water & 6 is (Venus) celebration"});
        numerologyData.put(Arrays.asList(2, 7), new Object[]{2.5, "Teaching / Occult"});
        numerologyData.put(Arrays.asList(2, 8), new Object[]{"?", "Struggle / Health Issues"});
        numerologyData.put(Arrays.asList(2, 9), new Object[]{1, "Marriage problems"});

        numerologyData.put(Arrays.asList(3, 1), new Object[]{3.5, "Occult/ Education / Healer / Doctor / Administrative jobs"});
        numerologyData.put(Arrays.asList(3, 2), new Object[]{2.5, "Water related work Navy"});
        numerologyData.put(Arrays.asList(3, 3), new Object[]{3, "Best for Education / Occult"});
        numerologyData.put(Arrays.asList(3, 4), new Object[]{2, "Good for Sales & Marketing"});
        numerologyData.put(Arrays.asList(3, 5), new Object[]{3, "Excellent communication, Anchoring, News, Reading, Acting, Teaching, Banking"});
        numerologyData.put(Arrays.asList(3, 6), new Object[]{"?", "Marriage / Health : 3& 6 are anti to each other"});
        numerologyData.put(Arrays.asList(3, 7), new Object[]{4, "Best for Education / Occult / Healing / Teaching"});
        numerologyData.put(Arrays.asList(3, 8), new Object[]{2, "Lawyer , Printing, Sales"});
        numerologyData.put(Arrays.asList(3, 9), new Object[]{4, "Education /Occult/ Army/ Administrative/ Doctor"});

        numerologyData.put(Arrays.asList(4, 1), new Object[]{3.5, "Politics"});
        numerologyData.put(Arrays.asList(4, 2), new Object[]{2, "Depression / Struggle"});
        numerologyData.put(Arrays.asList(4, 3), new Object[]{2.5, "Sales / Marketing / Occult Education"});
        numerologyData.put(Arrays.asList(4, 4), new Object[]{1.5, "Best for Law / Struggle"});
        numerologyData.put(Arrays.asList(4, 5), new Object[]{3, "Banking/ Event Management"});
        numerologyData.put(Arrays.asList(4, 6), new Object[]{3, "Media/ Luxury/ Glamour"});
        numerologyData.put(Arrays.asList(4, 7), new Object[]{4, "Successful/ Best in Occult"});
        numerologyData.put(Arrays.asList(4, 8), new Object[]{1, "Struggle / Excellent for Law"});
        numerologyData.put(Arrays.asList(4, 9), new Object[]{1, "Struggle/ Health problems/ Surgeries/ Accidents"});

        numerologyData.put(Arrays.asList(5, 1), new Object[]{4, "Successful/ Finance/ Loan/ Property have Balanced Life"});
        numerologyData.put(Arrays.asList(5, 2), new Object[]{3.5, "Property / Agriculture"});
        numerologyData.put(Arrays.asList(5, 3), new Object[]{3, "Communication / Occult"});
        numerologyData.put(Arrays.asList(5, 4), new Object[]{3, "Overall Success / Sales Marketing"});
        numerologyData.put(Arrays.asList(5, 5), new Object[]{4, "Very Successful / Romantic / May be Lazy"});
        numerologyData.put(Arrays.asList(5, 6), new Object[]{4.5, "Life is successful Luxury / Media/ Sitting Jobs"});
        numerologyData.put(Arrays.asList(5, 7), new Object[]{3, "Occult & Banking"});
        numerologyData.put(Arrays.asList(5, 8), new Object[]{3, "Property / Law / Printing"});
        numerologyData.put(Arrays.asList(5, 9), new Object[]{3, "Successful/ Law/ doctor/ Food/ cooking"});

        numerologyData.put(Arrays.asList(6, 1), new Object[]{3.5, "Media/ Luxury/ Glamour"});
        numerologyData.put(Arrays.asList(6, 2), new Object[]{2, "Sweet Shop/ liquid"});
        numerologyData.put(Arrays.asList(6, 3), new Object[]{"(-)?", "Health & marriage issues"});
        numerologyData.put(Arrays.asList(6, 4), new Object[]{3, "Successful / Media"});
        numerologyData.put(Arrays.asList(6, 5), new Object[]{4.5, "Super successful"});
        numerologyData.put(Arrays.asList(6, 6), new Object[]{4, "Super successful, Media/ Film industry/ Tours & travels"});
        numerologyData.put(Arrays.asList(6, 7), new Object[]{3.5, "Successful, Sports, Romantic"});
        numerologyData.put(Arrays.asList(6, 8), new Object[]{3, "Best for Law"});
        numerologyData.put(Arrays.asList(6, 9), new Object[]{3, "Successful but Marriage problems, Scandal Controversies"});

        numerologyData.put(Arrays.asList(7, 1), new Object[]{3, "Best Occult"});
        numerologyData.put(Arrays.asList(7, 2), new Object[]{2, "Intuitive/ Occult"});
        numerologyData.put(Arrays.asList(7, 3), new Object[]{3, "Teaching/ Healing/ Occult"});
        numerologyData.put(Arrays.asList(7, 4), new Object[]{3, "Successful/ Law/ Occult"});
        numerologyData.put(Arrays.asList(7, 5), new Object[]{3, "Occult/ Deskwork"});
        numerologyData.put(Arrays.asList(7, 6), new Object[]{4, "Sports/ Luxury"});
        numerologyData.put(Arrays.asList(7, 7), new Object[]{1, "Disappointment in Life/ Marriage life in Danger"});
        numerologyData.put(Arrays.asList(7, 8), new Object[]{1, "Occult/ Teaching"});
        numerologyData.put(Arrays.asList(7, 9), new Object[]{1, "Teaching/ Occult/ research/ Law"});

        numerologyData.put(Arrays.asList(8, 1), new Object[]{"?", "Marriage Problems/ Struggle"});
        numerologyData.put(Arrays.asList(8, 2), new Object[]{"?", "Health issues/ Struggle"});
        numerologyData.put(Arrays.asList(8, 3), new Object[]{2, "Law/ Printing"});
        numerologyData.put(Arrays.asList(8, 4), new Object[]{1, "Best for Law, Sales & marketing"});
        numerologyData.put(Arrays.asList(8, 5), new Object[]{3, "Real estate, Property"});
        numerologyData.put(Arrays.asList(8, 6), new Object[]{3, "Best for Law"});
        numerologyData.put(Arrays.asList(8, 7), new Object[]{2, "Occult/ Leather Business"});
        numerologyData.put(Arrays.asList(8, 8), new Object[]{1, "Struggle but good in Sports"});
        numerologyData.put(Arrays.asList(8, 9), new Object[]{1, "Army/ Doctor/ Navy/ Law/ Leather business"});

        numerologyData.put(Arrays.asList(9, 1), new Object[]{3, "Successful/ Army is Best"});
        numerologyData.put(Arrays.asList(9, 2), new Object[]{1, "Struggle/ Marriage problem"});
        numerologyData.put(Arrays.asList(9, 3), new Object[]{2.5, "Occult/ Healing"});
        numerologyData.put(Arrays.asList(9, 4), new Object[]{1.5, "Struggle/ Surgeries/ health Issues"});
        numerologyData.put(Arrays.asList(9, 5), new Object[]{3, "Successful/ Law/ desk work"});
        numerologyData.put(Arrays.asList(9, 6), new Object[]{2, "Scandals/ Controversies"});
        numerologyData.put(Arrays.asList(9, 7), new Object[]{1, "Occult/ teaching"});
        numerologyData.put(Arrays.asList(9, 8), new Object[]{2, "Army/ police/ navy"});
        numerologyData.put(Arrays.asList(9, 9), new Object[]{1, "Marriage problem/ Anger"});

        // ... Add the rest of the mappings

        Map<String, Object> result = new HashMap<>();
        Object[] data = numerologyData.getOrDefault(Arrays.asList(D, C), new Object[]{"N/A", "No data available"});

        // Adding the results to a map instead of a document
        result.put("D-C Relation", D + "-" + C);
        result.put("Star Rating", data[0]);
        result.put("Affect", data[1]);

        return result;
    }
    
    public static Map<String, String> getDcCharacteristic(int D, int C) {
        Map<Integer, String> characteristics = Map.of(
            1, "Communicator Good / Bad",
            2, "Intuitive Sensitive",
            3, "Creative, Imaginative",
            4, "Discipline, Organized & organizational Skills",
            5, "Balance, Finance, Health, Family, Relation, work",
            6, "Home & family relationships Association / Love / luxury",
            7, "Disappointments / Spiritual / Occult",
            8, "Money Finance Management, Only management not earning",
            9, "Humanitarian, Social connect"
        );

        Map<String, String> result = new HashMap<>();
        result.put("Characteristic of D-" + D, characteristics.getOrDefault(D, "No data available"));
        result.put("Characteristic of C-" + C, characteristics.getOrDefault(C, "No data available"));

        return result;
        
    }
    
    private static final Map<Integer, Map<String, String>> DCcharacteristics = Map.of(
            1, Map.of("Description", "Communicator Good / Bad"),
            2, Map.of("Description", "Intuitive Sensitive"),
            3, Map.of("Description", "Creative, Imaginative"),
            4, Map.of("Description", "Discipline, Organized & organizational Skills"),
            5, Map.of("Description", "Balance, Finance, Health, Family, Relation, work"),
            6, Map.of("Description", "Home & family relationships Association / Love / luxury"),
            7, Map.of("Description", "Disappointments / Spiritual / Occult"),
            8, Map.of("Description", "Money Finance Management, Only management not earning"),
            9, Map.of("Description", "Humanitarian, Social connect")
    );

    
    private static final Map<Integer, Map<Integer, String>> relationsTable = Map.of(
            1, Map.of(
                0, "Very Difficult to express themselves. Child may start talking very late. Egoless, easy to mix with others. Support others, Low esteem. May stammer at early age",
                1, "May be good Communicator But find it hard to allow inner self to come out",
                2, "Excellent Communicator / articulate, impartial",
                3, "Chatter box, Diplomatic / introvert depending on the situation",
                4, "Expressive, Misunderstood in society",
                5, "May be communicator/ surrounded with scandals / controversies. Definitely Big Name & Fame in Society"
            ),
            2, Map.of(
                0, "Lack sensitivity and intuition. May not care for others feelings. Might pass own faults on others. Do not believe their intuitions. May not admit own faults",
                1, "Sensitive & Intuitive But May not use it",
                2, "Highly Intelligent, Sensitive & Intuitive",
                3, "Over Sensitive, easily hurt, Avoid big gatherings",
                4, "Impatient, Extremely sensitive Many face depression in life If driver is 2,4,8,9 more sensitive be careful in handling them"
            ),
            3, Map.of(
                0, "Low creativity, imagination. Surrender easily. May choose wrong profession usually",
                1, "Good & creative Mind",
                2, "Good Imagination, Good Communication, May be eccentric & Enjoy flaunting rules [Good starter Bad finishers]",
                3, "Over Imagination May Not Connect well with Society. Argumentative",
                4, "Impractical, Fearful and Lives in fool’s paradise"
            ),
            4, Map.of(
                0, "Not disciplined, not organized, not punctual, do not value time. Three plane Thought, Mental & Raj yog are broken. Consistent struggle",
                1, "Practical and Hard working, Punctual. Enjoy hands on activities, work with hands artists, handicrafts, chef etc.",
                2, "Excellent Organizational Skills. Create Order in anything. More impatient can annoy other. Penny wise pound foolish",
                3, "Struggle and cannot identify their true potential. Waste their time in wrong fields.",
                4, "Aggressive behavior. Destructive Negative outputs."
            ),
            5, Map.of(
                0, "Lack balance in every sphere. Lack of drive and versatility to get their goals",
                1, "Emotionally well balanced, Self Responsible & accountable, Lazy",
                2, "Highly Romantic, Always Young at Heart, Intense, Determined, Lazy, Always Motivate & Inspire Others. Do not like interference in own work Gain weight after 32 yrs Usually on higher side.",
                3, "Always keep resignation in pocket Cannot Tolerate interference in their life. Do not trust easily others",
                4, "Exaggerate things, Speak unwittingly, Over adventurous & prone to accidents. Job / wk shifting very frequently. Cannot stick with one thing"
            ),
            6, Map.of(
                0, "Poor bonding, Family members do not respect them. Husband & wife both missing 6 will not be close. If 5 & 4 also missing it's even bad. Will not get societal support. [Make arrangements for own retirement - No one will be there to help you] Can face marriage problems. Disturbed relationship including with parents, siblings, wife, children...",
                1, "Have great love for their home and loved once. Centre of attraction in the family (over) protective towards family.",
                2, "Worried about and obsessed with their kids and loved once. Sense of insecurity. They think they only can identify what is good for family / kids.",
                3, "Always look at the negative side of life and become hindrance in the progress of their kids. Too much involved family members affairs they will decide every thing"
            ),
            7, Map.of(
                0, "Lack of spirituality religion occult education. May not care for the feelings of others. Might lack good/required education/right/wanted education",
                1, "Disappointment in love, health, money and emotional setback.",
                2, "Highly disappointed in love, health and money. Frequently deceived by own. Spiritualism & occult can solve intricate problems",
                3, "Marriage may be a problem. Broken hearts in Love. Attracted towards spiritualism, religion and occult",
                4, "Depression because relationship sector is a problem. Marriage & love issues. Partnership & business should be avoided (do not invest money). Money given will not come back. Relationships will be problem. Stick to occult/ spiritualism."
            ),
            8, Map.of(
                0, "Miss good financial management. Spend thrift.",
                1, "Able to manage their money and finance. Logical and argumentative.",
                2, "Do not trust others. Always rely on their own experience. Keep money in multiple pockets and multiple bank accts. (Do not believe/ listen in others stubborn)",
                3, "Extremely restless and have a strong need for change. Fail due to their attitude. Might not update themselves. Chances of default on loan repayment. Do not lend money to these people They are bad money managers. You might not get back your money."
            ),
            9, Map.of(
                0, "Overlook others feelings. Lack energy. Lack humanitarian feelings.",
                1, "Intelligence and Humanitarian.",
                2, "Intelligent and think that they are the best try to prove their point. Egoist",
                3, "Intelligent and tend to criticize / Insult others. Need to channelize their intelligence and energy (work out then +ve aspects come out). May not find reciprocation in love.",
                4, "Caught in a rut Highly egoistic, unpredictable, very angry, somersault.",
                5, "Think that they are the best and does not connect well in their day to day life with society (Do not marry double 9 persons Difficult in last)"
            )
    );

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

        
    public static List<Map<String, String>> outputBasedOnFreq(int[][] freq, int[][]matrix) {
//    	printIntMatrix(freq, "Meaning Matrix");
//      printIntMatrix(matrix, "Frequency Matrix");

        List<Map<String, String>> results = new ArrayList<>();
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (freq[i][j] != 0) {
                	Map<String, String> resultEntry = new HashMap<>();
                	resultEntry.put("Number", String.valueOf(matrix[i][j]));
                	resultEntry.put("Frequency", String.valueOf(freq[i][j]));
                	resultEntry.put("relation", relationsTable.getOrDefault(matrix[i][j], Map.of(0, "Nothing")).get(freq[i][j]));
                	results.add(resultEntry);
                }
            }
        }


        return results;
    }


        
        private static final Map<Integer, String> missingNumberDescriptions = Map.of(
                1, "Very Difficult to express themselves. Child may start talking very late. Egoless, easy to mix with others. Support others, Low esteem. May stammer at early age",
                2, "Lack sensitivity and intuition. May not care for others feelings. Might pass own faults on others. Do not believe their intuitions. May not admit own faults",
                3, "Low creativity, imagination. Surrender easily. May choose wrong profession usually.",
                4, "Not disciplined, not organized, not punctual, do not value time. Three plane Thought, Mental & Raj yog are broken. Consistent struggle",
                5, "Lack balance in every sphere. Lack of drive and versatility to get their goals",
                6, "Poor bonding, Family members do not respect them. Husband & wife both missing 6 will not be close if 5&4 also missing its even bad. Will not get societal support. [Make arrangements for own retirement- No one will be there to help you ] Can face marriage problems. Disturbed relationship including with parents , siblings, wife children ...",
                7, "Lack of spirituality, religion, occult education. May not care for the feelings of others. Might lack good/ required education /right/wanted education",
                8, "Miss good financial management. Spendthrift.",
                9, "Overlook others feelings. Lack energy. Lack humanitarian feelings."
            );

            private static final Map<Integer, Map<String, String>> complimentaryNumbers = Map.of(
                1, Map.of("Description", "Complimentary number: 9. Qualities of 1 present in 9 will compliment."),
                2, Map.of("Description", "5, 7."),
                3, Map.of("Description", "5, 7."),
                4, Map.of("Description", "8."),
                5, Map.of("Description", "No Complimentary number (Partially 6)."),
                6, Map.of("Description", "5 | Balance Materialistic support."),
                7, Map.of("Description", "3 | Spiritual, healing."),
                8, Map.of("Description", "4 | 4 – Discipline, 5 – Balance."),
                9, Map.of("Description", "1")
            );

            public Map<String, Object> missingNumber(int[][] meaningMatrix, int[][] frequencyMatrix) {
                Map<String, Object> result = new HashMap<>();
                List<Integer> missingNumbers = new ArrayList<>();

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (frequencyMatrix[i][j] == 0) {
                            missingNumbers.add(meaningMatrix[i][j]);
                        }
                    }
                }

                Collections.sort(missingNumbers);
                List<Map<String, String>> missingDetails = new ArrayList<>();

                for (int num : missingNumbers) {
                    Map<String, String> detail = new HashMap<>();
                    detail.put("Number", String.valueOf(num));
                    detail.put("Characteristic", DCcharacteristics
                            .getOrDefault(num, Map.of("Description", "Characteristic not found"))
                            .get("Description"));
                    detail.put("Affect", missingNumberDescriptions.get(num));
                    detail.put("Complimentary", complimentaryNumbers.get(num).get("Description"));
                    missingDetails.add(detail);
                }

                result.put("Missing Numbers", missingDetails);
                return result;
            }
            
            public static Map<String, Object> remedies(int[][] frequencyMatrix, int[][] meaningMatrix) {


                List<String> missElements = new ArrayList<>();
                List<Integer> missingNumbers = new ArrayList<>();

                // Identify missing numbers
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (frequencyMatrix[i][j] == 0) {
                            missingNumbers.add(meaningMatrix[i][j]);
                        }
                    }
                }

                Collections.sort(missingNumbers);

                @SuppressWarnings("serial")
				Map<Integer, String> numEleMapping = new HashMap<>() {{
                    put(1, "Water");
                    put(2, "Earth");
                    put(3, "Wood");
                    put(4, "Wood");
                    put(5, "Earth");
                    put(6, "Metal Gold");
                    put(7, "Metal Silver");
                    put(8, "Earth");
                    put(9, "Fire");
                }};

                for (Integer num : missingNumbers) {
                    String element = numEleMapping.getOrDefault(num, "Element not found");
                    if (!missElements.contains(element)) {
                        missElements.add(element);
                    }
                }

                @SuppressWarnings("serial")
				Map<String, String> elementDict = new HashMap<>() {{
                    put("Wood", "Rudraksh");
                    put("Fire", "Red Thread");
                    put("Water", "Carry Water, Keep on Workplace");
                    put("Metal Silver", "Silver Colour Watch");
                    put("Metal Gold", "Watch Golden Colour");
                    put("Earth", "White Quartz, Spatik, Crystal");
                }};

                // Prepare the results
                Map<String, Object> result = new HashMap<>();
                List<Map<String, String>> remediesList = new ArrayList<>();

                for (String element : missElements) {
                    String remedy = elementDict.getOrDefault(element, "Remedy not found");
                    Map<String, String> remedyEntry = new HashMap<>();
                    remedyEntry.put("element", element);
                    remedyEntry.put("remedy", remedy);
                    remediesList.add(remedyEntry);
                }

                result.put("missingElements", missElements);
                result.put("remedies", remediesList);
                return result;
            }
            
            private static final Map<Integer, Map<String, String>> numeroVastu = new HashMap<>();

            static {
                numeroVastu.put(1, Map.of(
                        "Success", "South-East",
                        "Health", "East",
                        "Family", "South",
                        "Personal Development", "North"
                ));
                numeroVastu.put(2, Map.of(
                        "Success", "North-East",
                        "Health", "West",
                        "Family", "North-West",
                        "Personal Development", "South-West"
                ));
                numeroVastu.put(3, Map.of(
                        "Success", "South",
                        "Health", "North",
                        "Family", "South-East",
                        "Personal Development", "East"
                ));
                numeroVastu.put(4, Map.of(
                        "Success", "North",
                        "Health", "South",
                        "Family", "East",
                        "Personal Development", "South-East"
                ));
                numeroVastu.put(5, Map.of(
                        "Success", "North-West / South-West",
                        "Health", "West / North-West",
                        "Family", "North-West / West",
                        "Personal Development", "South-West / North-East"
                ));
                numeroVastu.put(6, Map.of(
                        "Success", "West",
                        "Health", "North-East",
                        "Family", "South-West",
                        "Personal Development", "North-West"
                ));
                numeroVastu.put(7, Map.of(
                        "Success", "North-West",
                        "Health", "West",
                        "Family", "North-East",
                        "Personal Development", "South-West"
                ));
                numeroVastu.put(8, Map.of(
                        "Success", "South-West",
                        "Health", "North-West",
                        "Family", "West",
                        "Personal Development", "North-East"
                ));
                numeroVastu.put(9, Map.of(
                        "Success", "East",
                        "Health", "South-East",
                        "Family", "North",
                        "Personal Development", "South"
                ));
            }

            // Function to display directions based on the Kua number
            public static Map<String, Object> getDirections(int kuaNumber) {
                Map<String, Object> result = new HashMap<>();
                result.put("Heading", "Numero Directions");

                // Retrieve directions for the given Kua number
                Map<String, String> directions = numeroVastu.get(kuaNumber);

                // Add Kua number and directions to the result
                result.put("Kua", kuaNumber);
                if (directions != null) {
                    Map<String, String> attributesAndDirections = new HashMap<>();
                    for (Map.Entry<String, String> entry : directions.entrySet()) {
                        attributesAndDirections.put(entry.getKey(), entry.getValue());
                    }
                    result.put("Attributes", attributesAndDirections);
                }

                return result;
            }
            
            
            public static Map<String, Object> personalYearEffect() {
                Map<String, Object> result = new HashMap<>();
                result.put("Title", "Personal Years and their effects are tabulated");

                // Define the table headers
                String[] headers = {"Per Yr", "Effect"};

                // Define the content
                String[][] content = {
                        {"1", "Blessing Yr. Seed in Sown. Sun yr. Start Point for the cycle. Year of Execution not planning. Enthusiasm and Energy drive greater results in all works started"},
                        {"2", "Roots Start Growing. Moon yr. Slow down and go gentle. Year of consolidation, demands patience. Analyze & consolidate past and present issues. Plan & Organize. Yr of partnership except with 4, 8 & 9."},
                        {"3", "Sprouting starts. Jupiter yr. Yr of Knowledge & education, spiritual pursuits. Focus on upgrading self by education. Yr of expansion. Business linked with Education may flourish. 3 is Creative-socialize, travel, plan for kids etc"},
                        {"4", "Plant strengthening yr. Rahu yr. Testing Yr. Only true & sincere hard wk will give you success. Do not get diverted by miss leading thoughts. Have a goal and pursue it else yr will get wasted."},
                        {"5", "Blessing Yr. Enjoy the flowers of Plant. Year of success. Good for all, expect un expected. Auspicious for change / variety including a career shift."},
                        {"6", "Blessing year. Enjoy the fruits. Yr of luxury, home & Family. Get married If relation is bad might get a divorce. New home vehicle, birth of a child, get married."},
                        {"7", "No risk yr. Res and introspect in life. Learn new skills, Better to be religious / god fearing. Can get sudden disappointment. DO NOT START ANYTHING NEW."},
                        {"8", "Judgment year Karmic yr. Reap the fruits of your karma / hard wk. Usually a balanced yr. No exceptional or unexpected results / events."},
                        {"9", "Completion of cycle. Abusive yr. Face off yr true colours of self are revealed. Unpredictable yr. Can be good or very bad. Prepare for next blessing year (1)."}
                };

                // Add headers and content to the result
                result.put("Headers", headers);
                result.put("Content", content);

                return result;
            }
            
            
            public static Map<String, Object> calculatePersonalYear(Date dob, int predictionYear) {
                // Create a Calendar instance with the given date of birth
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dob);
                
                // Get the day, month, and use predictionYear directly
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH) + 1; // Calendar.MONTH is zero-based
                int total = sumDigits(day, month, predictionYear);
                int totSum = total;

                // Reduce to a single digit if necessary
                while (total >= 10) {
                    total = sumDigits(total);
                }

                // Create a result map and return it
                Map<String, Object> result = new HashMap<>();
                result.put("personalYear", total);
                result.put("totalSum", totSum);
                return result;
            }


            public static Map<String, Object> calculatePersonalMonth(Date dob, int year, int month) {
            	int pyn = (int) calculatePersonalYear(dob, year).get("personalYear");
                // Personal month is calculated as PYN + (Month Number)
                int total = pyn + month;
                int totSum = total;

                while (total >= 10) {
                    total = sumDigits(total);
                }

                Map<String, Object> result = new HashMap<>();
                result.put("personalMonth", total);
                result.put("totalSum", totSum);
                return result;
            }
            public static Map<String, Object> _calculatePersonalMonth(Date dob, int pyn, int month) {
                // Personal month is calculated as PYN + (Month Number)
                int total = pyn + month;
                int totSum = total;

                while (total >= 10) {
                    total = sumDigits(total);
                }

                Map<String, Object> result = new HashMap<>();
                result.put("personalMonth", total);
                result.put("totalSum", totSum);
                return result;
            }



            // Create a table with personal year and personal month numbers for 10 years and 12 months
            @SuppressWarnings("unchecked")
			public static String[][] createPersonalYearTable(int start_year, Date dob, int D, int C, int K) {
                // Call getRelationships to get values for anti, nonFriendsC, friendly
                Map<String, Object> relationships = getRelationships(C, D);
                List<Integer> anti = (List<Integer>) relationships.get("antiNumbers");
                List<Integer> nonFriendsC = new ArrayList<>((Set<Integer>) relationships.get("allNonfriends"));
//                List<Integer> friendly = (List<Integer>) relationships.get("commonFriends");

                String[][] table = new String[10][12];  // 12 rows (months) and 10 columns (years)
                Calendar calendar = Calendar.getInstance();
                calendar.set(start_year, 1, 1);
                String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
                List<Integer> orange = new ArrayList<>(nonFriendsC);
                orange.removeAll(anti);

                // Fill in the table for each year and month
                for (int i = 0; i < 10; i++) {
                    int predictionYear = start_year + i;

                    // Calculate personal year for the current iteration year
                    Map<String, Object> personalYear = calculatePersonalYear(dob, predictionYear);
                    int pyn = (int) personalYear.get("personalYear");

                    for (int j = 0; j < 12; j++) {
                        // Calculate personal month
                        Map<String, Object> personalMonth = _calculatePersonalMonth(dob, pyn, j + 1);
                        String personalMonthStr = personalMonth.get("personalMonth") + "/" + personalMonth.get("totalSum");
                        table[i][j] = personalMonthStr;
                    }
                }

                return table;
            }




            private static int sumDigits(int... numbers) {
                int sum = 0;
                for (int number : numbers) {
                    sum += String.valueOf(number).chars().map(Character::getNumericValue).sum();
                }
                return sum;
            }
            
            
            @SuppressWarnings("serial")
			private static final Map<Integer, String> professionDict = new HashMap<>() {{
                put(1, "Fire: Authority, Energy, Power position, Leader.");
                put(2, "Water/ Liquid related.");
                put(3, "Knowledge, Education, Communication, creation.");
                put(4, "Street smart, Intelligent wks, logical reasoning, analytical, mind related wks. Include IT jobs.");
                put(5, "Sitting jobs, Finance related, more mental wk than physical Eg real estate.");
                put(6, "Luxury, Glamour, High end products and services.");
                put(7, "Occult, Spiritual, Deep research jobs.");
                put(8, "Metal related wks. Law, Financial Management, Physical effort needed. Black color related, Leather. Discipline, Need patience.");
                put(9, "High energy, Physical work and aggression.");
            }};

            public static Map<String, Object> getProfession(int D, int C) {
                Map<String, Object> result = new HashMap<>();

                String dProfession = professionDict.getOrDefault(D, "Not Found");
                String cProfession = professionDict.getOrDefault(C, "Not Found");

                List<Map<String, String>> professions = new ArrayList<>();
                Map<String, String> dProfessionMap = new HashMap<>();
                dProfessionMap.put("id", String.valueOf(D));
                dProfessionMap.put("description", dProfession);
                professions.add(dProfessionMap);

                Map<String, String> cProfessionMap = new HashMap<>();
                cProfessionMap.put("id", String.valueOf(C));
                cProfessionMap.put("description", cProfession);
                professions.add(cProfessionMap);

                result.put("professions", professions);
                return result;
            }
            
            
			@SuppressWarnings("serial")
			private static final Map<Character, Integer> charToValue = new HashMap<>() {{
                put('A', 1); put('B', 2); put('C', 3); put('D', 4); put('E', 5);
                put('F', 8); put('G', 3); put('H', 5); put('I', 1); put('J', 1);
                put('K', 2); put('L', 3); put('M', 4); put('N', 5); put('O', 7);
                put('P', 8); put('Q', 1); put('R', 2); put('S', 3); put('T', 4);
                put('U', 6); put('V', 6); put('W', 6); put('X', 5); put('Y', 1);
                put('Z', 7); put(' ', 0);
            }};

//            private static int reduceToSingleDigit(int number) {
//                while (number > 9) {
//                    number = String.valueOf(number).chars().map(Character::getNumericValue).sum();
//                }
//                return number;
//            }

            public static Map<String, Object> calculateNameSum(String name) {
                name = name.toUpperCase();
                int totalSum = 0;

                // Prepare the results
                Map<String, Object> result = new HashMap<>();
                List<Map<String, String>> nameValues = new ArrayList<>();

                for (char charValue : name.toCharArray()) {
                    Map<String, String> valueMap = new HashMap<>();
                    valueMap.put("character", String.valueOf(charValue));

                    // Get corresponding value from charToValue dictionary
                    int value = charToValue.getOrDefault(charValue, 0);
                    valueMap.put("value", value == 0 ? "" : String.valueOf(value));

                    // Add to the total sum
                    totalSum += value;
                    nameValues.add(valueMap);
                }

                result.put("nameValues", nameValues);
                result.put("totalSum", totalSum);
                result.put("singleDigitSum", reduceToSingleDigit(totalSum));
                return result;
            }
            
            
            

}
