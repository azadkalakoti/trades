package com.db.trade.assignment;

import com.db.trade.assignment.controller.TradingController;
import com.db.trade.assignment.exception.BusinessException;
import com.db.trade.assignment.model.Expired;
import com.db.trade.assignment.model.ServiceResponse;
import com.db.trade.assignment.model.ServiceResponseStatus;
import com.db.trade.assignment.model.Trade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	TradingController tradingController;
	@Test
	public void testTrades_success() {

		//Mockito.when(this.tradeValidatorService.isValidTrade(ArgumentMatchers.anyObject())).thenReturn(true);
		Trade trade = this.createTradeObject("T1", 1, LocalDate.of(2022,8, 22));
		ResponseEntity responseEntity =	tradingController.createTrade(trade);
		ServiceResponse serviceResponse = (ServiceResponse)responseEntity.getBody();
		Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		Assertions.assertEquals(ServiceResponseStatus.SUCCESS, serviceResponse.getStatus());
		Assertions.assertEquals("000", serviceResponse.getCode());
	}

	@Test
	void testIfTradeVersionIsOld() {

		// Trade1
		//Mockito.when(this.tradeValidatorService.isValidTrade(ArgumentMatchers.anyObject())).thenReturn(true);
		//Mockito.when(this.tradingService.createTrade(ArgumentMatchers.anyObject())).thenReturn(getResponseEntityForCreate());
		Trade trade1 = this.createTradeObject("T1", 2, LocalDate.of(2022,8, 22));
		ResponseEntity responseEntity1 =	tradingController.createTrade(trade1);
		Assertions.assertEquals(HttpStatus.CREATED, responseEntity1.getStatusCode());
		List<Trade> tradeList1 = tradingController.getTrades();
		Assertions.assertEquals(1, tradeList1.size());
		Assertions.assertEquals("T1", tradeList1.get(0).getTradeId());
		Assertions.assertEquals(2, tradeList1.get(0).getVersion());
		Assertions.assertEquals("B1", tradeList1.get(0).getBookId());
		Assertions.assertEquals("CP-1", tradeList1.get(0).getCounterPartyId());

		//Trade 2
		try {
			//Mockito.when(this.tradeValidatorService.isValidTrade(ArgumentMatchers.anyObject())).thenReturn(false);
			Trade trade2 = this.createTradeObject("T1", 1, LocalDate.of(2023,11, 25));
			responseEntity1 = tradingController.createTrade(trade2);
		}catch (BusinessException exc){
			System.out.println(exc.getReason());
			System.out.println(exc.getResponse());
		}
		List<Trade> tradeList2 = tradingController.getTrades();
		Assertions.assertEquals(1, tradeList2.size());
		Assertions.assertEquals("T1", tradeList2.get(0).getTradeId());
		Assertions.assertEquals(2, tradeList2.get(0).getVersion());
		Assertions.assertEquals("B1", tradeList1.get(0).getBookId());
		Assertions.assertEquals("CP-1", tradeList1.get(0).getCounterPartyId());
	}

	private ResponseEntity getResponseEntityForCreate() {
		ServiceResponse serviceResponse = new ServiceResponse();
		serviceResponse.setStatus(ServiceResponseStatus.SUCCESS);
		serviceResponse.setCode("000");
		return ResponseEntity.status(HttpStatus.CREATED).body(serviceResponse);
	}

	private Trade createTradeObject(String tradeId, int version, LocalDate maturityDate) {

		Trade trade1 = new Trade();
		trade1.setTradeId(tradeId);
		trade1.setVersion(version);
		trade1.setBookId("B1");
		trade1.setCounterPartyId("CP-1");
		trade1.setCreatedDate(LocalDate.now());
		trade1.setMaturityDate(maturityDate);
		trade1.setExpired(Expired.N);
		return trade1;
	}
}
