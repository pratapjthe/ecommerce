package com.ecommerce.promotionengine.serviceImpl;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.ecommerce.promotionengine.domain.Cart;
import com.ecommerce.promotionengine.service.PromotionRule;
@Component
public class NitemForFixedPricePromotion extends PromotionRule
{
	private String sKU;
	private int numberOfItems;
	private int fixedPrice;

	public String getsKU() {
		return sKU;
	}

	public void setsKU(String sKU) {
		this.sKU = sKU;
	}

	public int getNumberOfItems() {
		return numberOfItems;
	}

	public void setNumberOfItems(int numberOfItems) {
		this.numberOfItems = numberOfItems;
	}

	public int getFixedPrice() {
		return fixedPrice;
	}

	public void setFixedPrice(int fixedPrice) {
		this.fixedPrice = fixedPrice;
	}

	public NitemForFixedPricePromotion(String sku, int numberOfItems, int fixedPrice)
	{
		if (StringUtils.containsWhitespace(sku)||sku.isEmpty())
		{
			throw new IllegalArgumentException("Invalid or missing SKU!");
		}
		if (numberOfItems <= 0)
		{
			throw new IllegalArgumentException("Invalid number of items in promotion rule! It must be grater than zero!");
		}
		if (fixedPrice <= 0)
		{
			throw new IllegalArgumentException("Invalid number for fixed price in promotion rule! It must be grater than zero!");
		}

		this.sKU = sku;
		this.numberOfItems = numberOfItems;
		this.fixedPrice = fixedPrice;
	}

	@Override
	public void execute(Cart cart)
	{
		int discountItemPrice = getFixedPrice() / getNumberOfItems();
		float residue = 0f;

		while (isApplicable(cart))
		{
			residue = getFixedPrice() - getNumberOfItems() * discountItemPrice;
			for (var item : cart.getItems().stream().filter(i -> !i.isPromotionApplied()&&i.getItem().equals(getsKU())).collect(Collectors.toList()))
			{
				float f = discountItemPrice + residue;
				 item.setFinalPrice(f);
				item.setPromotionApplied(true);
				residue = 0f;
			}
		}
	}

	@Override
	public boolean isApplicable(Cart cart)
	{
		return !isEmptyCart(cart) && cart.getItems().stream().filter(i-> i.getItem().getID().equals(getsKU())).count()>= getNumberOfItems();
	}

	@Override
	public String toString()
	{
		return String.format("%1$s of %2$s's for %3$s", getNumberOfItems(), getsKU(), getFixedPrice());
	}
}