package com.ecommerce.promotionengine.serviceImpl;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;

import com.ecommerce.promotionengine.domain.Cart;
import com.ecommerce.promotionengine.service.PromotionRule;

public class CombinedItemFixedPricePromotion extends PromotionRule {
	private ArrayList<String> sKUs;
	private int fixedPrice;
	

	public ArrayList<String> getsKUs() {
		return sKUs;
	}

	public void setsKUs(ArrayList<String> sKUs) {
		this.sKUs = sKUs;
	}

	public int getFixedPrice() {
		return fixedPrice;
	}

	public void setFixedPrice(int fixedPrice) {
		this.fixedPrice = fixedPrice;
	}

	public CombinedItemFixedPricePromotion(ArrayList<String> skus, int fixedPrice)
	{
		if (skus == null || skus.size()< 2 || skus.stream().anyMatch(s -> StringUtils.containsWhitespace(s)||s.isEmpty()))
		{
			throw new IllegalArgumentException("Invalid or missing SKUs! At least 2 SKU must be provided for this promotion");
		}
		if (fixedPrice <= 0)
		{
			throw new IllegalArgumentException("Invalid number for fixed price in promotion rule! It must be grater than zero!");
		}

		this.sKUs = skus;
		this.fixedPrice = fixedPrice;
	}

	@Override
	public void execute(Cart cart) {
		while (isApplicable(cart)) {
			ArrayList<String> unusedSKUs = new ArrayList<String>();
			for (var item : cart.getItems().stream().filter(i -> !i.isPromotionApplied()).collect(Collectors.toList())) {
				if (unusedSKUs.contains(item.getItem().getID())) {
					 float f = getFixedPrice() / getsKUs().size();
					 item.setFinalPrice(f);
					item.setPromotionApplied(true);
					unusedSKUs.remove(item.getItem().getID());
				}
			}
		}
	}

	@Override
	public boolean isApplicable(Cart cart) {
		if (isEmptyCart(cart)) {
			return false;
		}

		var cartItemsWithoutPromotion = cart.getItems().stream().filter(i -> !i.isPromotionApplied());
		boolean applicable = true;
		for (String sku : getsKUs()) {
			applicable &= cartItemsWithoutPromotion.anyMatch(i -> i.getItem().getID().equals(sku));
		}
		return applicable;
	}

	@Override
	public String toString() {
		return String.format("%1$s for %2$s", " & ", getsKUs(),getFixedPrice());
	}
}