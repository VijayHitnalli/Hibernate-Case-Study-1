package com.jsp.service;

import java.util.List;
import java.util.Scanner;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.dao.CartDao;
import com.jsp.dao.ProductDao;
import com.jsp.entity.Cart;
import com.jsp.entity.Product;

@Service
public class CartService {
	
	@Autowired
	private CartDao cartDao;
	@Autowired
	private ProductDao productDao;
	
	
	

	public void setCartDao(CartDao cartDao) {
		this.cartDao = cartDao;
	}

	

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	Cart cart= new Cart();
	Product product = new Product();
	Scanner input = new Scanner(System.in);
	
	

	public void createCart() {
		if(cart!=null) {
		String messege = cartDao.createCart(cart);
		System.out.println(messege);
		}
		
	}

	public void addProductToCart() {
		System.out.println("Enter any Number to view Products");
		int products = input.nextInt();
		
		List<Product> allProducts = productDao.getAllProducts();
		for (Product product : allProducts) {
			System.out.println(product.getProductId() + " " + product.getProductName() + " " + product.getProductPrice()
					+ " " + product.getProductQuantity());
		}		
		
		System.out.println("Enter ProductId to add ");
		int productId=input.nextInt();
		System.out.println("Enter cartId to add that Product");
		int cartId=input.nextInt();
		if(cart!=null) {
			
			String messege = cartDao.addProductToCart(cart, cartId,productId);
			System.out.println(messege);
		}
		

	}
	public void removeProductFromCart() {
		System.out.println("Enter any Number to view Carts");
		int number = input.nextInt();
		cartDao.viewCarts(cart);
		
		
		System.out.println("Select CartId to view Products inside that Cart");
		int cartId=input.nextInt();
		cartDao.viewProductsByCartId(cartId);
		
		System.out.println("Enter product Id to remove");
		int productId=input.nextInt();
		cartDao.removeProductFromCart(cartId, productId);
		
	}

	
	
	
}
