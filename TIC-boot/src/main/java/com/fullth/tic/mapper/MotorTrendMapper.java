package com.fullth.tic.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fullth.tic.domain.Motor;

@Repository
public interface MotorTrendMapper {
	// Select scrap data
	List<Motor> selectMotorScrapList();
	
	// Insert scrap data
	void insertMotorScrapList(Motor motor);
}
