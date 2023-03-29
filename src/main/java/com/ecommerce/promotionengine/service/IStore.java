package com.ecommerce.promotionengine.service;

import java.util.List;

import com.ecommerce.promotionengine.domain.Cart;
import com.ecommerce.promotionengine.domain.SKUitem;
import com.ecommerce.promotionengine.serviceImpl.Store;

public interface IStore {
	
	public Store addSKUitem(SKUitem item);
    public Store addPromotions(List<PromotionRule> promotions);
    public Store addPromotion(PromotionRule promotion);
    public Store addPromotion(String promotion);
    public Store addItemToCart(String itemSKU);
    public void deletePromotion(String promotion);
    public Store emptyCart();
    public Store checkout();
    public void deleteItemFromCart(String sku);
    public float getCartTotal();
    public List<SKUitem> getSKUitems();
    public void updateSKUitemUnitPrice(String sku, float price);
    public void deleteSKUitem(String sku);
    public List<PromotionRule> getPromotions();
    public List<SKUitem> getAllItems();
    public SKUitem getItem(String sku);
    public Cart getCart();

}
