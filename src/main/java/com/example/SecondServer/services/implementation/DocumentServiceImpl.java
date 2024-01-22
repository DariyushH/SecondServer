package com.example.SecondServer.services.implementation;

import com.example.SecondServer.enums.OperationType;
import com.example.SecondServer.model.BankInfo;
import com.example.SecondServer.model.MainDocument;
import com.example.SecondServer.model.MainDocumentPOJO;
import com.example.SecondServer.model.ParticipantInfo;
import com.example.SecondServer.repository.BankInfoRepository;
import com.example.SecondServer.repository.DocumentRepository;
import com.example.SecondServer.repository.ParticipantInfoRepository;
import com.example.SecondServer.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("documentService")
public class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;
    private final BankInfoRepository bankInfoRepository;
    private final ParticipantInfoRepository participantInfoRepository;

    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository, BankInfoRepository bankInfoRepository, ParticipantInfoRepository participantInfoRepository) {
        this.documentRepository = documentRepository;
        this.bankInfoRepository = bankInfoRepository;
        this.participantInfoRepository = participantInfoRepository;
    }

    @Override
    public void save(MainDocumentPOJO document) throws Exception {
        if (!documentRepository.existsByDocGUID(document.getDocGUID())) {
            MainDocument documentDTO = new MainDocument();

            documentDTO.setDocNumber(document.getDocNumber());
            documentDTO.setDocDate(document.getDocDate());
            documentDTO.setDocGUID(document.getDocGUID());
            documentDTO.setAmount(document.getAmount());
            documentDTO.setOperationType(document.getOperationType());
            documentDTO.setPurpose(document.getPurpose());

            saveBank(documentDTO, document.getPayerBankInfo(), OperationType.PAYMENT);
            saveBank(documentDTO, document.getReceiverBankInfo(), OperationType.RECEIVEMENT);
            saveParticipant(documentDTO, document.getPayerInfo(), OperationType.PAYMENT);
            saveParticipant(documentDTO, document.getReceiverInfo(), OperationType.RECEIVEMENT);

            documentRepository.save(documentDTO);
        }
    }

    private void saveBank(MainDocument document, BankInfo bankInfo, OperationType type) throws Exception {
        BankInfo bi;
        if (bankInfoRepository.existsByBic(bankInfo.getBic())) {
            bi = bankInfoRepository.findFirstByBic(bankInfo.getBic());
        } else {
            bankInfoRepository.save(bankInfo);
            bi = bankInfo;
        }
        switch (type) {
            case PAYMENT -> document.setPayerBankInfo(bi);
            case RECEIVEMENT -> document.setReceiverBankInfo(bi);
            default -> throw new Exception("No operation found");
        }
    }

    private void saveParticipant(MainDocument document, ParticipantInfo participantInfo, OperationType type) throws Exception {
        ParticipantInfo pi;
        if (participantInfoRepository.countByInnAndKpp(participantInfo.getInn(), participantInfo.getKpp()) == 1L) {
            pi = participantInfoRepository.findFirstByInnAndKpp(participantInfo.getInn(), participantInfo.getKpp());
        } else {
            if (participantInfoRepository.countByName(participantInfo.getName()) == 1L) {
                pi = participantInfoRepository.findFirstByName(participantInfo.getName());
            } else {
                participantInfoRepository.save(participantInfo);
                pi = participantInfo;
            }
        }
        switch (type) {
            case PAYMENT -> document.setPayerInfo(pi);
            case RECEIVEMENT -> document.setReceiverInfo(pi);
            default -> throw new Exception("No operation found");
        }
    }
}
