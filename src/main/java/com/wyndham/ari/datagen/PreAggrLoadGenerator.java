package com.wyndham.ari.datagen;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import com.wyndham.ari.cari.service.impl.CariPreAggregatorCacheService;
import com.wyndham.ari.dao.PreAgg;
import com.wyndham.ari.helper.Instrumentation;

public class PreAggrLoadGenerator {

	LoadContext context = null;
	

	public PreAggrLoadGenerator(LoadContext context){
		this.context = context;
	} 
	
	public List<PreAgg> constructPreAggrList(){

		String brandId = context.getBrandId();
		String propertyId = context.getPropertyId();
		int roomCnt = context.getRoomTypeCnt() != null ? 
					Integer.parseInt(context.getRoomTypeCnt()):1;
		int ratePlanCnt = context.getRatePlanCnt() != null ? 
							Integer.parseInt(context.getRoomTypeCnt()):1;
		int invDays = context.getInventoryDays() !=null ?					
							Integer.parseInt(context.getInventoryDays()):1;
							
		
		List<PreAgg> preAggrLst = new ArrayList<PreAgg>();
		for(int i=0;i<=ratePlanCnt-1;i++){
			for(int j=0;j<=roomCnt-1;j++){
				for(int k=0;k<=invDays-1;k++){
					
					Date invDate = PreAggrLoadHelper.getDateByAddingDays(k);
					PreAgg aggrDTO = new PreAgg(brandId,propertyId,
							"RP"+i, "RM"+j,invDate,(byte)1,(byte)0);
					preAggrLst.add(aggrDTO);
					
				}
			}
		}
		return preAggrLst;
	}
	
	public List<PreAgg> fillAvailabilityData(List<PreAgg> preAggrLst){
		
		String msgTypes = context.getMsgTypes();
		
		if(msgTypes == null){
			for(PreAgg PreAgg:preAggrLst){
				setValuesByMsgType("S",PreAgg);
			}
			return preAggrLst;
		}
		
		
		List<PreAgg> preAggrLstFinal = new ArrayList<PreAgg>();
		String[] msgTypesArr = msgTypes.split(",");
		for(String msgType:msgTypesArr){
			
			for(PreAgg PreAgg:preAggrLst){
				
				PreAgg PreAggFinal = new PreAgg(PreAgg.getBrandId(),PreAgg.getPropertyId(),
						PreAgg.getRatePlanId(),PreAgg.getRoomTypeId(),
							PreAgg.getInventoryDate(),PreAgg.getPartnerId(),
								PreAgg.getAggrStatusId());
				
				setValuesByMsgType(msgType,PreAggFinal);
				preAggrLstFinal.add(PreAggFinal);
			}
		}
		
		return preAggrLstFinal;
	}
	
	
	private void setValuesByMsgType(String msgType, PreAgg PreAgg){
				
		if(msgType.equalsIgnoreCase("S")){
			PreAgg.setAvailStatusFlag('O');
			PreAgg.setAvailableInvQty((short)100);
			PreAgg.setMsgSubjId((byte)1);
			PreAgg.setMsgSubjSubTypeId((byte)1);
		}
		
		if(msgType.equalsIgnoreCase("I")){
			PreAgg.setAvailableInvQty((short)100);
			PreAgg.setOldInvQty((short)99);
			PreAgg.setMsgSubjId((byte)2);
			PreAgg.setMsgSubjSubTypeId((byte)2);
		}
		
		if(msgType.equalsIgnoreCase("R")){
			PreAgg.setCloseToArrivalFlag(true);
			PreAgg.setLosNAInd("NNNNNNN");
			PreAgg.setMinLengthOfStay((byte)1);
			PreAgg.setMaxLengthOfStay((short)10);
			PreAgg.setMsgSubjId((byte)3);
			PreAgg.setMsgSubjSubTypeId((byte)3);			
		}
		
		PreAgg.setRequestTypeId((byte)0);
		PreAgg.setThreadId(0L);
		PreAgg.setRecCreateTime(Calendar.getInstance().getTime());
		PreAgg.setRecUpdateTime(Calendar.getInstance().getTime());
	}
}
