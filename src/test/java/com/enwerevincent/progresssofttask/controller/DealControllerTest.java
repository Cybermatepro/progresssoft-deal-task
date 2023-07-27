package com.enwerevincent.progresssofttask.controller;

import com.enwerevincent.progresssofttask.ProgresssofttaskApplication;
import com.enwerevincent.progresssofttask.dto.DealRequest;
import com.enwerevincent.progresssofttask.model.Deal;
import com.enwerevincent.progresssofttask.repository.DealRepository;
import com.enwerevincent.progresssofttask.service.DealServiceImpl;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.validation.ValidationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest(classes = { ProgresssofttaskApplication.class })
@AutoConfigureMockMvc
class DealControllerTest {

    @Autowired
    DealRepository dealRepository;

    @InjectMocks
    private DealServiceImpl dealService;
    private static final ObjectMapper mapper = createObjectMapper();



    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        return mapper.writeValueAsBytes(object);
    }
    @Autowired
    private MockMvc mockMvc;

    private final Currency ORDERING_CURRENCY = Currency.getInstance("NGN");
    private final Currency CONVERTING_CURRENCY = Currency.getInstance("USD");
    private final BigDecimal AMOUNT = BigDecimal.valueOf(8000);
    private final LocalDateTime DEAL_TIMESTAMP = LocalDateTime.now();

    private Deal deal;
    @BeforeEach
    void setUp() {
        deal = buildTestDeal();
    }

    @AfterEach
    public void destroyTest() {
        dealRepository.deleteAll();
    }
    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

    private Deal buildTestDeal(){
        Deal deal = new Deal();
        deal.setAmount(AMOUNT);
        deal.setConvertingCurrency(CONVERTING_CURRENCY);
        deal.setOrderTimeStamp(DEAL_TIMESTAMP);
        deal.setOrderingCurrency(ORDERING_CURRENCY);
        return deal;
    }


    @Test
    public void createDealTest_Success() throws Exception {
        int dealSizeBeforeCreate = dealRepository.findAll().size();
        DealRequest dealRequest = deal.buildDealRequest();
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/deal")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(dealRequest));
        MvcResult mvcResult =  mockMvc.perform(requestBuilder).andReturn();

        assertEquals(201, mvcResult.getResponse().getStatus());
        int dealSizeAfterCreate = dealRepository.findAll().size();
        assertNotEquals(dealSizeBeforeCreate , dealSizeAfterCreate);
    }


    @Test
    public void createDealTest_CurrencyValidation() throws Exception {
        int dealSizeBeforeCreate = dealRepository.findAll().size();
        DealRequest dealRequest = deal.buildDealRequest();
        dealRequest.setOrderingCurrency("USK");
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/deal")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(dealRequest));
        MvcResult mvcResult =  mockMvc.perform(requestBuilder).andReturn();

        assertEquals(500, mvcResult.getResponse().getStatus());
        int dealSizeAfterCreate = dealRepository.findAll().size();
        assertEquals(dealSizeBeforeCreate , dealSizeAfterCreate);
    }


    @Test
    public void createDealTest_Assert_That_Method_Throws_Exception(){
        int dealSizeBeforeCreate = dealRepository.findAll().size();
        DealRequest dealRequest = deal.buildDealRequest();
        dealRequest.setOrderingCurrency("USK");
       ValidationException exception =  assertThrows(ValidationException.class, ()-> dealService.createDealExchange(dealRequest));
       assertTrue(exception.getMessage().contains("Invalid Currency Code Provided"));
        int dealSizeAfterCreate = dealRepository.findAll().size();
        assertEquals(dealSizeBeforeCreate , dealSizeAfterCreate);
    }


    @Test
    void getAllDeals() throws Exception {

        dealRepository.saveAndFlush(deal);

        mockMvc
                .perform(get("/api/v1/deals"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.data.[*].amount").value(hasItem(AMOUNT.intValue())))
                .andExpect(jsonPath("$.data.[*].orderingCurrency").value(hasItem(ORDERING_CURRENCY.getCurrencyCode())))
                .andExpect(jsonPath("$.data.[*].convertingCurrency").value(hasItem(CONVERTING_CURRENCY.getCurrencyCode())));
    }
    @Test
    void getDealById() throws Exception {
        dealRepository.saveAndFlush(deal);
        mockMvc
                .perform(get("/api/v1/deal/"+deal.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.data.amount").value(AMOUNT.intValue()))
                .andExpect(jsonPath("$.data.orderingCurrency").value(ORDERING_CURRENCY.getCurrencyCode()))
                .andExpect(jsonPath("$.data.convertingCurrency").value(CONVERTING_CURRENCY.getCurrencyCode()));
    }



}