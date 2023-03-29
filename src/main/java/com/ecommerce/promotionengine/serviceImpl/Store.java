package com.ecommerce.promotionengine.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.ecommerce.promotionengine.domain.Cart;
import com.ecommerce.promotionengine.domain.SKUitem;
import com.ecommerce.promotionengine.service.IStore;
import com.ecommerce.promotionengine.service.PromotionRule;

@Component
public class Store implements IStore {
	@Autowired
	PromotionRuleExtensions promotionRuleExtensions;
	private Cart cart;
	private ArrayList<PromotionRule> promotions;
	private ArrayList<SKUitem> items;

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public ArrayList<PromotionRule> getPromotions() {
		return promotions;
	}

	public void setPromotions(ArrayList<PromotionRule> promotions) {
		this.promotions = promotions;
	}

	public ArrayList<SKUitem> getItems() {
		return items;
	}

	public void setItems(ArrayList<SKUitem> items) {
		this.items = items;
	}

	public Store() {
		this.cart = new Cart();
		this.promotions = new ArrayList<PromotionRule>();
		this.items = new ArrayList<SKUitem>();
	}

	public Store addSKUitem(SKUitem item) {

		if (item != null) {
			getItems().add(item);
		}
		return this;
	}

	public Store addPromotion(PromotionRule promotion) {
		if (promotion != null) {
			getPromotions().add(promotion);
		}
		return this;
	}

	public final Store addPromotion(String promotion) {
		if (Pattern.matches(promotion, "^\\d")) {
			addPromotion(promotionRuleExtensions.toNitemForFixedPricePromotion(promotion));
		} else {
			addPromotion(promotionRuleExtensions.toCombinedItemFixedPricePromotion(promotion));
		}
		return this;
	}

	@SuppressWarnings("unlikely-arg-type")
	public void deletePromotion(String promotion) {
		int promotionIndex = 0;
		getPromotions().indexOf(promotion);

		if (promotionIndex == -1) {
			throw new IllegalArgumentException("Promotion not found!");
		}
		getPromotions().remove(promotionIndex);
	}

	@SuppressWarnings("unchecked")
	public Store addItemToCart(String itemSKU) {
		if (!isValidSKU(itemSKU)) {
			throw new IllegalArgumentException("SKU not found!");
		}

		if (!StringUtils.containsWhitespace(itemSKU) && !itemSKU.isEmpty()) {
			getCart().addItem(((Stream<SKUitem>) getItems()).filter(i -> i.getID().equals(itemSKU)).findFirst().get());

		}
		return this;
	}

	public Store emptyCart() {
		setCart(new Cart());
		return this;
	}

	public Store checkout() {
		getPromotions().forEach(p -> {
			if (p.isApplicable(getCart())) {
				p.execute(getCart());
			}
		});
		return this;
	}

	public final ArrayList<SKUitem> getSKUitems() {
		return getItems();
	}

	public void updateSKUitemUnitPrice(String sku, float price) {
		if (!isValidSKU(sku)) {
			throw new IllegalArgumentException("SKU not found!");
		}
		getItems().stream().filter(i -> i.getID().equals(sku)).forEach(i -> i.updateUnitPrice(price));
	}

	@SuppressWarnings("unlikely-arg-type")
	public void deleteSKUitem(String sku) {
		if (!isValidSKU(sku)) {
			throw new IllegalArgumentException("SKU not found!");
		}

		getItems().remove(getItems().stream().allMatch(i -> i.getID().equals(sku)));
	}

	public ArrayList<SKUitem> getAllItems() {
		return items;
	}

	public SKUitem getItem(String sku) {
		if (!isValidSKU(sku)) {
			throw new IllegalArgumentException("SKU not found!");
		}

		return items.stream().filter(i -> i.getID().equals(sku)).findFirst().get();
	}

	public void deleteItemFromCart(String sku) {
		if (!isValidSKU(sku)) {
			throw new IllegalArgumentException("SKU not found!");
		}
		cart.removeItem(sku);
	}

	public float getCartTotal() {
		return (float) cart.getItems().parallelStream().mapToDouble(i -> i.getFinalPrice()).sum();

	}

	private boolean isValidSKU(String sku) {
		return items.parallelStream().anyMatch(i -> i.getID().equals(sku));

	}

	@Override
	public Store addPromotions(List<PromotionRule> promotions) {

		if (promotions != null && promotions.size() > 0) {
			getPromotions().addAll(promotions);
		}
		return this;

	}

}