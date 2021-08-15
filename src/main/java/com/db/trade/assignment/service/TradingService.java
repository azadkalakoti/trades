package com.db.trade.assignment.service;

import com.db.trade.assignment.model.Expired;
import com.db.trade.assignment.model.ServiceResponse;
import com.db.trade.assignment.model.ServiceResponseStatus;
import com.db.trade.assignment.model.Trade;
import com.db.trade.assignment.repository.TradingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Service class for trading application
 */
@Service
public class TradingService {

    @Autowired
    private TradingRepository tradingRepository;

    public Trade findTradeDetails(String tradeId) {
        return tradingRepository.getById(tradeId);
    }

    public List<Trade> findAllTrades() {
        return tradingRepository.findAll();
    }

    public ResponseEntity<ServiceResponse> createTrade(Trade trade) {
        trade.setCreatedDate(LocalDate.now());
        trade.setExpired(Expired.N);
        tradingRepository.save(trade);
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setStatus(ServiceResponseStatus.SUCCESS);
        serviceResponse.setCode("000");
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceResponse);
    }
}
