package com.wyndham.ari.dao;

import java.io.Serializable;

import java.util.Date;
import java.sql.Timestamp;

public class PreAgg implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3555753706886069697L;
	/*
	 * private int SEQ_ID = Integer.MAX_VALUE; private String BRAND_ID = "AB";
	 * private String PROPERTY_ID = "ABCDE"; private String ROOM_TYPE_ID =
	 * "ABCDEFGHIJKLMNOPQRST"; private String RATE_PLAN_ID =
	 * "ABCDEFGHIJKLMNOPQRST"; private Date INVENTORY_DATE = new Date(); private
	 * String SUBJECT = "AB"; private String PARTNER_ID ="ABCD"; private int
	 * AGGREGATE_FLAG = Integer.MAX_VALUE; private char AVAILABILITY_STATUS =
	 * 'A'; private int AVAILABLE_INVENTORY = Integer.MAX_VALUE; private char
	 * REE_SELL_FLAG ='Z'; private String RESTRICTION_TYPE =
	 * "ABCDEFGHIJKLMNOPQRST"; private char CLOSE_TO_ARRIVAL = 'm'; private char
	 * CLOSE_TO_DEPARTURE = 'n'; private int MIN_LENGTH_STAY =
	 * Integer.MAX_VALUE; private int MAX_LENGTH_STAY = Integer.MAX_VALUE;
	 * private String LOS_NOT_AVAILABLE = "1234567"; private int MIN_STAY_THRU =
	 * Integer.MAX_VALUE; private int MAX_STAY_THRU = Integer.MAX_VALUE; private
	 * int THREAD_ID = Integer.MAX_VALUE; private Date LAST_MODIFIED_DATE = new
	 * Date();
	 * 
	 * 
	 * private String reqId; private Timestamp ackRsTimeStamp; private String
	 * allRateCode; private String arrivalDateBased; private String
	 * availabilityStatusType; private Double baseByGuestAmts; private
	 * BigDecimal bookingLimit; private String bookingLimitMessageType; private
	 * String brandCode; private String chainCode; private String codeCurrency;
	 * private BigDecimal decimalPlaces; private Timestamp endDate; private
	 * BigDecimal errRetryLimit; private BigDecimal errorRetryCount; private
	 * String eventSource; private String fixedLengthPattern; private String
	 * hotelCityCode; private String hotelCode; private String invCode; private
	 * String isFri; private String isMon; private String isRoom; private String
	 * isSat; private String isSun; private String isThu; private String isTue;
	 * private String isWed; private Timestamp lastUpdateTime; private
	 * BigDecimal maxGuestApplicable; private String messageStatus; private
	 * BigDecimal minGuestApplicable; private String nackErrorCode; private
	 * BigDecimal nbrDays; private BigDecimal nbrUnits; private BigDecimal
	 * noRsRetryCount; private String operationId; private String parentReqId;
	 * private String partner; private BigDecimal priority; private BigDecimal
	 * rate1p; private BigDecimal rate2p; private BigDecimal rate3p; private
	 * BigDecimal rate4p; private String ratePlanCode; private String rateTier;
	 * private String rateTimeUnit; private String restrictionType; private
	 * String retryIndicator; private String rsErrCode; private Timestamp
	 * sourceTimeStamp; private Timestamp startDate; private BigDecimal
	 * unitMultiplier;
	 */
	private int SEQ_ID = Integer.MAX_VALUE;
	private String BRAND_ID = "AB";
	private String PROPERTY_ID = "ABCDE";
	private String ROOM_TYPE_ID = "ABCDEFGHIJKLMNOPQRST";
	private String RATE_PLAN_ID = "ABCDEFGHIJKLMNOPQRST";
	private Date INVENTORY_DATE = new Date();
	private String SUBJECT = "AB";
	private String PARTNER_ID = "ABCD";
	private int AGGREGATE_FLAG = 0;
	private char AVAILABILITY_STATUS = 'A';
	private int AVAILABLE_INVENTORY = Integer.MAX_VALUE;
	private char REE_SELL_FLAG = 'Z';
	private String RESTRICTION_TYPE = "ABCDEFGHIJKLMNOPQRST";
	private char CLOSE_TO_ARRIVAL = 'm';
	private char CLOSE_TO_DEPARTURE = 'n';
	private int MIN_LENGTH_STAY = Integer.MAX_VALUE;
	private int MAX_LENGTH_STAY = Integer.MAX_VALUE;
	private String LOS_NOT_AVAILABLE = "1234567";
	private int MIN_STAY_THRU = Integer.MAX_VALUE;
	private int MAX_STAY_THRU = Integer.MAX_VALUE;
	private int THREAD_ID = Integer.MAX_VALUE;
	private Date LAST_MODIFIED_DATE = new Date();
	private String key;

	public PreAgg(String ibrandCode, String ichainCode, String iallRateCode,
			String ihotelCode, String iratePlanCode, String ipartner,
			long istartDate) {
		BRAND_ID = ibrandCode;
		PROPERTY_ID = ichainCode;
		ROOM_TYPE_ID = iallRateCode;
		RATE_PLAN_ID = ihotelCode;
		SUBJECT = iratePlanCode;
		PARTNER_ID = ipartner;
		INVENTORY_DATE = new Timestamp(istartDate);
		key = BRAND_ID + PROPERTY_ID + ROOM_TYPE_ID + RATE_PLAN_ID + SUBJECT
				+ PARTNER_ID + INVENTORY_DATE + AGGREGATE_FLAG;

	}

	public String getKey() {
		key = BRAND_ID + PROPERTY_ID + ROOM_TYPE_ID + RATE_PLAN_ID + SUBJECT
				+ PARTNER_ID + INVENTORY_DATE + AGGREGATE_FLAG;
		return key;

	}
	/**
	public void setKey(String key) {
		this.key = key;
	}
	**/
	public int getSEQ_ID() {
		return SEQ_ID;
	}

	public void setSEQ_ID(int sEQ_ID) {
		SEQ_ID = sEQ_ID;
	}

	public String getBRAND_ID() {
		return BRAND_ID;
	}

	public void setBRAND_ID(String bRAND_ID) {
		BRAND_ID = bRAND_ID;
	}

	public String getPROPERTY_ID() {
		return PROPERTY_ID;
	}

	public void setPROPERTY_ID(String pROPERTY_ID) {
		PROPERTY_ID = pROPERTY_ID;
	}

	public String getROOM_TYPE_ID() {
		return ROOM_TYPE_ID;
	}

	public void setROOM_TYPE_ID(String rOOM_TYPE_ID) {
		ROOM_TYPE_ID = rOOM_TYPE_ID;
	}

	public String getRATE_PLAN_ID() {
		return RATE_PLAN_ID;
	}

	public void setRATE_PLAN_ID(String rATE_PLAN_ID) {
		RATE_PLAN_ID = rATE_PLAN_ID;
	}

	public Date getINVENTORY_DATE() {
		return INVENTORY_DATE;
	}

	public void setINVENTORY_DATE(Date iNVENTORY_DATE) {
		INVENTORY_DATE = iNVENTORY_DATE;
	}

	public String getSUBJECT() {
		return SUBJECT;
	}

	public void setSUBJECT(String sUBJECT) {
		SUBJECT = sUBJECT;
	}

	public String getPARTNER_ID() {
		return PARTNER_ID;
	}

	public void setPARTNER_ID(String pARTNER_ID) {
		PARTNER_ID = pARTNER_ID;
	}

	public int getAGGREGATE_FLAG() {
		return AGGREGATE_FLAG;
	}

	public void setAGGREGATE_FLAG(int aGGREGATE_FLAG) {
		AGGREGATE_FLAG = aGGREGATE_FLAG;
	}

	public char getAVAILABILITY_STATUS() {
		return AVAILABILITY_STATUS;
	}

	public void setAVAILABILITY_STATUS(char aVAILABILITY_STATUS) {
		AVAILABILITY_STATUS = aVAILABILITY_STATUS;
	}

	public int getAVAILABLE_INVENTORY() {
		return AVAILABLE_INVENTORY;
	}

	public void setAVAILABLE_INVENTORY(int aVAILABLE_INVENTORY) {
		AVAILABLE_INVENTORY = aVAILABLE_INVENTORY;
	}

	public char getREE_SELL_FLAG() {
		return REE_SELL_FLAG;
	}

	public void setREE_SELL_FLAG(char rEE_SELL_FLAG) {
		REE_SELL_FLAG = rEE_SELL_FLAG;
	}

	public String getRESTRICTION_TYPE() {
		return RESTRICTION_TYPE;
	}

	public void setRESTRICTION_TYPE(String rESTRICTION_TYPE) {
		RESTRICTION_TYPE = rESTRICTION_TYPE;
	}

	public char getCLOSE_TO_ARRIVAL() {
		return CLOSE_TO_ARRIVAL;
	}

	public void setCLOSE_TO_ARRIVAL(char cLOSE_TO_ARRIVAL) {
		CLOSE_TO_ARRIVAL = cLOSE_TO_ARRIVAL;
	}

	public char getCLOSE_TO_DEPARTURE() {
		return CLOSE_TO_DEPARTURE;
	}

	public void setCLOSE_TO_DEPARTURE(char cLOSE_TO_DEPARTURE) {
		CLOSE_TO_DEPARTURE = cLOSE_TO_DEPARTURE;
	}

	public int getMIN_LENGTH_STAY() {
		return MIN_LENGTH_STAY;
	}

	public void setMIN_LENGTH_STAY(int mIN_LENGTH_STAY) {
		MIN_LENGTH_STAY = mIN_LENGTH_STAY;
	}

	public int getMAX_LENGTH_STAY() {
		return MAX_LENGTH_STAY;
	}

	public void setMAX_LENGTH_STAY(int mAX_LENGTH_STAY) {
		MAX_LENGTH_STAY = mAX_LENGTH_STAY;
	}

	public String getLOS_NOT_AVAILABLE() {
		return LOS_NOT_AVAILABLE;
	}

	public void setLOS_NOT_AVAILABLE(String lOS_NOT_AVAILABLE) {
		LOS_NOT_AVAILABLE = lOS_NOT_AVAILABLE;
	}

	public int getMIN_STAY_THRU() {
		return MIN_STAY_THRU;
	}

	public void setMIN_STAY_THRU(int mIN_STAY_THRU) {
		MIN_STAY_THRU = mIN_STAY_THRU;
	}

	public int getMAX_STAY_THRU() {
		return MAX_STAY_THRU;
	}

	public void setMAX_STAY_THRU(int mAX_STAY_THRU) {
		MAX_STAY_THRU = mAX_STAY_THRU;
	}

	public int getTHREAD_ID() {
		return THREAD_ID;
	}

	public void setTHREAD_ID(int tHREAD_ID) {
		THREAD_ID = tHREAD_ID;
	}

	public Date getLAST_MODIFIED_DATE() {
		return LAST_MODIFIED_DATE;
	}

	public void setLAST_MODIFIED_DATE(Date lAST_MODIFIED_DATE) {
		LAST_MODIFIED_DATE = lAST_MODIFIED_DATE;
	}

}
