package com.example.qlkhachsan.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class RevenueStatistics {
    private int month;
    private Double revenue;
}
