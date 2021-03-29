package br.com.lacd.entities;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DataList implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/* CONSTRUCTOR */
	
	public DataList() {}
	
	public DataList(List<Product> data) {
		this.data = data;
	}
	
	/* PROPERTIES */
	
	@JsonProperty("data")
	private List<Product> data;
	
	/* GETTERS & SETTERS */

	public List<Product> getData() {
		return data;
	}

	public void setData(List<Product> data) {
		this.data = data;
	}

}
