package com.pankin.entity;


import java.util.*;

/*
Class implementation running example lexic order in array.
 */
public class LexicOrderArray implements Calculations {

    private StringBuilder data;


    public LexicOrderArray(String data) {
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
        StringBuilder stringResult = new StringBuilder();

        //split field enter of arrays a1 and a2
        String[] arrayStrBuf = data.toString().split("\n");
        String[] a1 = arrayStrBuf[0].split(",");
        String[] a2 = arrayStrBuf[1].split(",");

        //delete duplicate in array a1
        Set<String> a1NoDuplicate = new HashSet<>(Arrays.asList(a1));
        a1 = a1NoDuplicate.toArray(new String[a1NoDuplicate.size()]);

        //trim arrays a1 and a2
        for (int i = 0; i < a1.length; i++) {
            a1[i] = a1[i].trim();
        }
        for (int i = 0; i < a2.length; i++) {
            a2[i] = a2[i].trim();
        }

        //find coincidence substring a1 in string a2
        List<String> listResult = new ArrayList<>();
        for (int i = 0; i < a1.length; i++) {
            for (int j = 0; j < a2.length; j++) {
                if (a2[j].contains(a1[i])) {
                    listResult.add(a1[i]);

                    break;
                }
            }
        }

        //Arraylist sort of lexico
        Collections.sort(listResult);

        //ArrayList convert in StringBuilder
        for (int i = 0; i < listResult.size(); i++) {
            stringResult.append(listResult.get(i));
            if (!(i == listResult.size() - 1)) stringResult.append(", ");
        }
        if (listResult.isEmpty()) stringResult.append("Совпадений не найдено");
        return stringResult;
    }
}
