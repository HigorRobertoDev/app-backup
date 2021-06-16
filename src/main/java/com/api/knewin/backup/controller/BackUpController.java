package com.api.knewin.backup.controller;

import com.api.knewin.backup.service.BackupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api-backup")
@Slf4j
public class BackUpController {

    @Autowired
    private BackupService backupService;

    @GetMapping
    public void executeBackup() throws IOException {

        log.info("Início do controller");

        backupService.executeBackup();
    }

}
