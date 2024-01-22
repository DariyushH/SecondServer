package com.example.SecondServer.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "bank_info")
public class BankInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "bs")
    private String bs;
    @Column(name = "bic")
    private String bic;
    @Column(name = "bs_ks")
    private String bsKs;
}
