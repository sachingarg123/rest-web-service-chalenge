package com.example.smaato.restwebservicechallenge.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.smaato.restwebservicechallenge.model.UniqueIdModel;

@Component
public class UniqueIdDao {
	
	@Autowired
	private UniqueIdRepository uniqueIdRepository;
	
   @Transactional
   public void saveUniqueId(UniqueIdModel Id) {
	   uniqueIdRepository.save(Id);
		
	}
   
   
   public Optional<UniqueIdModel> getUniqueId(Integer Id) {
	   return uniqueIdRepository.findById(Id);
		
	}
   
   public List<UniqueIdModel> findIdByDate(Date date) {
	   return uniqueIdRepository.findAllByCreationDate(date);
		
	}
	
	
	

}
