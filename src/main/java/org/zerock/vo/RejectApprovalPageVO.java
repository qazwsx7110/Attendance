package org.zerock.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class RejectApprovalPageVO extends PageVO {

	String writer;
	
	 public RejectApprovalPageVO() {
			
			super();
			
			// TODO Auto-generated constructor stub
		}
	 
	 public RejectApprovalPageVO(String writer) {
			
			super();
			this.writer=writer;
			// TODO Auto-generated constructor stub
		}
	 
	 
	 public RejectApprovalPageVO(int pageNum, int amount, String writer) {
			super(pageNum, amount);
			this.writer=writer;
			// TODO Auto-generated constructor stub
		}
}
