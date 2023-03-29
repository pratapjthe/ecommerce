package com.ecommerce.promotionengine.domain;

import java.io.Serializable;
import java.util.ArrayList;

public class Cart implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<CartItem> items;

	public Cart() {
		this.items = new ArrayList<CartItem>();
	}

	public ArrayList<CartItem> getItems() {
		return items;
	}

	public void setItems(ArrayList<CartItem> items) {
		this.items = items;
	}

	public void addItems(SKUitem item, int numberOfItems) {
		for (int i = 0; i <= numberOfItems; i++) {
			CartItem tempVar = new CartItem();
			tempVar.setItem(item);
			tempVar.setFinalPrice(item.getUnitPrice());
			tempVar.setPromotionApplied(false);
			getItems().add(tempVar);
		}
	}

	public void addItem(SKUitem item) {
		CartItem tempVar = new CartItem();
		tempVar.setItem(item);
		tempVar.setFinalPrice(item.getUnitPrice());
		tempVar.setPromotionApplied(false);
		getItems().add(tempVar);
	}

	@SuppressWarnings("unlikely-arg-type")
	public void removeItem(String skuItemId) {
		if (!IsValidSKU(skuItemId)) {
			throw new IllegalArgumentException("Item not found on cart!");
		}
		CartItem ci = getItems().stream().findFirst().get();

		getItems().remove(ci.getItem().getID().equals(skuItemId));
	}

	private boolean IsValidSKU(String sku) {
		return getItems().stream().anyMatch(i -> i.getItem().equals(sku));
	}

}
