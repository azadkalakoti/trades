package com.db.trade.assignment.controller;

import com.db.trade.assignment.exception.BusinessException;
import com.db.trade.assignment.model.Trade;
import com.db.trade.assignment.service.TradeValidatorService;
import com.db.trade.assignment.service.TradingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <>TradingController</> is Controller class. It has
 * service endpoints that a consumer can consume and get results
 */
@RestController
@RequestMapping("/trade")
public class TradingController {

    @Autowired
    private TradingService tradingService;

    @Autowired
    private TradeValidatorService tradeValidatorService;
    /*
    * Gets the details of a particular trade based on TradeID
    *
     */
    @GetMapping("/{tradeId}")
    public Trade getTradeDetails(@RequestParam String tradeId){
        return tradingService.findTradeDetails(tradeId);
    }

    /*
    * Gets the list of all trades
    *
     */
    @GetMapping
    public List<Trade> getTrades(){
        return tradingService.findAllTrades();
    }

    /*
    * Creates a record in BE table with details provided request
    *
     */
    @PostMapping
    public ResponseEntity createTrade(@RequestBody Trade trade) {

        if(!tradeValidatorService.isValidTrade(trade)) {
            throw new BusinessException("Invalid Trade! Trade id = " + trade.getTradeId());
        }
        return tradingService.createTrade(trade);
    }
}
