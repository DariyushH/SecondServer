package com.example.SecondServer.repository;

import com.example.SecondServer.model.ParticipantInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantInfoRepository extends JpaRepository<ParticipantInfo, Integer> {
    Long countByInnAndKpp(String inn, String kpp);
    ParticipantInfo findFirstByInnAndKpp(String inn, String kpp);
    Long countByName(String CName);
    ParticipantInfo findFirstByName(String CName);
}
