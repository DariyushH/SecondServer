package com.example.SecondServer.controller;

import com.example.SecondServer.model.MainDocumentPOJO;
import com.example.SecondServer.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainController {
    private final DocumentService documentService;
    @Autowired
    public MainController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping("/index")
    public void index(@RequestBody List<MainDocumentPOJO> mainDocumentList){
        for (MainDocumentPOJO mainDocument : mainDocumentList){
            System.out.println(mainDocument.toString());
            try {
                documentService.save(mainDocument);
            } catch (Exception e) {
                System.out.printf("Caught exception: %s%n", e.getMessage());
            }
        }
        System.out.println("Caught Post Request");
    }
}
