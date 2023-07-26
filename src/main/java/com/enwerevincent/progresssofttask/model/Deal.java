package com.enwerevincent.progresssofttask.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

@Entity
public class Deal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message ="From currency field must be provided")
    private Currency orderingCurrency;

    @NotNull(message ="converting currency field must be provided")
    private Currency convertingCurrency;

    @Column(nullable = false)
    private LocalDateTime orderTimeStamp;

    @Column(name ="deal_amount", precision = 16)
    private BigDecimal amount;
}
