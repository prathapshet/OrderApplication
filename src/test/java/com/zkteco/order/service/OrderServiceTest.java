package com.zkteco.order.service;

import java.util.ArrayList;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.zkteco.order.dto.OrdersDTO;
import com.zkteco.order.dto.ResultDTO;
import com.zkteco.order.entity.Orders;
import com.zkteco.order.exception.OrderNotFoundException;

@SpringBootTest
class OrderServiceTest {

	@Autowired
	private OrderService orderService;

	ResultDTO result = new ResultDTO();

	List<Orders> order = new ArrayList<>();

	OrdersDTO dto;

	@Test
	@DisplayName("Get Data based on Valid Order Id")
	void whenValidOrderId_thenOrderShouldFound() throws OrderNotFoundException {

		String orderId = "402894c87c794263017c79434c5b0000";
		result = orderService.fetchOrderById(orderId);
		assertEquals("One Order resource created successfully", result.getMessage());

	}

	@Test
	@DisplayName("If Id does not exist")
	void whenOrderIdDoesNotExist() throws OrderNotFoundException {

		Exception exception = assertThrows(OrderNotFoundException.class, () -> {
			orderService.fetchOrderById("402894c87c794263017c79434c5b00340");
		});

		String expectedMessage = "Orders Not Available";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	@DisplayName("Create Order")
	void testSaveOrders() throws Exception {

		OrdersDTO dto = new OrdersDTO();
		dto.setOrderId("402894c87c794263017c79434c5b0034009");
		dto.setOrderName("Mobile");
		dto.setOrderAddress("BLR");
		dto.setOrderCode("MB-01");

		dto.setOrderQty("1");
		dto.setOrderQlty("Good");
		dto.setOrderPrice("15000");

		result = orderService.saveOrder(dto);
		assertEquals("Order resource created successfully", result.getMessage());

	}

	@Test
	@DisplayName("All Orders Resource by page Number and Page Size")
	void testAllOrdersResource() throws OrderNotFoundException {
		int pagenumber = 0;
		int pagesize = 5;

		result = orderService.getAllOrder(pagenumber, pagesize);
		assertEquals("Order resource returns Successfully", result.getMessage());
	}

	@Test
	@DisplayName("Update Orders Resource when given valid Id")
	void testUpdateOrders() throws OrderNotFoundException {

		OrdersDTO orders = new OrdersDTO();
		orders.setOrderId("402894c87c794263017c79440d4a0002");
		orders.setOrderName("Pen");
		orders.setOrderAddress("BLR");
		orders.setOrderCode("PNL-01");
		orders.setOrderQty("10");
		orders.setOrderQlty("Good");
		orders.setOrderPrice("50");
		String orderId = "402894c87c794263017c79440d4a0002";
		result = orderService.updateOrder(orderId, orders);
		assertEquals("Order resource updated Successfully", result.getMessage());

	}

	@Test
	@DisplayName("Update Orders Resource when given Invalid Id")
	void testOrdersResource_ifIdInvalid() throws OrderNotFoundException {
		OrdersDTO orders = new OrdersDTO();
		orders.setOrderId("402894c87c794263017c79434c5b00340098");
		orders.setOrderName("Pen");
		orders.setOrderAddress("BLR");
		orders.setOrderCode("PNL-01");
		orders.setOrderQty("10");
		orders.setOrderQlty("Good");
		orders.setOrderPrice("50");
		String orderId = "402894c87c794263017c79434c5b00340098";

		Exception exception = assertThrows(OrderNotFoundException.class, () -> {
			orderService.updateOrder(orderId, orders);
		});

		String expectedMessage = "Orders Not Available";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	@DisplayName("Delete Orders Resource")
	void testDeleteOrdersResourceById() throws OrderNotFoundException {
		String orderId = "402894c87c794263017c79434c5b00340098";
		result = orderService.deleteOrderById(orderId);
		assertEquals("One or more Orders are not processed", result.getMessage());
	}

}
