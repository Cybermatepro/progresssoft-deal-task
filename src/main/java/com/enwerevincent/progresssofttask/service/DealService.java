package com.enwerevincent.progresssofttask.service;

import com.enwerevincent.progresssofttask.dto.DealRequest;
import com.enwerevincent.progresssofttask.exception.AppCustomException;

import java.util.List;

public interface DealService {

    DealRequest createDealExchange(DealRequest dealRequest);

    DealRequest findDeal(Long id) throws AppCustomException;

    List<DealRequest> findAllDeals();

}
