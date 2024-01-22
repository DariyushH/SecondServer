package com.example.SecondServer.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;


//TODO Сделать сервис
@Data
@ToString
@Entity
@Table(name = "document")
public class MainDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private Integer id;
    @Column(name = "number", nullable = false)
    private String docNumber;
    @Column(name = "date")
    private String docDate;
    @Column(name = "guid")
    private String docGUID;
    @Column(name = "operation_type")
    private String operationType;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "purpose")
    private String purpose;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false, referencedColumnName = "id")
    private ParticipantInfo receiverInfo;
    @ManyToOne
    @JoinColumn(name = "payer_id", nullable = false, referencedColumnName = "id")
    private ParticipantInfo payerInfo;
    @ManyToOne
    @JoinColumn(name = "bank_receiver_id", referencedColumnName = "id")
    private BankInfo receiverBankInfo;
    @ManyToOne
    @JoinColumn(name = "bank_payer_id", referencedColumnName = "id")
    private BankInfo payerBankInfo;
}