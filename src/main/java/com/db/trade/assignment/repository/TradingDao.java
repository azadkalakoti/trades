package com.db.trade.assignment.repository;

import com.db.trade.assignment.model.Trade;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Dao Class of Trading
 */
@Repository
public class TradingDao {

    public  static Map<String,Trade> mapOfTrades =new ConcurrentHashMap<>();

    /*
     * Returns list of all the trades
     */
    public List<Trade> findAll() {
        return mapOfTrades.values().stream().collect(Collectors.toList());
    }

    /*
     * Returns details of a single trade.
     * Takes trade id as input
     */
    public Trade findTrade(String tradeId) {
        return mapOfTrades.get(tradeId);
    }

    /*
     * Saves details of a single trade.
     */
    public void save(Trade trade) {
        trade.setCreatedDate(LocalDate.now());
        mapOfTrades.put(trade.getTradeId(), trade);
    }
}
