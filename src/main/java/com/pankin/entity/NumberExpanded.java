package com.pankin.entity;

/*
Class implementation running example NumberExpanded
 */

public class NumberExpanded implements Calculations {

    private StringBuilder data;

    public NumberExpanded(String data) {
        this.data = new StringBuilder(data.subSequence(0, data.length()));
    }

    public StringBuilder getData() {
        return data;
    }

    public void setData(StringBuilder data) {
        this.data = data;
    }

    @Override
    public StringBuilder run() {
        long number;
        String numberStr;
        StringBuilder expandedNumber = new StringBuilder();
        try {
            number = Long.parseLong(data.toString().trim());
            numberStr = String.valueOf(number);
        } catch (NumberFormatException ex) {
            return new StringBuilder("Некорректное число");
        }

        if (number > 0) {
            for (int i = 0; i < numberStr.length(); i++) {
                if (!(numberStr.charAt(i) == '0')) {

                    expandedNumber.append(numberStr.charAt(i));
                    for (int j = numberStr.length() - i - 1; j > 0; j--) {
                        expandedNumber.append("0");
                    }
                    //Check end
                    if (!(numberStr.length() - 1 == i)) {
                        if (!(numberStr.charAt(i + 1) == '0'))expandedNumber.append(" + ");
                    }
                }
            }
            return expandedNumber;
        } else {
            return new StringBuilder("Число равно 0 или отрицательное");
        }
    }
}
