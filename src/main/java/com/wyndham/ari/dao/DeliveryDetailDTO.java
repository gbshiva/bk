package com.wyndham.ari.dao;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class DeliveryDetailDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1466017048126468456L;

	private long reqId;

	private short locId;
	
	private String brandId;
	
	private String propertyId;

	private String errorCode;

	private String roomTypeId;

	private String partnerRoomTypeId;

	private String ratePlanId;

	private String partnerRatePlanId;

	private Date startDate;

	private Date endDate;

	private byte subjectSubTypeId;

	private char fplosArrvStayInd;

	private String fixedLengthPattern;

	private char availabilityStatus;
	
	

	
	private char sunFlag ; 
	private char monFlag ; 
	private char tueFlag ; 
	private char wedFlag; 
	private char thuFlag ; 
	private char friFlag ; 
	private char satFlag ;
	
	private boolean[] daysInd; 
	 

	private float rate1P;
	private float rate2P;
	private float rate3P;
	private float rate4P;
	
	private float  rateExtraPerson;

	private String currency;
	private short availableInventory;

	private char freeSellFlag;

	private short minLengthOfStay;

	private short maxLengthOfStay;

	private short minStayThrough;

	private short maxStayThrough;

	private char isClosedToArrival;
	private char isClosedToDeparture;

	private Date lastModifiedTime;

	public DeliveryDetailDTO() {
	}

	

	public short getLocId() {
		return locId;
	}

	public void setLocId(short locId) {
		this.locId = locId;
	}

	public String getRoomTypeId() {
		return roomTypeId;
	}

	public void setRoomTypeId(String roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	public String getPartnerRoomTypeId() {
		return partnerRoomTypeId;
	}

	public void setPartnerRoomTypeId(String partnerRoomTypeId) {
		this.partnerRoomTypeId = partnerRoomTypeId;
	}

	public String getRatePlanId() {
		return ratePlanId;
	}

	public void setRatePlanId(String ratePlanId) {
		this.ratePlanId = ratePlanId;
	}

	public String getPartnerRatePlanId() {
		return partnerRatePlanId;
	}

	public void setPartnerRatePlanId(String partnerRatePlanId) {
		this.partnerRatePlanId = partnerRatePlanId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	

	public long getReqId() {
		return reqId;
	}



	public void setReqId(long reqId) {
		this.reqId = reqId;
	}



	public byte getSubjectSubTypeId() {
		return subjectSubTypeId;
	}



	public void setSubjectSubTypeId(byte subjectSubTypeId) {
		this.subjectSubTypeId = subjectSubTypeId;
	}



	

	public String getFixedLengthPattern() {
		return fixedLengthPattern;
	}

	public void setFixedLengthPattern(String fixedLengthPattern) {
		this.fixedLengthPattern = fixedLengthPattern;
	}

	

	public float getRate1P() {
		return rate1P;
	}

	public void setRate1P(float rate1p) {
		rate1P = rate1p;
	}

	public float getRate2P() {
		return rate2P;
	}

	public void setRate2P(float rate2p) {
		rate2P = rate2p;
	}

	public float getRate3P() {
		return rate3P;
	}

	public void setRate3P(float rate3p) {
		rate3P = rate3p;
	}

	public float getRate4P() {
		return rate4P;
	}

	public void setRate4P(float rate4p) {
		rate4P = rate4p;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public short getAvailableInventory() {
		return availableInventory;
	}

	public void setAvailableInventory(short availableInventory) {
		this.availableInventory = availableInventory;
	}

	public char getFreeSellFlag() {
		return freeSellFlag;
	}

	public void setFreeSellFlag(char freeSellFlag) {
		this.freeSellFlag = freeSellFlag;
	}

	public short getMinLengthOfStay() {
		return minLengthOfStay;
	}

	public void setMinLengthOfStay(short minLengthOfStay) {
		this.minLengthOfStay = minLengthOfStay;
	}

	public short getMaxLengthOfStay() {
		return maxLengthOfStay;
	}

	public void setMaxLengthOfStay(short maxLengthOfStay) {
		this.maxLengthOfStay = maxLengthOfStay;
	}

	public short getMinStayThrough() {
		return minStayThrough;
	}

	public void setMinStayThrough(short minStayThrough) {
		this.minStayThrough = minStayThrough;
	}

	public short getMaxStayThrough() {
		return maxStayThrough;
	}

	public void setMaxStayThrough(short maxStayThrough) {
		this.maxStayThrough = maxStayThrough;
	}

	public char getIsClosedToArrival() {
		return isClosedToArrival;
	}

	public void setIsClosedToArrival(char isClosedToArrival) {
		this.isClosedToArrival = isClosedToArrival;
	}

	public char getIsClosedToDeparture() {
		return isClosedToDeparture;
	}

	public void setIsClosedToDeparture(char isClosedToDeparture) {
		this.isClosedToDeparture = isClosedToDeparture;
	}

	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	public boolean[] getDaysInd() {
		return daysInd;
	}

	public void setDaysInd(boolean[] daysInd) {
		this.daysInd = daysInd;
	}
	
	public String getBrandId() {
		return brandId;
	}



	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}

	public String getPropertyId() {
		return propertyId;
	}



	public String getErrorCode() {
		return errorCode;
	}



	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}



	public char getFplosArrvStayInd() {
		return fplosArrvStayInd;
	}



	public void setFplosArrvStayInd(char fplosArrvStayInd) {
		this.fplosArrvStayInd = fplosArrvStayInd;
	}



	public char getSunFlag() {
		return sunFlag;
	}



	public void setSunFlag(char sunFlag) {
		this.sunFlag = sunFlag;
	}



	public char getMonFlag() {
		return monFlag;
	}



	public void setMonFlag(char monFlag) {
		this.monFlag = monFlag;
	}



	public char getTueFlag() {
		return tueFlag;
	}



	public void setTueFlag(char tueFlag) {
		this.tueFlag = tueFlag;
	}



	public char getWedFlag() {
		return wedFlag;
	}



	public void setWedFlag(char wedFlag) {
		this.wedFlag = wedFlag;
	}



	public char getThuFlag() {
		return thuFlag;
	}



	public void setThuFlag(char thuFlag) {
		this.thuFlag = thuFlag;
	}



	public char getFriFlag() {
		return friFlag;
	}



	public void setFriFlag(char friFlag) {
		this.friFlag = friFlag;
	}



	public char getSatFlag() {
		return satFlag;
	}



	public void setSatFlag(char satFlag) {
		this.satFlag = satFlag;
	}



	public float getRateExtraPerson() {
		return rateExtraPerson;
	}



	public void setRateExtraPerson(float rateExtraPerson) {
		this.rateExtraPerson = rateExtraPerson;
	}

	public char getAvailabilityStatus() {
		return availabilityStatus;
	}

	public void setAvailabilityStatus(char availabilityStatus) {
		this.availabilityStatus = availabilityStatus;
	}



	@Override
	public String toString() {
		return "DeliveryDetailDTO [reqId=" + reqId + ", locId=" + locId
				+ ", brandId=" + brandId + ", propertyId=" + propertyId
				+ ", errorCode=" + errorCode + ", roomTypeId=" + roomTypeId
				+ ", partnerRoomTypeId=" + partnerRoomTypeId + ", ratePlanId="
				+ ratePlanId + ", partnerRatePlanId=" + partnerRatePlanId
				+ ", startDate=" + startDate + ", endDate=" + endDate
				+ ", subjectSubTypeId=" + subjectSubTypeId
				+ ", fplosArrvStayInd=" + fplosArrvStayInd
				+ ", fixedLengthPattern=" + fixedLengthPattern
				+ ", availabilityStatus=" + availabilityStatus + ", sunFlag="
				+ sunFlag + ", monFlag=" + monFlag + ", tueFlag=" + tueFlag
				+ ", wedFlag=" + wedFlag + ", thuFlag=" + thuFlag
				+ ", friFlag=" + friFlag + ", satFlag=" + satFlag
				+ ", daysInd=" + Arrays.toString(daysInd) + ", rate1P="
				+ rate1P + ", rate2P=" + rate2P + ", rate3P=" + rate3P
				+ ", rate4P=" + rate4P + ", rateExtraPerson=" + rateExtraPerson
				+ ", currency=" + currency + ", availableInventory="
				+ availableInventory + ", freeSellFlag=" + freeSellFlag
				+ ", minLengthOfStay=" + minLengthOfStay + ", maxLengthOfStay="
				+ maxLengthOfStay + ", minStayThrough=" + minStayThrough
				+ ", maxStayThrough=" + maxStayThrough + ", isClosedToArrival="
				+ isClosedToArrival + ", isClosedToDeparture="
				+ isClosedToDeparture + ", lastModifiedTime="
				+ lastModifiedTime + "]";
	} 
}