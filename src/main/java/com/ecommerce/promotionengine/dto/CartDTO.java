package com.ecommerce.promotionengine.dto;

import java.io.Serializable;
import java.util.*;

public class CartDTO implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<CartItemDTO> Items;
	private float TotalPrice;
	public ArrayList<CartItemDTO> getItems() {
		return Items;
	}
	public void setItems(ArrayList<CartItemDTO> items) {
		Items = items;
	}
	public float getTotalPrice() {
		return TotalPrice;
	}
	public void setTotalPrice(float totalPrice) {
		TotalPrice = totalPrice;
	}
}
