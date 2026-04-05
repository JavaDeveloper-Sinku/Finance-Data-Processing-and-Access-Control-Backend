package com.finance.backend.controller;


import com.finance.backend.dto.FinancialSummaryDTO;
import com.finance.backend.entity.FinancialRecord;
import com.finance.backend.enums.RecordType;
import com.finance.backend.service.FinancialRecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/records")
public class FinancialRecordController {

    @Autowired
    private FinancialRecordService recordService;

    // Helper to get current logged-in email
    private String getCurrentUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    // CREATE - ADMIN only
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public FinancialRecord create(@Valid @RequestBody FinancialRecord record) {
        return recordService.createRecord(record, getCurrentUserEmail());
    }

    // READ - ADMIN + ANALYST + VIEWER
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST','VIEWER')")
    public List<FinancialRecord> getAll() {
        return recordService.getRecordsByUser(getCurrentUserEmail());
    }

    // UPDATE - ADMIN only
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public FinancialRecord update(@PathVariable Long id,
                                  @Valid
                                  @RequestBody FinancialRecord record) {
        return recordService.updateRecord(id, record, getCurrentUserEmail());
    }

    // DELETE - ADMIN only
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable Long id) {
        recordService.deleteRecord(id, getCurrentUserEmail());
        return "Record deleted";
    }


    //Dashboard Summary Controller
    @GetMapping("/dashboard")
    @PreAuthorize("hasAnyRole('ADMIN','ANALYST','VIEWER')")
    public FinancialSummaryDTO dashboard() {
        return recordService.getDashboardSummary(getCurrentUserEmail());
    }
}