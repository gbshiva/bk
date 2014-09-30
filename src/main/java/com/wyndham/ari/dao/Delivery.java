package com.wyndham.ari.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Delivery implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 5626003126418400487L;

	private List<DeliveryDetailDTO> detailLst;

	private long reqId;

	private String brandId;

	private String propertyId;

	private String partnerPropertyId;

	private byte messageStatusId;

	private Date sourceTimeStamp;

	private byte retryCount;

	private Date ackRSTimeStmp;

	private byte subjectId;
	
	private String partnerErrorCode;
	
	private long sourceTimeStamp_long;
	
	public String getPartnerErrorCode() {
		return partnerErrorCode;
	}
	public void setPartnerErrorCode(String partnerErrorCode) {
		this.partnerErrorCode = partnerErrorCode;
	}
	public List<DeliveryDetailDTO> getDetailLst() {
		return detailLst;
	}
	public void setDetailLst(List<DeliveryDetailDTO> detailLst) {
		this.detailLst = detailLst;
	}
	public long getReqId() {
		return reqId;
	}
	public void setReqId(long reqId) {
		this.reqId = reqId;
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
	public String getPartnerPropertyId() {
		return partnerPropertyId;
	}
	public void setPartnerPropertyId(String partnerPropertyId) {
		this.partnerPropertyId = partnerPropertyId;
	}
	
	public byte getMessageStatusId() {
		return messageStatusId;
	}
	public void setMessageStatusId(byte messageStatusId) {
		this.messageStatusId = messageStatusId;
	}
	public Date getSourceTimeStamp() {
		return sourceTimeStamp;
	}
	public void setSourceTimeStamp(Date sourceTimeStamp) {
		this.sourceTimeStamp = sourceTimeStamp;
		this.sourceTimeStamp_long = sourceTimeStamp.getTime();
	}
	
	
	public long getsourceTimeStamp_long(){
		return sourceTimeStamp_long;
	}
	
	public byte getRetryCount() {
		return retryCount;
	}
	public void setRetryCount(byte retryCount) {
		this.retryCount = retryCount;
	}
	public Date getAckRSTimeStmp() {
		return ackRSTimeStmp;
	}
	public void setAckRSTimeStmp(Date ackRSTimeStmp) {
		this.ackRSTimeStmp = ackRSTimeStmp;
	}
	public byte getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(byte subjectId) {
		this.subjectId = subjectId;
	}

	@Override
	public String toString() {
		return "DeliveryDTO [detailLst=" + detailLst + ", reqId=" + reqId
				+ ", brandId=" + brandId + ", propertyId=" + propertyId
				+ ", partnerPropertyId=" + partnerPropertyId
				+ ", messageStatusId=" + messageStatusId + ", sourceTimeStamp="
				+ sourceTimeStamp + ", retryCount=" + retryCount
				+ ", ackRSTimeStmp=" + ackRSTimeStmp + ", subjectId="
				+ subjectId + ", partnerErrorCode=" + partnerErrorCode + "]";
	}
	

	

}