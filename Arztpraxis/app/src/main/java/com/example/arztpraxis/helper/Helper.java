package com.example.arztpraxis.helper;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Helper {
     public static String formatDateTime(Date date,boolean returnTime){
        SimpleDateFormat sdf;
        if (returnTime){
             sdf=new SimpleDateFormat("HH:mm:ss");
        }else{
            sdf= new SimpleDateFormat("dd.MM.yyyy");
        }
        return sdf.format(date);
    }

    public static String getGender(String s){
         switch (s){
             case "m":
                 return "male";
             case "w":
                 return "female";
             case "male":
                 return "m";
             case "female":
                 return  "w";
             case "other":
                 return "d";
             default:
                 return "other";
         }
    }
}
