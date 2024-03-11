package com.rank;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateExamples {

    public static void main(String[] args) {
        //String dob = "06/04/1989 10:25:34";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy MM:HH:SS");
        /*Date date = new Date(dob);
        System.out.println("date::"+date);*/
        Date dateNow = new Date();

        System.out.println("Date Now::"+simpleDateFormat.format(dateNow));

    }
}
