package com.fullth.tic.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullth.tic.domain.ErrBoard;
import com.fullth.tic.mapper.ErrBoardMapper;

@Service
public class ErrBoardService  {
	
	@Autowired
	ErrBoardMapper errBoardMapper; 
	
	public List<ErrBoard> selectErrList() {
		List<ErrBoard> board = new ArrayList<>();
		board = errBoardMapper.selectErrList();
		return board;
	}
	
	public void insertErr(ErrBoard err) {
		errBoardMapper.insertErr(err);
	}

}
