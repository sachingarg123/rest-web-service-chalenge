package com.example.smaato.restwebservicechallenge.scheduler;

import java.util.List;

public class KafkaMessage {
	private List<Integer> listOfIds;
	private Integer count;
	/**
	 * @param listOfIds
	 * @param count
	 */
	public KafkaMessage(List<Integer> listOfIds, Integer count) {
		super();
		this.listOfIds = listOfIds;
		this.count = count;
	}
	public List<Integer> getListOfIds() {
		return listOfIds;
	}
	public void setListOfIds(List<Integer> listOfIds) {
		this.listOfIds = listOfIds;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	

}
