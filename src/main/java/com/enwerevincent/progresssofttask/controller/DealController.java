package com.enwerevincent.progresssofttask.controller;

import com.enwerevincent.progresssofttask.dto.DealRequest;
import com.enwerevincent.progresssofttask.exception.AppCustomException;
import com.enwerevincent.progresssofttask.response.ApiResponse;
import com.enwerevincent.progresssofttask.service.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class DealController extends BaseController{

    private final DealService dealService;

    @PostMapping("deal")
    public ResponseEntity<ApiResponse<DealRequest>> createDealExchange(@RequestBody  DealRequest dealRequest){
        return getResponse(dealService.createDealExchange(dealRequest));
    }

    @GetMapping("deal/{id}")
    public ResponseEntity<ApiResponse<DealRequest>> getDealExchange(@PathVariable Long id) throws AppCustomException {
        return getResponse("success", HttpStatus.OK, dealService.findDeal(id));
    }


    @GetMapping("deals")
    public ResponseEntity<ApiResponse<List<DealRequest>>> getAllDealExchange() {
        return getResponse("success", HttpStatus.OK, dealService.findAllDeals());
    }

}
