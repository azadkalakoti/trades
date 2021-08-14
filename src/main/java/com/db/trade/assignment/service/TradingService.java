package com.db.trade.assignment.service;

import com.db.trade.assignment.model.Expired;
import com.db.trade.assignment.model.Trade;
import com.db.trade.assignment.repository.TradingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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

    public void createTrade(Trade trade) {
        trade.setCreatedDate(LocalDate.now());
        trade.setExpired(Expired.N);
        tradingRepository.save(trade);
    }
}
