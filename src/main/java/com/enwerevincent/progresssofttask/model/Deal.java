package com.enwerevincent.progresssofttask.model;

import com.enwerevincent.progresssofttask.dto.DealRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @Transient
    public DealRequest buildDealRequest(){
        DealRequest dealRequest = new DealRequest();
        dealRequest.setId(getId());
        dealRequest.setOrderingCurrency(getOrderingCurrency() != null ? getOrderingCurrency().getCurrencyCode() : null);
        dealRequest.setAmount(getAmount());
        dealRequest.setConvertingCurrency(getConvertingCurrency() != null ? getConvertingCurrency().getCurrencyCode() : null);
        dealRequest.setOrderTimeStamp(getOrderTimeStamp());
        return  dealRequest;
    }
}
