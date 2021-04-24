package com.fullth.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fullth.tic.service.PropReader;
import com.fullth.tic.service.impl.MotorTrendServiceImpl;

@DisplayName("ForTDD")
public class TestAPIs {
	
	@Test
	void propRead() {
		PropReader propReader = new PropReader();
		propReader.propRead();
	}
	
	@Test
	//@Disabled
	@DisplayName("NaverSearchAPI")
	void testRequestNaverSearchAPI() {
		MotorTrendServiceImpl motorTrendServiceImpl = new MotorTrendServiceImpl();
		motorTrendServiceImpl.getMotorTrend("TEST");
	}
}