package org.zerock.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ViewApprovalVO extends ApprovalVO {

	private String approver;
	private int orderNo;
	private String signFileName;
	private String signNo;
	private String userName;
	private String grade;

}
