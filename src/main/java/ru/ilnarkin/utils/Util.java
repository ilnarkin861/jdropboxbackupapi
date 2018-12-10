package ru.ilnarkin.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    public static String getZipFileName(){

        String newstring = new SimpleDateFormat("dd-MM-yyyy_hh:mm:ss").format(new Date());
        return  newstring.concat(".zip");
    }
}
