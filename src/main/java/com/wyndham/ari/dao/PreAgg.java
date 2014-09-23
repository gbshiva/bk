package com.wyndham.ari.dao;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PreAgg implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5820723281825600227L;

	//private String key = null;
	
	private String brandId = null;

    private String propertyId = null;

    private String roomTypeId = null;

    private String ratePlanId = null;
    
    private Date inventoryDate = null;

    private byte msgSubjId;
    
    private byte msgSubjSubTypeId;
 
    private byte partnerId;
    
    private byte aggrStatusId= (byte)0;
    
    private short availableInvQty;

    private short oldInvQty;
    
    private char availStatusFlag;
    
    private boolean closeToArrivalFlag;

    private boolean closeToDepartureFlag;

    private String losNAInd;

    private short minLengthOfStay;

   	private short maxLengthOfStay;

    private short minStayThrough;

   	private short maxStayThrough;

    private byte requestTypeId;
    
    private Long threadId;
    
    private Date recCreateTime;
    
    private Date recUpdateTime;
    
    private String dateStr;
    
	public PreAgg(String brandId, String propertyId, String ratePlanId,
			String roomTypeId, Date inventoryDate, byte partnerId,
			byte aggrStatusId) {
		this.brandId = brandId;
		this.propertyId = propertyId;
		this.ratePlanId = ratePlanId;
		this.roomTypeId = roomTypeId;
		this.inventoryDate = inventoryDate;
		this.partnerId=partnerId;
		//this.msgSubjSubTypeId = msgSubjSubTypeId;
		this.aggrStatusId=aggrStatusId;
		
		SimpleDateFormat df = new SimpleDateFormat("yyMMdd");
		dateStr = df.format(inventoryDate);
		
		/*key = brandId + propertyId + roomTypeId + ratePlanId + inventoryDate +
				msgSubjSubTypeId + partnerId + aggrStatusId;*/

	}    
    

	public String getKey(){
	
		return brandId + propertyId + roomTypeId + ratePlanId + dateStr +
					msgSubjSubTypeId + partnerId + aggrStatusId;
		
		//return brandId + propertyId + roomTypeId + ratePlanId + inventoryDate +
				//msgSubjSubTypeId + partnerId + aggrStatusId;
	}

	
	

	public String getBrandId() {
		return brandId;
	}



	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}



	public String getPropertyId() {
		return propertyId;
	}



	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}



	public String getRoomTypeId() {
		return roomTypeId;
	}



	public void setRoomTypeId(String roomTypeId) {
		this.roomTypeId = roomTypeId;
	}



	public String getRatePlanId() {
		return ratePlanId;
	}



	public void setRatePlanId(String ratePlanId) {
		this.ratePlanId = ratePlanId;
	}



	public Date getInventoryDate() {
		return inventoryDate;
	}



	public void setInventoryDate(Date inventoryDate) {
		this.inventoryDate = inventoryDate;
	}



	public byte getMsgSubjId() {
		return msgSubjId;
	}



	public void setMsgSubjId(byte msgSubjId) {
		this.msgSubjId = msgSubjId;
	}



	public byte getMsgSubjSubTypeId() {
		return msgSubjSubTypeId;
	}



	public void setMsgSubjSubTypeId(byte msgSubjSubTypeId) {
		this.msgSubjSubTypeId = msgSubjSubTypeId;
	}



	public byte getPartnerId() {
		return partnerId;
	}



	public void setPartnerId(byte partnerId) {
		this.partnerId = partnerId;
	}



	public byte getAggrStatusId() {
		return aggrStatusId;
	}



	public void setAggrStatusId(byte aggrStatusId) {
		this.aggrStatusId = aggrStatusId;
	}



	public short getAvailableInvQty() {
		return availableInvQty;
	}



	public void setAvailableInvQty(short availableInvQty) {
		this.availableInvQty = availableInvQty;
	}



	public short getOldInvQty() {
		return oldInvQty;
	}



	public void setOldInvQty(short oldInvQty) {
		this.oldInvQty = oldInvQty;
	}



	public char getAvailStatusFlag() {
		return availStatusFlag;
	}



	public void setAvailStatusFlag(char availStatusFlag) {
		this.availStatusFlag = availStatusFlag;
	}



	public boolean isCloseToArrivalFlag() {
		return closeToArrivalFlag;
	}



	public void setCloseToArrivalFlag(boolean closeToArrivalFlag) {
		this.closeToArrivalFlag = closeToArrivalFlag;
	}



	public boolean isCloseToDepartureFlag() {
		return closeToDepartureFlag;
	}



	public void setCloseToDepartureFlag(boolean closeToDepartureFlag) {
		this.closeToDepartureFlag = closeToDepartureFlag;
	}



	public String getLosNAInd() {
		return losNAInd;
	}



	public void setLosNAInd(String losNAInd) {
		this.losNAInd = losNAInd;
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



	public byte getRequestTypeId() {
		return requestTypeId;
	}



	public void setRequestTypeId(byte requestTypeId) {
		this.requestTypeId = requestTypeId;
	}



	public Long getThreadId() {
		return threadId;
	}



	public void setThreadId(Long threadId) {
		this.threadId = threadId;
	}



	public Date getRecCreateTime() {
		return recCreateTime;
	}



	public void setRecCreateTime(Date recCreateTime) {
		this.recCreateTime = recCreateTime;
	}



	public Date getRecUpdateTime() {
		return recUpdateTime;
	}



	public void setRecUpdateTime(Date recUpdateTime) {
		this.recUpdateTime = recUpdateTime;
	}


	@Override
	public String toString() {
		return "PreAggrDTO [brandId=" + brandId + ", propertyId=" + propertyId
				+ ", roomTypeId=" + roomTypeId + ", ratePlanId=" + ratePlanId
				+ ", inventoryDate=" + dateStr 
				+ ", msgSubjId="
				+ msgSubjId + ", msgSubjSubTypeId=" + msgSubjSubTypeId
				+ ", partnerId=" + partnerId + ", aggrStatusId=" + aggrStatusId
				+ ", availableInvQty=" + availableInvQty + ", oldInvQty="
				+ oldInvQty + ", availStatusFlag=" + availStatusFlag
				+ ", closeToArrivalFlag=" + closeToArrivalFlag
				+ ", closeToDepartureFlag=" + closeToDepartureFlag
				+ ", losNAInd=" + losNAInd + ", minLengthOfStay="
				+ minLengthOfStay + ", maxLengthOfStay=" + maxLengthOfStay
				+ ", minStayThrough=" + minStayThrough + ", maxStayThrough="
				+ maxStayThrough + ", requestTypeId=" + requestTypeId
				+ ", threadId=" + threadId; 
				//+ ", recCreateTime=" + recCreateTime
				//+ ", recUpdateTime=" + recUpdateTime + "]";
		
		//return "";
	}
	
	
}
