package com.qa.utils;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    @SneakyThrows
    public String readUrl(){
        Properties pr = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/env/dev.properties");
        if(System.getProperty("env") == "dev" || System.getProperty("env") == null){
            pr.load(fis);

        }
       return pr.getProperty("url");
    }
}
