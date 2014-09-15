package com.whg.cari.push;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import com.wyndham.ari.cari.service.impl.CariPreAggregatorCacheService;
import com.wyndham.ari.dao.PreAggrDTO;
import com.wyndham.ari.helper.Instrumentation;

public class PreAggrLoadGenerator {

	LoadContext context = null;
	

	public PreAggrLoadGenerator(LoadContext context){
		this.context = context;
	} 
	
	public List<PreAggrDTO> constructPreAggrList(){

		String brandId = context.getBrandId();
		String propertyId = context.getPropertyId();
		int roomCnt = context.getRoomTypeCnt() != null ? 
					Integer.parseInt(context.getRoomTypeCnt()):1;
		int ratePlanCnt = context.getRatePlanCnt() != null ? 
							Integer.parseInt(context.getRoomTypeCnt()):1;
		int invDays = context.getInventoryDays() !=null ?					
							Integer.parseInt(context.getInventoryDays()):1;
							
		
		List<PreAggrDTO> preAggrLst = new ArrayList<PreAggrDTO>();
		for(int i=0;i<=ratePlanCnt-1;i++){
			for(int j=0;j<=roomCnt-1;j++){
				for(int k=0;k<=invDays-1;k++){
					
					Date invDate = PreAggrLoadHelper.getDateByAddingDays(k);
					PreAggrDTO aggrDTO = new PreAggrDTO(brandId,propertyId,
							"RP"+i, "RM"+j,invDate,(byte)1,(byte)1);
					preAggrLst.add(aggrDTO);
					
				}
			}
		}
		return preAggrLst;
	}
	
	public List<PreAggrDTO> fillAvailabilityData(List<PreAggrDTO> preAggrLst){
		
		String msgTypes = context.getMsgTypes();
		
		if(msgTypes == null){
			for(PreAggrDTO preAggrDTO:preAggrLst){
				setValuesByMsgType("S",preAggrDTO);
			}
			return preAggrLst;
		}
		
		
		List<PreAggrDTO> preAggrLstFinal = new ArrayList<PreAggrDTO>();
		String[] msgTypesArr = msgTypes.split(",");
		for(String msgType:msgTypesArr){
			
			for(PreAggrDTO preAggrDTO:preAggrLst){
				
				PreAggrDTO preAggrDTOFinal = new PreAggrDTO(preAggrDTO.getBrandId(),preAggrDTO.getPropertyId(),
						preAggrDTO.getRatePlanId(),preAggrDTO.getRoomTypeId(),
							preAggrDTO.getInventoryDate(),preAggrDTO.getPartnerId(),
								preAggrDTO.getAggrStatusId());
				
				setValuesByMsgType(msgType,preAggrDTOFinal);
				preAggrLstFinal.add(preAggrDTOFinal);
			}
		}
		
		return preAggrLstFinal;
	}
	
	
	private void setValuesByMsgType(String msgType, PreAggrDTO preAggrDTO){
				
		if(msgType.equalsIgnoreCase("S")){
			preAggrDTO.setAvailStatusFlag('O');
			preAggrDTO.setAvailableInvQty((short)100);
			preAggrDTO.setMsgSubjId((byte)1);
			preAggrDTO.setMsgSubjSubTypeId((byte)1);
		}
		
		if(msgType.equalsIgnoreCase("I")){
			preAggrDTO.setAvailableInvQty((short)100);
			preAggrDTO.setOldInvQty((short)99);
			preAggrDTO.setMsgSubjId((byte)2);
			preAggrDTO.setMsgSubjSubTypeId((byte)2);
		}
		
		if(msgType.equalsIgnoreCase("R")){
			preAggrDTO.setCloseToArrivalFlag(true);
			preAggrDTO.setLosNAInd("NNNNNNN");
			preAggrDTO.setMinLengthOfStay((byte)1);
			preAggrDTO.setMaxLengthOfStay((short)10);
			preAggrDTO.setMsgSubjId((byte)3);
			preAggrDTO.setMsgSubjSubTypeId((byte)3);			
		}
		
		preAggrDTO.setRequestTypeId((byte)0);
		preAggrDTO.setThreadId(12345678L);
		preAggrDTO.setRecCreateTime(Calendar.getInstance().getTime());
		preAggrDTO.setRecUpdateTime(Calendar.getInstance().getTime());
	}
}
