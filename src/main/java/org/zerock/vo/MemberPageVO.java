package org.zerock.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class MemberPageVO extends PageVO{
	
	String code;
	String name;
	String grade;
	String id;
	
	 public MemberPageVO() {
			
			super();
			
			// TODO Auto-generated constructor stub
		}
	
	 public MemberPageVO(String code) {
			
			super();
			this.code=code;
			// TODO Auto-generated constructor stub
		}
	
	public MemberPageVO(int pageNum, int amount, String code) {
		super(pageNum, amount);
		this.code=code;
		// TODO Auto-generated constructor stub
	}

}
