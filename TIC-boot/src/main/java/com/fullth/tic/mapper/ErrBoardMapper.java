package com.fullth.tic.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fullth.tic.domain.ErrBoard;

@Repository
public interface ErrBoardMapper {
	List<ErrBoard> selectErrList();
	
	void insertErr(ErrBoard err);
}
