package com.ecommerce.promotionengine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.promotionengine.service.IStore;
import com.ecommerce.promotionengine.service.PromotionRule;
import com.ecommerce.promotionengine.serviceImpl.Store;

@RestController
@RequestMapping("api/promotions")
public class PromotionsController {
	@Autowired
	private IStore store;

	@GetMapping("/all")
	public List<PromotionRule> getAllPromotions() {
		List<PromotionRule> promotions = null;
		try {
			promotions = store.getPromotions();
		} catch (RuntimeException e) {
		}
		return promotions;
	}

	@PostMapping("/in/{promotion}")
	public Store insertPromotion(@PathVariable("promotion") String promotion) {
		Store store1 = null;
		try {
			store1 = store.addPromotion(promotion);
		} catch (RuntimeException e) {
		}
		return store1;
	}

	@DeleteMapping("/delete/{promotion}")
	public ResponseEntity<String> deletePromotion(@PathVariable("promotion") String promotion) {
		try {
			store.deletePromotion(promotion);
		} catch (RuntimeException e) {
		}
		return new ResponseEntity<>("Promotion successfully deleted!", HttpStatus.ACCEPTED);
	}
}
