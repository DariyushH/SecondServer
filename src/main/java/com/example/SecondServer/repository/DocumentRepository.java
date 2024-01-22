package com.example.SecondServer.repository;

import com.example.SecondServer.model.MainDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<MainDocument, Integer> {
    boolean existsByDocGUID(String docGUID);
}
