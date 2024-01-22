package com.example.SecondServer.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
//TODO сделать идентичный DTO(тот же самый класс, но с аннотациями для того чтобы ложить в БД)
// Сделать Repository для этого класса
// Сделать сервис
public class MainDocumentPOJO {
    /**
     * Аттрибуты из Report
     **/
    private String docNumber;
    private String docDate;
    private String docGUID;
    private String operationType;
    private Double amount;
    /**
     * Аттрибуты из PayDoc
     **/
    private ParticipantInfo receiverInfo;
    private ParticipantInfo payerInfo;
    private BankInfo receiverBankInfo;
    private BankInfo payerBankInfo;
    private String purpose;


}