package com.iting.common.model;



import lombok.Data;

@Data
public class AccountVO {
	private String accnum;
	private String idv;
	private String pwv;
	private String nickv;
	private String addrv;
	private String dadrv;
	private String mailv;
	private String phonev;
	//private String token;
	private String actype;
	private String intr;
	
	private String fileName;
	private String newFileName;
}
