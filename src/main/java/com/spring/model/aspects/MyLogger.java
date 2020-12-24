package com.spring.model.aspects;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class MyLogger {

    public static void log(Kind kind, Object errorClass, String message) {
        Date date = new Date();
        String string = date.toString();
        string = string.substring(0, 10);
        String res = string.replaceAll(" ", ".");
        File file = new File("webapps/com.company-1.0-SNAPSHOT/log/myLogger " + res + ".txt");
        try {
            FileUtils.writeStringToFile
                    (file, kind + " --" + date.toString() + " " + message + " " + errorClass.getClass().toString() + " "
                            + "\n", true);
        } catch (IOException ex) {
            System.out.println();
        }
    }

        public enum Kind {
            INFO, WARNING, FATAL
        }
    }
