package com.spring.model.aspects;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class MyLogger {

    public  static void log(Exception e, Object object, String message) {
        Date date = new Date();
        String string = date.toString();
        string = string.substring(0, 10);
        String res = string.replaceAll(" ", ".");
        File file = new File("webapps/spring.com/fileLog" + res + ".txt");
        try {
            FileUtils.writeStringToFile
                    (file, date.toString() + " " + message + "\n" + object.getClass().toString() + "\n"
                            + e.getLocalizedMessage() + "\n", true);
        } catch (IOException ex) {
            System.out.println();
        }
    }
}
