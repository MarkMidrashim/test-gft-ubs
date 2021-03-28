package br.com.lacd.model;

import java.io.Serializable;
import java.util.List;

public class DataList implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/* CONSTRUCTOR */
	
	public DataList(List<Product> data) {
		this.data = data;
	}
	
	/* PROPERTIES */
	
	private List<Product> data;
	
	/* GETTERS & SETTERS */

	public List<Product> getData() {
		return data;
	}

	public void setData(List<Product> data) {
		this.data = data;
	}

}
