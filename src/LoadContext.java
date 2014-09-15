package com.whg.cari.push;

import java.util.List;

public class LoadContext {

	private String brandId;
	private String propertyId;
	private String ratePlanCnt;
	private String roomTypeCnt;
	private String inventoryDays;
	private String batchCnt;
	private String msgTypes;
	private String threadCnt;
	
	private List<String> responseTimes;
	
	public String getThreadCnt() {
		return threadCnt;
	}
	public void setThreadCnt(String threadCnt) {
		this.threadCnt = threadCnt;
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
	public String getRatePlanCnt() {
		return ratePlanCnt;
	}
	public void setRatePlanCnt(String ratePlanCnt) {
		this.ratePlanCnt = ratePlanCnt;
	}
	public String getRoomTypeCnt() {
		return roomTypeCnt;
	}
	public void setRoomTypeCnt(String roomTypeCnt) {
		this.roomTypeCnt = roomTypeCnt;
	}
	public String getInventoryDays() {
		return inventoryDays;
	}
	public void setInventoryDays(String inventoryDays) {
		this.inventoryDays = inventoryDays;
	}
	public String getBatchCnt() {
		return batchCnt;
	}
	public void setBatchCnt(String batchCnt) {
		this.batchCnt = batchCnt;
	}
	public String getMsgTypes() {
		return msgTypes;
	}
	public void setMsgTypes(String msgTypes) {
		this.msgTypes = msgTypes;
	}
	public List<String> getResponseTimes() {
		return responseTimes;
	}
	public void setResponseTimes(List<String> responseTimes) {
		this.responseTimes = responseTimes;
	}
	@Override
	public String toString() {
		return "LoadContext [brandId=" + brandId + ", propertyId=" + propertyId
				+ ", ratePlanCnt=" + ratePlanCnt + ", roomTypeCnt="
				+ roomTypeCnt + ", inventoryDays=" + inventoryDays
				+ ", batchCnt=" + batchCnt + ", msgTypes=" + msgTypes
				+ ", threadCnt=" + threadCnt + "]";
	}

	
}
