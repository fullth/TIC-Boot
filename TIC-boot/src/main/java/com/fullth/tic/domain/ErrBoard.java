package com.fullth.tic.domain;

public class ErrBoard {
	
	private int idx;
	private String title;
	private String contents;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	@Override
	public String toString() {
		return "Board [idx=" + idx + ", title=" + title + "]";
	}
}
