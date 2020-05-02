package com.spring.model.aspects;

import org.apache.commons.io.FileUtils;
import org.aspectj.lang.JoinPoint;

import java.io.File;
import java.io.IOException;


public class FirstAspect {
    private String fileName;
    private File file;

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public  String getFileName() {
        return fileName;
    }

    public void writeData(JoinPoint joinPoint) throws IOException {
        Object[] objects = joinPoint.getArgs();
        FileUtils.writeStringToFile
                (file, "search price is " + objects[3].toString() + " search sits is " + objects[4] +
                         "\n", true);
    }

    public void log(JoinPoint joinPoint) throws IOException{
        Object[] objects = joinPoint.getArgs();
        FileUtils.writeStringToFile
                (file, "logging"+
                        "\n", true);
    }

    public void afterThrowing(JoinPoint joinPoint,Throwable e) throws IOException {
        Object[] objects = joinPoint.getArgs();
        FileUtils.writeStringToFile
                (file, e.toString()+"error"+
                        "\n", true);
    }

    public void init() {
        this.file = new File(fileName);
    }

    public void destroy() throws IOException {
        FileUtils.writeStringToFile
                (file, "destroy" + "\n", true);
    }

}
