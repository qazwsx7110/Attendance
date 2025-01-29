package org.zerock.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CompleteApprovalPageVO extends PageVO {

	String writer;
	
	 public CompleteApprovalPageVO() {
			
			super();
			
			// TODO Auto-generated constructor stub
		}
	 
	 public CompleteApprovalPageVO(String writer) {
			
			super();
			this.writer=writer;
			// TODO Auto-generated constructor stub
		}
	 
	 
	 public CompleteApprovalPageVO(int pageNum, int amount, String writer) {
			super(pageNum, amount);
			this.writer=writer;
			// TODO Auto-generated constructor stub
		}
}
