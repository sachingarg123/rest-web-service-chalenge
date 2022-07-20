package com.example.smaato.restwebservicechallenge.httpclient;

import java.util.List;

public class RestMessage {
	
	private Integer count;
	private List<Integer> listOfIds;

	/**
	 * 
	 */
	public RestMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param count
	 * @param listOfIds
	 */
	public RestMessage(Integer count, List<Integer> listOfIds) {
		super();
		this.count = count;
		this.listOfIds = listOfIds;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public List<Integer> getListOfIds() {
		return listOfIds;
	}
	public void setListOfIds(List<Integer> listOfIds) {
		this.listOfIds = listOfIds;
	}
	

}
