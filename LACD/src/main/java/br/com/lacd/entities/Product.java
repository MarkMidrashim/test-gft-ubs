package br.com.lacd.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.lacd.model.deserializers.ProductDeserializer;
import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonDeserialize(using=ProductDeserializer.class)
@Data
@EqualsAndHashCode
@Entity
@Table(name="product")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/* CONSTRUCTOR */
	
	public Product () {}
	
	public Product(String product, Integer quantity, Double price, String type, String industry, String origin) {
		this.product = product;
		this.quantity = quantity;
		this.price = price;
		this.type = type;
		this.industry = industry;
		this.origin = origin;
	}

	/* PROPERTIES */
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="product", nullable=false, length=50)
	@JsonProperty("product")
	private String product;
	
	@Column(name="quantity", nullable=false)
	@JsonProperty("quantity")
	private Integer quantity;
	
	@Column(name="price", nullable=false)
	@JsonProperty("price")
	private Double price;
	
	@Column(name="type", nullable=false, length=10)
	@JsonProperty("type")
	private String type;
	
	@Column(name="industry", nullable=false, length=100)
	@JsonProperty("industry")
	private String industry;
	
	@Column(name="origin", nullable=false, length=10)
	@JsonProperty("origin")
	private String origin;

	/* GETTERS & SETTERS */
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	/* METHODS */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((industry == null) ? 0 : industry.hashCode());
		result = prime * result + ((origin == null) ? 0 : origin.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (industry == null) {
			if (other.industry != null)
				return false;
		} else if (!industry.equals(other.industry))
			return false;
		if (origin == null) {
			if (other.origin != null)
				return false;
		} else if (!origin.equals(other.origin))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
}
