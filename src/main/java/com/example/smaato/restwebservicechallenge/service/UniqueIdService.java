package com.example.smaato.restwebservicechallenge.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.smaato.restwebservicechallenge.dao.UniqueIdDao;
import com.example.smaato.restwebservicechallenge.exception.ApplicationException;
import com.example.smaato.restwebservicechallenge.model.UniqueIdModel;

@Component
public class UniqueIdService {
	Logger logger = LoggerFactory.getLogger(UniqueIdService.class);
	
	@Autowired
	private UniqueIdDao uniqueIdDao;
	
	
	public void saveUniqueId(Integer Id) throws ApplicationException {	
		try {
		if(!uniqueIdDao.getUniqueId(Id).isPresent()){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		UniqueIdModel uniqueIdModel = new UniqueIdModel(Id,dateFormat.parse(dateFormat.format(new Date())));
		uniqueIdDao.saveUniqueId(uniqueIdModel);
		}else{	
			logger.info("Id already available in system");
			throw new ApplicationException("Id already available in system");
			}
		}
		catch (ParseException e) {
			logger.error("Errror in converting date"+e.getMessage());
			throw new ApplicationException(e.getMessage(), e);
		}
		}
	
	public List<Integer> getUniqueIdList(Date d) throws ApplicationException{
		List<UniqueIdModel> listOfIds;
		List<Integer> idList = new ArrayList<>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			Date formatedDate = dateFormat.parse(dateFormat.format(d));
			listOfIds = uniqueIdDao.findIdByDate(formatedDate);
			if(!listOfIds.isEmpty()) {
				for(UniqueIdModel id:listOfIds ) {
					idList.add(id.getId());
				}
			}
		} catch (ParseException e) {
			logger.error("Errror in converting date"+e.getMessage());
			throw new ApplicationException(e.getMessage(), e);
		}
		return idList;
		}
		
		
	}
		
