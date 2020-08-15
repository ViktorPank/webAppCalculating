package com.pankin.controller;

import com.pankin.dto.DTOCalculations;
import com.pankin.service.CalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;

@RestController
public class MainController {

    CalculationService calculationService;

    @Autowired
    public MainController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    /**
     * Calculation of received data from the front
     * @param dtoCalculations
     * @return Server response status and calculation data
     */
    @PostMapping(value = "/calculate")
    public ResponseEntity<DTOCalculations> calculatingExample(@RequestBody DTOCalculations dtoCalculations) {
        System.out.println();
        return ResponseEntity.status(HttpStatus.OK).body(calculationService.calculations(dtoCalculations));
    }

    /**
     * Upload choose file
     * @param file
     * @return Server response status
     */
    @PostMapping(value = "/upload")
    public ResponseEntity handleFileUpload(@RequestBody MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                String rawData = new String(file.getBytes());
                return ResponseEntity.status(HttpStatus.OK).body(calculationService.calculations(new DTOCalculations(rawData)));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    /**
     * Creating a file to save data
     * @param dtoCalculations
     * @return Server response status
     */
    @PostMapping(value = "/createDownloadFileResult")
    public ResponseEntity createResultToFiles(@RequestBody DTOCalculations dtoCalculations) {
        try {
            File filePath = new File("temp");
            filePath.mkdir();
            File file = new File(filePath + "\\temp.bin");
            try {
                file.createNewFile();
            } catch (IOException exception) {
                return ResponseEntity.badRequest().build();
            }
            try (FileWriter writer = new FileWriter(file, false)) {
                String text = dtoCalculations.getRawData();
                writer.write(text);
                writer.flush();
            } catch (IOException ex) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Download data file
     * @param filename
     * @return
     */
    @GetMapping(value = "/downloadFile/{file}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("file") String filename) {
        File filePath = new File("temp");
        filePath.mkdir();
        File fileDownload = new File(filePath + "\\" + filename);
        InputStreamResource resource = null;
        try {
            resource = new InputStreamResource(new FileInputStream(fileDownload));
        } catch (FileNotFoundException e) {
            ResponseEntity.badRequest().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/octet-stream");
        headers.add("Content-Disposition", "attachment; filename=result.bin");
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(fileDownload.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
