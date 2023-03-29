package com.ecommerce.promotionengine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.promotionengine.domain.SKUitem;
import com.ecommerce.promotionengine.service.IStore;
import com.ecommerce.promotionengine.serviceImpl.Store;

@RestController
@RequestMapping("api/skuitem")
public class SKUitemController {
	@Autowired
	private IStore store;

	@GetMapping("/all")
	public List<SKUitem> getAllSKUitems() {
		List<SKUitem> skUitems = null;
		try {
			skUitems = store.getSKUitems();
		} catch (RuntimeException e) {

		}
		return skUitems;
	}

	@GetMapping("/getskuitem/{id}")
	public SKUitem getSKUitem(String id) {
		SKUitem skUitem = null;
		try {
			skUitem = store.getSKUitems().stream().filter(s -> s.getID().equals(id)).findFirst().get();

		} catch (RuntimeException e) {
		}
		return skUitem;
	}

	@PostMapping("/in")
	public Store insertSKUitem(@RequestBody SKUitem item) {
		Store store2 = null;
		try {
			store2 = store.addSKUitem(item);
		} catch (RuntimeException e) {
		}
		return store2;
	}

	@PutMapping("/update/{skuID}/{unitPrice}")
	public ResponseEntity<String> update(String skuID, float unitPrice) {
		try {
			store.updateSKUitemUnitPrice(skuID, unitPrice);
		} catch (RuntimeException e) {
			return new ResponseEntity<>("Update SKUitem UnitPrice failed!", HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<>("SKUitem UnitPrice updated successfully!", HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{skuID}")
	public ResponseEntity<String> deleteSKUId(@PathVariable("skuID") String skuID) {
		try {
			store.deleteSKUitem(skuID);
		} catch (RuntimeException e) {
			return new ResponseEntity<>("Delte SKUitem failed!", HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<>("Delted SKUitem successfully!", HttpStatus.ACCEPTED);
	}
}
