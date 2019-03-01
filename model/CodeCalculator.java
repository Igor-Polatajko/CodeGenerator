package model;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class CodeCalculator {
    public Map<String, Object> calculate(String phase, String key) {
        Map<String, Object> result = new TreeMap<>();

        if (phase.length() < 3 || phase.length() > 11) {
            String code = "phase/key length should be between 3 and 11";
            result.put("Code", code);
            result.put("IsError", true);
            return result;
        }

        if (phase.length() != key.length()) {
            String code = "phase and key length must be equals";
            result.put("Code", code);
            result.put("IsError", true);
            return result;
        }

        if (!isKeyValid(key)) {
            String code = "key can contains only numbers";
            result.put("Code", code);
            result.put("IsError", true);
            return result;
        }

        try {
            String codeString = numbersArrayToString(calculateCode(phase, key), phase.length());
            result.put("Code", codeString);
            result.put("IsError", false);
            return result;
        }
        catch (IllegalArgumentException iae) {
            String code = "key is not valid";
            result.put("Code", code);
            result.put("IsError", true);
            return result;
        }

    }

    private int[] calculateCode(String phase, String key) {

        int[] phaseArray = numbersStringToArray(phase);
        int[] keyArray = numbersStringToArray(key);

        int mavVal = getMax(phaseArray);
        int codeLength = getCodeLength(mavVal, key);

        int[] codeArray = new int[codeLength + phase.length()];
        int[] proveArray = new int[phase.length()];


        for (int i = 0; i < codeLength + proveArray.length; i++) {

            if (i < phaseArray.length) {
                codeArray[i] = phaseArray[i];
            }
            else {
                int num = 0;

                for (int j = keyArray.length; j > 0; j--) {
                    num += codeArray[i - j] * keyArray[keyArray.length - j];
                }

                num = num % mavVal;


                if (i >= codeLength) {
                    proveArray[i - codeLength] = num;
                }

                codeArray[i] = num;
            }
        }

        if (!Arrays.equals(phaseArray, proveArray)) {
            throw new IllegalArgumentException();
        }

        return codeArray;
    }

    private boolean isKeyValid(String key) {
        boolean isKeyValid = true;
        boolean notZero = false;

        for (int i = 0; i < key.length(); i++) {
            try {
                if (Integer.parseInt("" + key.charAt(i)) != 0) {
                    notZero = true;
                }
            }
            catch (Exception e) {
                isKeyValid = false;
            }
        }

        return isKeyValid && notZero;
    }

    private int[] numbersStringToArray(String string) {
        int[] numbersArray = new int[string.length()];

        for (int i = 0; i < string.length(); i++) {
            numbersArray[i] = Integer.parseInt("" + string.charAt(i));
        }

        return numbersArray;
    }

    private String numbersArrayToString(int[] numbersArray, int phaseLength) {
        StringBuffer strBuff = new StringBuffer();

        for (int num : numbersArray) {
            strBuff.append(num);
        }


        return strBuff.toString().substring(0, strBuff.length() - phaseLength);
    }

    private int getMax(int[] numbersArray) {
        int max = 0;

        for (int i = 0; i < numbersArray.length; i++) {
            if (max < numbersArray[i]) {
                max = numbersArray[i];
            }
        }

        return max + 1;
    }

    private int getCodeLength(int maxVal, String key) {
        return (int) Math.pow(maxVal, key.length()) - 1;
    }
}

