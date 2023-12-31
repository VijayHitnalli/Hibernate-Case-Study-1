package com.jsp;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jsp.dao.CartDao;
import com.jsp.dao.ProductDao;
import com.jsp.entity.Product;
import com.jsp.service.CartService;
import com.jsp.service.ProductService;

/**
 * Hello world!
 *
 */


@Configuration
@ComponentScan(basePackages = {"com.jsp"})
public class App {

	public static void main(String[] args) {
		

		System.out.println("Enter\n" + "1 to add Product\n" + "2 to get getProductById\n" + "3 to get All Product\n"
				+ "4 to update Product\n" + "5 to delete Product\n" + "6 to create Cart\n"
				+ "7 to add Product To Cart\n" +"8 to remove Product from"
						+ " Cart\n"+ "0 to terminate");
		Scanner input = new Scanner(System.in);
		int choice = input.nextInt();

		ApplicationContext applicationContext=new AnnotationConfigApplicationContext(App.class);
		CartService cartService = applicationContext.getBean(CartService.class);
		ProductService productService = applicationContext.getBean(ProductService.class);
		switch (choice) {
		case 1:
			productService.addProduct();
			break;
		case 2:
			productService.getProductById();
			break;
		case 3:
			productService.getAllProduct();
			break;
		case 4:
			productService.updateProduct();
			break;
		case 5:
			productService.deleteProduct();
			break;
		case 6:
			cartService.createCart();
			break;
		case 7:
			cartService.addProductToCart();
			break;
		case 8:
			cartService.removeProductFromCart();
			break;
		
		case 0:
			System.err.println("Programme terminated");
			break;
		default:
			System.err.println("Invali input");
			break;
		}

	}
}
