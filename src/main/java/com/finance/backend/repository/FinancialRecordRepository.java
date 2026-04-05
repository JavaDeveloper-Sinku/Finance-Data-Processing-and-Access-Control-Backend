package com.finance.backend.repository;

import com.finance.backend.entity.FinancialRecord;
import com.finance.backend.entity.User;
import com.finance.backend.enums.RecordType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface FinancialRecordRepository extends JpaRepository<FinancialRecord, Long> {

    Page<FinancialRecord> findByUser(User user, Pageable pageable);

    List<FinancialRecord> findByUser(User user);



    List<FinancialRecord> findByUserAndType(User user, RecordType type);
    List<FinancialRecord> findByUserAndDate(User user, LocalDate date);
    List<FinancialRecord> findByUserAndCategory(User user, String category);


    List<FinancialRecord> findByUserAndTypeAndCategory(
            User user,
            RecordType type,
            String category
    );


}
