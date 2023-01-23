package com.delinac.filetransfer.file.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import com.delinac.filetransfer.file.entity.File;
import com.delinac.filetransfer.file.service.FileService;

import javax.annotation.PostConstruct;

@Component
public class InitializedData {

    private final FileService fileService;
    private final Environment environment;

    @Autowired
    public InitializedData(FileService fileService, Environment environment) {
        this.fileService = fileService;
        this.environment = environment;
    }

    @PostConstruct
    private synchronized void init() {
        // room for initial data
    }
}
