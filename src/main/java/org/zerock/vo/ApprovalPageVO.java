package org.zerock.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ApprovalPageVO extends PageVO{

	String approver;
	
	 public ApprovalPageVO() {
			
			super();
			
			// TODO Auto-generated constructor stub
		}
	 
	 public ApprovalPageVO(String approver) {
			
			super();
			this.approver=approver;
			// TODO Auto-generated constructor stub
		}
	 
	 
	 public ApprovalPageVO(int pageNum, int amount, String approver) {
			super(pageNum, amount);
			this.approver=approver;
			// TODO Auto-generated constructor stub
		}
}
