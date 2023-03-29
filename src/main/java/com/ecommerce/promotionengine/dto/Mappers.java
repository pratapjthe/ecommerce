package com.ecommerce.promotionengine.dto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ecommerce.promotionengine.domain.Cart;
import com.ecommerce.promotionengine.domain.CartItem;
import com.ecommerce.promotionengine.domain.SKUitem;

@Component
public class Mappers {

	@Autowired
	private ModelMapper modelMapper;

	public SKUitemDTO SKUitemToSKUitemDTO(SKUitem skuItem) {

		return modelMapper.map(skuItem, SKUitemDTO.class);
	}

	public SKUitem SKUitemDTOToSKUitem(SKUitemDTO sKUitemDTO) {
		return modelMapper.map(sKUitemDTO, SKUitem.class);
	}

	public CartDTO CartToCartDTO(Cart cart) {
		return modelMapper.map(cart, CartDTO.class);
	}

	public CartItemDTO CartItemToCartItemDTO(CartItem cartItem) {
		return modelMapper.map(cartItem, CartItemDTO.class);
	}
}
