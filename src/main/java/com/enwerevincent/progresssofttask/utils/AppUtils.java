package com.enwerevincent.progresssofttask.utils;

import com.enwerevincent.progresssofttask.dto.DealRequest;
import com.enwerevincent.progresssofttask.model.Deal;
import org.springframework.stereotype.Service;

import java.util.Currency;

@Service
public class AppUtils {


    public Deal buildDealEntity(DealRequest dealRequest){
        Deal deal = new Deal();
        deal.setAmount(dealRequest.getAmount());
        deal.setConvertingCurrency(Currency.getInstance(dealRequest.getConvertingCurrency()));
        deal.setOrderingCurrency(Currency.getInstance(dealRequest.getOrderingCurrency()));
        deal.setOrderTimeStamp(dealRequest.getOrderTimeStamp());
        return deal;
    }
}
