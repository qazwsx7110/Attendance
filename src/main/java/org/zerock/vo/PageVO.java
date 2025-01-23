package org.zerock.vo;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class PageVO {

	private int pageNum;
	private int amount;
	
    public PageVO() {
		
		this.pageNum=1;
		this.amount=5;
		// TODO Auto-generated constructor stub
	}
	
	
	
	public PageVO(int pageNum, int amount) {
		
		this.pageNum=pageNum;
		this.amount=amount;
		// TODO Auto-generated constructor stub
	}
	
}
