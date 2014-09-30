package com.wyndham.ari.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class DeliveryAdapter {

	public Delivery convert(PreAgg aggrDTO){
		
		Delivery deliveryDTO = new Delivery();

		deliveryDTO.setAckRSTimeStmp(new Date());
		deliveryDTO.setBrandId(aggrDTO.getBrandId());
		deliveryDTO.setMessageStatusId((byte)1);
		deliveryDTO.setPartnerPropertyId(aggrDTO.getPropertyId());
		deliveryDTO.setPropertyId(aggrDTO.getPropertyId());
		deliveryDTO.setReqId(getRequestId());
		deliveryDTO.setRetryCount((byte)0);
		deliveryDTO.setSourceTimeStamp(new Date());
		deliveryDTO.setSubjectId(aggrDTO.getMsgSubjId());
		
		DeliveryDetailDTO detailDTO = new DeliveryDetailDTO();
		detailDTO.setAvailabilityStatus(aggrDTO.getAvailStatusFlag());
		detailDTO.setAvailableInventory(aggrDTO.getAvailableInvQty());
		detailDTO.setBrandId(aggrDTO.getBrandId());
		detailDTO.setCurrency("USD");
		detailDTO.setDaysInd(null);
		detailDTO.setEndDate(aggrDTO.getInventoryDate());
		//detailDTO.setFplosArrvStayInd(null);
		detailDTO.setIsClosedToArrival(aggrDTO.isCloseToArrivalFlag()?'Y':'N');
		detailDTO.setIsClosedToDeparture(aggrDTO.isCloseToDepartureFlag()?'Y':'N');
		detailDTO.setLastModifiedTime(new Date());
		detailDTO.setLocId((byte)1);
		detailDTO.setMaxLengthOfStay(aggrDTO.getMaxLengthOfStay());
		detailDTO.setMaxStayThrough(aggrDTO.getMaxStayThrough());
		detailDTO.setMinLengthOfStay(aggrDTO.getMinLengthOfStay());
		detailDTO.setMinStayThrough(aggrDTO.getMinStayThrough());
		detailDTO.setPartnerRatePlanId(aggrDTO.getRatePlanId());
		detailDTO.setPartnerRoomTypeId(aggrDTO.getRoomTypeId());
		detailDTO.setPropertyId(aggrDTO.getPropertyId());
		detailDTO.setRatePlanId(aggrDTO.getRatePlanId());
		detailDTO.setRoomTypeId(aggrDTO.getRoomTypeId());
		detailDTO.setStartDate(aggrDTO.getInventoryDate());
		detailDTO.setSubjectSubTypeId(aggrDTO.getMsgSubjSubTypeId());
		
		List<DeliveryDetailDTO> list = new ArrayList<DeliveryDetailDTO>();
		list.add(detailDTO);
		deliveryDTO.setDetailLst(list);
		
		return deliveryDTO;
		
	}
	
	private static long getRequestId(){

	
		double randomNumDbl = Math.random() * 10000000;
        String randomNumStr = String.valueOf(randomNumDbl);
        randomNumStr = randomNumStr.substring(0, 6);

        String threadId = System.currentTimeMillis() + "";
        randomNumStr = randomNumStr.replaceAll("[^\\d]", "");
        StringBuilder builder = new StringBuilder();
        builder.append(threadId);
        builder.append(randomNumStr);
        return Long.valueOf(builder.toString()).longValue();
	
	}
	
	public static void main(String[] args){
		PreAgg preagg = new PreAgg("1", "1", "1", "1", new Date(), (byte)1,(byte)1);
		Delivery dlvr = new DeliveryAdapter().convert(preagg);
	}
	
}
