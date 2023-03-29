package com.ecommerce.promotionengine.service;

import com.ecommerce.promotionengine.domain.Cart;

public abstract class PromotionRule {
	public final String getName()
	{
		return toString();
	}
	public abstract boolean isApplicable(Cart cart);
	public abstract void execute(Cart cart);
	public static boolean isEmptyCart(Cart cart)
	{
		return (cart == null) || cart.getItems() == null || cart.getItems().size() == 0;
	}
}
