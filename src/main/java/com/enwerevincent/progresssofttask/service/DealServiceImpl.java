package com.enwerevincent.progresssofttask.service;

import com.enwerevincent.progresssofttask.dto.DealRequest;
import com.enwerevincent.progresssofttask.exception.AppCustomException;
import com.enwerevincent.progresssofttask.model.Deal;
import com.enwerevincent.progresssofttask.repository.DealRepository;
import com.enwerevincent.progresssofttask.utils.AppUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DealServiceImpl implements DealService{

    private final DealRepository dealRepository;

    private final AppUtils appUtils;
    @Override

    public DealRequest createDealExchange(@NonNull  DealRequest dealRequest) {
        log.info("Going to Create Deal for the request ----> {}", dealRequest);
        if (isISOCurrencyCodeNotValid(dealRequest.getConvertingCurrency())
                || isISOCurrencyCodeNotValid(dealRequest.getOrderingCurrency()))
            throw  new ValidationException("Invalid Currency Code Provided");

        Deal deal = dealRepository.save(appUtils.buildDealEntity(dealRequest));
        return  deal.buildDealRequest();
    }

    @Override
    public DealRequest findDeal(Long id) throws AppCustomException {
        return dealRepository.findById(id).orElseThrow(() -> new AppCustomException("Deal Not Found"))
                .buildDealRequest();
    }

    @Override
    public List<DealRequest> findAllDeals(){
        return dealRepository.findAll().stream().map(Deal::buildDealRequest)
                .collect(Collectors.toList());
    }

    private boolean isISOCurrencyCodeNotValid(String currencyCode) {
        return Currency.getAvailableCurrencies().stream().noneMatch(currency -> currency.getCurrencyCode().equals(currencyCode));
    }
}
