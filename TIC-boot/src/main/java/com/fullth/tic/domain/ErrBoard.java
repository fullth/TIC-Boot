package com.fullth.tic.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
// TODO Test lombok on mac after branch pull.
public class ErrBoard {
	private int idx;	
	private String title;
	private String contents;
}
