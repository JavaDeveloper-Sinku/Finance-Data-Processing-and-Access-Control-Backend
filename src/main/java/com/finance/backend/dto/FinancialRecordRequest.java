package com.finance.backend.dto;


import com.finance.backend.enums.RecordType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FinancialRecordRequest {

    @NotNull(message = "Amount is required")
    private BigDecimal amount;
    private RecordType type;
    private String category;
    private LocalDate date;
    private String notes;

}
