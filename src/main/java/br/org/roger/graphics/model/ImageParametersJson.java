package br.org.roger.graphics.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class ImageParametersJson implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Length(min=7, message="Prodcut Id can't be less than 7 digits")
	private String itemId;

	@NotNull
	private String itemDescription;
	
	private String itemOldPrice;
	
	@NotNull
	private String itemPrice;

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String name) {
		this.itemDescription = name;
	}
	
	public String getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}
	
	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemOldPrice() {
		return itemOldPrice;
	}

	public void setItemOldPrice(String itemOldPrice) {
		this.itemOldPrice = itemOldPrice;
	}

	@Override
	public String toString() {
		return "ImageParametersJson [itemId=" + itemId + ", itemDescription="
				+ itemDescription + ", itemOldPrice=" + itemOldPrice
				+ ", itemPrice=" + itemPrice + "]";
	}
	
}
