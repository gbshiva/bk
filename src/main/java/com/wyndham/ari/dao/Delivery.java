package com.wyndham.ari.dao;

import java.util.Date;

public class Delivery {
	private String BRAND_CODE;
	private String HOTEL_CODE;
	private String REQ_ID;
	private String PARTNER_HOTEL_CODE;
	private Date SOURCE_TIME_STAMP = new Date();
	private String  PARTNER;                                                                                                                                                                                  
	private Date ACK_RS_TIME_STAMP;                                                                                                                                                                                  
	private String NACK_ERROR_CODE;                                                                                                                                                                                  
	private int ERR_RETRY_LIMIT;                                                                                                                                                                                        
	private int NO_RS_RETRY_COUNT;                                                                                                                                                                                        
	private String PARENT_REQ_ID;                                                                                                                                                                                 
	private String RETRY_INDICATOR;                                                                                                                                                                                 
	private Date LAST_UPDATE_TIME = new Date();
	private String MESSAGE_STATUS = "new";
	private String key;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getBRAND_CODE() {
		return BRAND_CODE;
	}
	public void setBRAND_CODE(String bRAND_CODE) {
		BRAND_CODE = bRAND_CODE;
	}
	public String getHOTEL_CODE() {
		return HOTEL_CODE;
	}
	public void setHOTEL_CODE(String hOTEL_CODE) {
		HOTEL_CODE = hOTEL_CODE;
	}
	public String getREQ_ID() {
		return REQ_ID;
	}
	public void setREQ_ID(String rEQ_ID) {
		REQ_ID = rEQ_ID;
	}
	public String getPARTNER_HOTEL_CODE() {
		return PARTNER_HOTEL_CODE;
	}
	public void setPARTNER_HOTEL_CODE(String pARTNER_HOTEL_CODE) {
		PARTNER_HOTEL_CODE = pARTNER_HOTEL_CODE;
	}
	public Date getSOURCE_TIME_STAMP() {
		return SOURCE_TIME_STAMP;
	}
	public long getSOURCE_TIME_STAMP_long(){
		return SOURCE_TIME_STAMP.getTime();
	}
	public void setSOURCE_TIME_STAMP(Date sOURCE_TIME_STAMP) {
		SOURCE_TIME_STAMP = sOURCE_TIME_STAMP;
	}
	public String getPARTNER() {
		return PARTNER;
	}
	public void setPARTNER(String pARTNER) {
		PARTNER = pARTNER;
	}
	public Date getACK_RS_TIME_STAMP() {
		return ACK_RS_TIME_STAMP;
	}
	public void setACK_RS_TIME_STAMP(Date aCK_RS_TIME_STAMP) {
		ACK_RS_TIME_STAMP = aCK_RS_TIME_STAMP;
	}
	public String getNACK_ERROR_CODE() {
		return NACK_ERROR_CODE;
	}
	public void setNACK_ERROR_CODE(String nACK_ERROR_CODE) {
		NACK_ERROR_CODE = nACK_ERROR_CODE;
	}
	public int getERR_RETRY_LIMIT() {
		return ERR_RETRY_LIMIT;
	}
	public void setERR_RETRY_LIMIT(int eRR_RETRY_LIMIT) {
		ERR_RETRY_LIMIT = eRR_RETRY_LIMIT;
	}
	public int getNO_RS_RETRY_COUNT() {
		return NO_RS_RETRY_COUNT;
	}
	public void setNO_RS_RETRY_COUNT(int nO_RS_RETRY_COUNT) {
		NO_RS_RETRY_COUNT = nO_RS_RETRY_COUNT;
	}
	public String getPARENT_REQ_ID() {
		return PARENT_REQ_ID;
	}
	public void setPARENT_REQ_ID(String pARENT_REQ_ID) {
		PARENT_REQ_ID = pARENT_REQ_ID;
	}
	public String getRETRY_INDICATOR() {
		return RETRY_INDICATOR;
	}
	public void setRETRY_INDICATOR(String rETRY_INDICATOR) {
		RETRY_INDICATOR = rETRY_INDICATOR;
	}
	public Date getLAST_UPDATE_TIME() {
		return LAST_UPDATE_TIME;
	}
	public void setLAST_UPDATE_TIME(Date lAST_UPDATE_TIME) {
		LAST_UPDATE_TIME = lAST_UPDATE_TIME;
	}
	public String getMESSAGE_STATUS() {
		return MESSAGE_STATUS;
	}
	public void setMESSAGE_STATUS(String mESSAGE_STATUS) {
		MESSAGE_STATUS = mESSAGE_STATUS;
	}   
	
	public Delivery(PreAgg preagg){
		BRAND_CODE=preagg.getBRAND_ID();
		HOTEL_CODE=preagg.getPROPERTY_ID();
		
		PARTNER=preagg.getPARTNER_ID();
		PARTNER_HOTEL_CODE=HOTEL_CODE;
		key=BRAND_CODE+HOTEL_CODE+PARTNER+PARTNER_HOTEL_CODE;
	}

}
