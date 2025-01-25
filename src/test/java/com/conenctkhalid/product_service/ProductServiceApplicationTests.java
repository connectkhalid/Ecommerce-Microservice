package com.conenctkhalid.product_service;

import  io.restassured.RestAssured;
import io.restassured.response.Response;
import  org.hamcrest.Matchers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

	@ServiceConnection
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setUp() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	static {
		mongoDBContainer.start();
	}

	@Test
	void shouldCreateProduct() {
		String requestBody = """
                {
                    "name": "Toothpaste",
                    "description": "Colgate Toothpaste",
                    "price": 120
                }
                """;

//		expected response:
//		{
//			"statusCode": 200,
//				"message": "SUCCESSFULLY_CREATED",
//				"details": {
//					"id": "67953544e4d9df33fc80c6c9",
//					"name": "phone",
//					"description": "iphone",
//					"price": 120
//					}
//		}

		 RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/api/private/create-product")
				.then()
				.statusCode(200)
				.body("details.id", Matchers.notNullValue()) // Ensure `id` inside `details` is not null
				.body("details.name", Matchers.equalTo("Toothpaste")) // Validates the name
				.body("details.description", Matchers.equalTo("Colgate Toothpaste")) // Validates the description
				.body("details.price", Matchers.equalTo(120)); // Validates the price
	}
}
