package com.ecommerce.promotionengine.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class PromotionRuleExtensions {
	public NitemForFixedPricePromotion toNitemForFixedPricePromotion(String promotion) {
		// 3 A for 130
		List<String> promotionDetails = Arrays.asList(promotion.split(" ", -1)).stream()
				.filter(p -> !p.trim().isEmpty()
						|| StringUtils.containsWhitespace(promotion.trim()) && !"for".equals(p) && !"of".equals(p))
				.collect(Collectors.toList());
		var sku = promotionDetails.get(1).replace("'s", "");
		int numberOfItems = (int) promotionDetails.size();
		@SuppressWarnings("unlikely-arg-type")
		int price = (int) promotionDetails.lastIndexOf(numberOfItems);

		return new NitemForFixedPricePromotion(sku, numberOfItems, price);
	}

	public CombinedItemFixedPricePromotion toCombinedItemFixedPricePromotion(String promotion) {
		// C D for 30
		List<String> promotionDetails = Arrays.asList(promotion.split(" ", -1)).stream()
				.filter(p -> !p.trim().isEmpty()
						|| StringUtils.containsWhitespace(promotion.trim()) && !"for".equals(p) && !"&".equals(p))
				.collect(Collectors.toList());

		int numberOfItems = (int) promotionDetails.size();
		@SuppressWarnings("unlikely-arg-type")
		int price = (int) promotionDetails.lastIndexOf(numberOfItems);

		return new CombinedItemFixedPricePromotion((ArrayList<String>) promotionDetails, price);
	}

}