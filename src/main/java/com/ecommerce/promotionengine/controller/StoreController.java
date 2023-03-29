package com.ecommerce.promotionengine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.promotionengine.domain.Cart;
import com.ecommerce.promotionengine.domain.SKUitem;
import com.ecommerce.promotionengine.service.IStore;

@RestController
@RequestMapping("api/store")
public class StoreController {
	@Autowired
	private IStore store;

	@GetMapping("/allitems")
	public List<SKUitem> getAllItems() {
		List<SKUitem> skuItem = null;
		try {
			skuItem = store.getAllItems();

		} catch (RuntimeException e) {

		}
		return skuItem;
	}

	@GetMapping("/item/{sku}")
	public SKUitem getItem(@PathVariable("sku") String sku) {
		SKUitem skUitem = null;
		try {
			skUitem = store.getItem(sku);
		} catch (RuntimeException e) {

		}
		return skUitem;
	}

	@GetMapping("/cart")
	public Cart getCart() {
		Cart cart = null;
		try {
			cart = store.getCart();
		} catch (RuntimeException e) {

		}
		return cart;
	}

	@PostMapping("/insetcart/{sku}")
	public SKUitem addItemToCart(@PathVariable("sku") String sku) {
		SKUitem skUitem = null;
		try {
			store.addItemToCart(sku);
			skUitem = store.getItem(sku);

		} catch (RuntimeException e) {
		}
		return skUitem;
	}

	@DeleteMapping("delete/{sku}")
	public SKUitem deleteItemFromCart(@PathVariable("sku") String sku) {
		try {
			store.addItemToCart(sku);

		} catch (RuntimeException e) {
		}
		return store.getItem(sku);
	}

	@GetMapping("/getcarttotal")
	public Float getCartTotal() {

		return store.getCartTotal();
	}

	@GetMapping("/checkout")
	public Float checkout() {
		try {
			store.checkout();

		} catch (RuntimeException e) {
		}
		return store.getCartTotal();
	}
}
