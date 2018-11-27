package com.productivity.lucene.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class ApplicationConfig {

    @Value("${baseDir}")
    private String baseDir;

    private String indexDir;

    public String getBaseDir() {
        return baseDir;
    }

    public void setBaseDir(String baseDir) {
        this.baseDir = baseDir;
        this.indexDir = baseDir + File.separator + "indexDir";
    }

    public String getIndexDir() {
        return baseDir  ;
    }
}
