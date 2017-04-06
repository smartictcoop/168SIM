package sim168.user;

import java.sql.Date;

public class UserModal {
	private String SIM, MSISDN, BRANCH, STATUS, DATE_INSERT, REGISTER_DATE, REGISTER_BRANCH, INSERT, UPDATE;

	public UserModal(){
		super();
	}
	
	
	public String getSIM() {
		return SIM;
	}


	public void setSIM(String sIM) {
		SIM = sIM;
	}


	public String getMSISDN() {
		return MSISDN;
	}


	public void setMSISDN(String mSISDN) {
		MSISDN = mSISDN;
	}


	public String getBRANCH() {
		return BRANCH;
	}


	public void setBRANCH(String bRANCH) {
		BRANCH = bRANCH;
	}


	public String getSTATUS() {
		return STATUS;
	}


	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}



	
	public String getREGISTER_BRANCH() {
		return REGISTER_BRANCH;
	}


	public void setREGISTER_BRANCH(String rEGISTER_BRANCH) {
		REGISTER_BRANCH = rEGISTER_BRANCH;
	}
	
	
	public String getDATE_INSERT() {
		 return DATE_INSERT;
	}
	
	public void setDATE_INSERT(String dATE_INSERT) {
		DATE_INSERT = dATE_INSERT;
		
	}
	
	
	public String getREGISTER_DATE() {
		return REGISTER_DATE;
		
	}
	
	public void setREGISTER_DATE(String rEGISTER_DATE) {
		REGISTER_DATE = rEGISTER_DATE;
		
	}
	
	}