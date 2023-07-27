package com.enwerevincent.progresssofttask.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DealRequest {

    private Long id;

    @NotBlank(message = "valid currency code is required")
    private String orderingCurrency;

    @NotBlank(message = "valid currency code is required")
    private String convertingCurrency;

    @NotNull
    private LocalDateTime orderTimeStamp = LocalDateTime.now();

    @Min(value = 1, message = "amount should not be empty")
    private BigDecimal amount;





}
