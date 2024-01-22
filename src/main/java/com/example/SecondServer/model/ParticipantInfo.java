package com.example.SecondServer.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "participant_info")
public class ParticipantInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "inn")
    private String inn;
    @Column(name = "kpp")
    private String kpp;
    @Column(name = "name")
    private String name;
}
