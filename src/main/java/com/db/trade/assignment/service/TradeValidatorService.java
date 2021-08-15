package com.db.trade.assignment.service;

import com.db.trade.assignment.model.Expired;
import com.db.trade.assignment.model.Trade;
import com.db.trade.assignment.repository.TradingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TradeValidatorService {

    private static final Logger logger = LoggerFactory.getLogger(TradingService.class);
    @Autowired
    private TradingRepository tradingRepository;

    public boolean isValidTrade(Trade trade) {

        if(isValidMaturityDate(trade)) {
            if(tradingRepository.existsById(trade.getTradeId())) {
                Trade existingTrade = tradingRepository.getById(trade.getTradeId());
                return existingTrade.getVersion() <= trade.getVersion();
            }
            return true;
        }
        return false;
    }

    private boolean isValidMaturityDate(Trade trade) {
        return LocalDate.now().isBefore(trade.getMaturityDate());
    }

    public void updateExpiryFlagOfTrade() {
        tradingRepository.findAll().stream()
                .forEach( trade -> {
                    if(LocalDate.now().isAfter(trade.getMaturityDate())) {
                        trade.setExpired(Expired.Y);
                        logger.info("Updated trade for expiry {}", trade.getMaturityDate());
                        tradingRepository.save(trade);
                    }
                });
    }
}
