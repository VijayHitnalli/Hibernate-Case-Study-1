package com.jsp.service;

import java.util.List;
import java.util.Scanner;

import com.jsp.dao.ProductDao;
import com.jsp.entity.Product;

public class ProductService {

	private ProductDao productDao;
	

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	Scanner input = new Scanner(System.in);

	public void addProduct() {
		System.out.println("Enter product name");
		String name = input.next();

		System.out.println("Enter product price");
		double price = input.nextDouble();

		System.out.println("Enter product quantiry");
		int quantity = input.nextInt();

		Product product = new Product();
		product.setProductName(name);
		product.setProductPrice(price);
		product.setProductQuantity(quantity);

		String messege = productDao.addProduct(product);
		System.out.println(messege);
	}

	public void getProductById() {
		System.out.println("Enter the productId");
		int id = input.nextInt();
		Product product = productDao.getProductById(id);
		System.out.println(
				product.getProductName() + " " + product.getProductPrice() + " " + product.getProductQuantity());

	}

	public void getAllProduct() {
		List<Product> allProducts = productDao.getAllProducts();
		for (Product product : allProducts) {
			System.out.println(product.getProductId() + " " + product.getProductName() + " " + product.getProductPrice()
					+ " " + product.getProductQuantity());
		}
	}

	public void updateProduct() {
		System.out.println("Enter the productId to update");
		int id = input.nextInt();

		Product product = productDao.getProductById(id);

		System.out.println("Enter product Name");
		String name = input.next();

		System.out.println("Enter product price");
		double price = input.nextDouble();

		System.out.println("Enter product quantity");
		int quantity = input.nextInt();

		product.setProductName(name);
		product.setProductPrice(price);
		product.setProductQuantity(quantity);

		String messege = productDao.updateProduct(product);
		System.out.println(messege);
	}

	public void deleteProduct() {
		System.out.println("Enter ProductId to DELETE");
		int id = input.nextInt();
		Product product = productDao.getProductById(id);
		String messege = productDao.deleteProduct(product);
		System.out.println(messege);
	}

}
