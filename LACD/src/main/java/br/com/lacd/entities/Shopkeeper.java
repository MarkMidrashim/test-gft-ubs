package br.com.lacd.entities;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Shopkeeper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/* CONSTRUCTOR */
	
	public Shopkeeper() {}
	
	public Shopkeeper(List<Product> products, Integer quantity, Double price, Double averagePrice) {
		this.products = products;
		this.quantity = quantity;
		this.price = price;
		this.averagePrice = averagePrice;
	}

	/* PROPERTIES */
	
	private List<Product> products;
	
	private Integer quantity;
	
	private Double price;
	
	private Double averagePrice;
	
	/* GETTERS & SETTERS */

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Integer getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getAveragePrice() {
		return averagePrice;
	}
	
	public void setAveragePrice(Double averagePrice) {
		this.averagePrice = averagePrice;
	}
	
	/* METHODS */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((averagePrice == null) ? 0 : averagePrice.hashCode());
		result = prime * result + ((products == null) ? 0 : products.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Shopkeeper other = (Shopkeeper) obj;
		if (averagePrice == null) {
			if (other.averagePrice != null)
				return false;
		} else if (!averagePrice.equals(other.averagePrice))
			return false;
		if (products == null) {
			if (other.products != null)
				return false;
		} else if (!products.equals(other.products))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}

}
