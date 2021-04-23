package com.fullth.tic.service;

import java.util.List;

import com.fullth.tic.domain.Motor;

public interface MotorTrendService {
	List<Motor> selectMotorScrapList();
	void insertMotorScrapList(Motor scrapMotor);
}
