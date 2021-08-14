package com.db.trade.assignment.repository;

import com.db.trade.assignment.model.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradingRepository extends JpaRepository<Trade, String> {
}
