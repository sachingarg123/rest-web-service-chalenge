package com.example.smaato.restwebservicechallenge.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UniqueIdModel {
	
	@Id
	private Integer Id;
	private Date creationDate;
	
	public UniqueIdModel() {
		
	}

	/**
	 * @param id
	 * @param crte_ts
	 */
	public UniqueIdModel(Integer id, Date creationDate) {
		super();
		Id = id;
		this.creationDate = creationDate;
	}
	
	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCrte_ts(Date creationDate) {
		this.creationDate = creationDate;
	}

	
}
