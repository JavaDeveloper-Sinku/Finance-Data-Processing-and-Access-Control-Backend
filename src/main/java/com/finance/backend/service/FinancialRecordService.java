package com.finance.backend.service;


import com.finance.backend.dto.FinancialSummaryDTO;
import com.finance.backend.entity.FinancialRecord;
import com.finance.backend.entity.User;
import com.finance.backend.enums.RecordType;
import com.finance.backend.repository.FinancialRecordRepository;
import com.finance.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FinancialRecordService {

    @Autowired
    private FinancialRecordRepository recordRepository;

    @Autowired
    private UserRepository userRepository;

    public FinancialRecord createRecord(FinancialRecord record, String userEmail) {
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        record.setUser(user);
        return recordRepository.save(record);
    }

    public List<FinancialRecord> getRecordsByUser(String userEmail) {
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        return recordRepository.findByUser(user);
    }

    public FinancialRecord updateRecord(Long id, FinancialRecord updatedRecord, String userEmail) {
        FinancialRecord record = recordRepository.findById(id).orElseThrow();
        if(!record.getUser().getEmail().equals(userEmail)) {
            throw new RuntimeException("Not authorized");
        }
        record.setAmount(updatedRecord.getAmount());
        record.setCategory(updatedRecord.getCategory());
        record.setDate(updatedRecord.getDate());
        record.setNote(updatedRecord.getNote());
        record.setType(updatedRecord.getType());
        return recordRepository.save(record);
    }

    public void deleteRecord(Long id, String userEmail) {
        FinancialRecord record = recordRepository.findById(id).orElseThrow();
        if(!record.getUser().getEmail().equals(userEmail)) {
            throw new RuntimeException("Not authorized");
        }
        recordRepository.delete(record);
    }




    // Dashboard Summary Method
    public FinancialSummaryDTO getDashboardSummary(String userEmail) {
        List<FinancialRecord> records = getRecordsByUser(userEmail);

        double totalIncome = 0;
        double totalExpense = 0;
        Map<String, Double> categoryTotals = new HashMap<>();

        for(FinancialRecord r : records){
            if(r.getType() == com.finance.backend.enums.RecordType.INCOME){
                totalIncome += r.getAmount();
            } else if(r.getType() == com.finance.backend.enums.RecordType.EXPENSE){
                totalExpense += r.getAmount();
            }

            categoryTotals.put(
                    r.getCategory(),
                    categoryTotals.getOrDefault(r.getCategory(), 0.0) + r.getAmount()
            );
        }

        return FinancialSummaryDTO.builder()
                .totalIncome(totalIncome)
                .totalExpense(totalExpense)
                .netBalance(totalIncome - totalExpense)
                .categoryWiseTotals(categoryTotals)
                .build();
    }
}
