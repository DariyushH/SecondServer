package com.example.SecondServer.services;

import com.example.SecondServer.model.MainDocumentPOJO;

public interface DocumentService {
    void save(MainDocumentPOJO document) throws Exception;
}
