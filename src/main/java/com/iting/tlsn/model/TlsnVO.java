package com.iting.tlsn.model;

import java.util.Date;
import lombok.Data;

@Data
public class TlsnVO {
	private String tlsnNum;
	private String tlsnStCd;
	private String memNum;
	private String ltNum;
	private String ceteYnCd;
	private Date applDt;
	private String lnNum;
	private String ltTtl;
	private String ltCateCd;
	private String lecturerNum;
}
