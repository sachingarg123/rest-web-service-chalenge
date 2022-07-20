package com.example.smaato.restwebservicechallenge.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.smaato.restwebservicechallenge.model.UniqueIdModel;

@Repository
public interface UniqueIdRepository extends JpaRepository<UniqueIdModel, Integer> {
	
	List<UniqueIdModel> findAllByCreationDate(Date creationDate);

}
