package com.zkteco.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;

import com.zkteco.order.dto.OrdersDTO;
import com.zkteco.order.dto.ResultDTO;
import com.zkteco.order.entity.Orders;
import com.zkteco.order.exception.OrderNotFoundException;
import com.zkteco.order.repository.OrderRepository;

@SpringBootTest
class OrderServiceTest {

	@Autowired
	private OrderService orderService;

	@MockBean
	private OrderRepository orderRepository;
	
	public List<Orders> ordr;

	Orders ord = new Orders();

	Optional<Orders> order = Optional.of(new Orders());
	
	OrdersDTO dto = new OrdersDTO();

	ResultDTO result = new ResultDTO();

    
	
	//Pageable page;

	@BeforeEach
	void setUp() throws Exception {

		order = Optional.of(Orders.builder()
				.id("402894c87c794263017c79440d4a000")
				.orderName("Bat")
				.orderAddress("BLR")
				.orderCode("BT-01")
				.orderQty("1")
				.orderQlty("Good")
				.orderPrice("2500")
				.build());
		
		 Mockito.when(orderRepository.findById("402894c87c794263017c79440d4a000")).
		  thenReturn(order);
			
		 
		 //save
			/*
			 * OrdersDTO dto = new OrdersDTO();
			 * dto.setOrderId("402894c87c794263017c79440d4a00009"); dto.setOrderName("Pen");
			 * dto.setOrderAddress("BLR"); dto.setOrderCode("PN-01"); dto.setOrderQty("50");
			 * dto.setOrderQlty("Good"); dto.setOrderPrice("1000");
			 */
		 
		 OrdersDTO orders = new OrdersDTO();
		 Orders.builder()
		       .id("402894c87c794263017c79440d4a000")
		       .orderName("Mobile")
		       .orderAddress("BLR")
		       .orderCode("MB-01")
		       .orderQty("1")
		       .orderQlty("Good")
		       .orderPrice("15000")
		       .build();
		 
		 Mockito.when(orderRepository.save(orders)).thenReturn(ord);
		 
	}

	@Test
	@DisplayName("Update If Valid Id")
	public void whenUpdateIfValidId() {
		
		String orderId = "402894c87c794263017c79440d4a000";
		result = orderService.updateOrder(orderId, orders);
		assertEquals(result.getMessage(), "Order resource updated Successfully");
		
	}
	
	/*
	 * @BeforeEach void setUp1() throws Exception {
	 * 
	 * ord = Orders.builder() .id("402894c87c794263017c79440d4a00") .orderName("Bt")
	 * .orderAddress("BLR") .orderCode("BT-01") .orderQty("1") .orderQlty("Good")
	 * .orderPrice("2500") .build();
	 * 
	 * Mockito.when(orderRepository.findAll(page)).thenReturn(pages);
	 * 
	 * }
	 */
 


	//fetchOrderById
	
	@Test
	@Order(1)
	@DisplayName("Get Data based on Valid Order Id")
	public void whenValidOrderId_thenOrderShouldFound() throws OrderNotFoundException {

		String orderId = "402894c87c794263017c79440d4a000";

		
		 
		result = orderService.fetchOrderById(orderId);
		assertEquals(result.getMessage(), "One Order resource created successfully");

	}

	@Test
	@DisplayName("If Id does not exist")
	public void whenOrderIdDoesNotExist() throws OrderNotFoundException {

		String orderId = "402894c87c794263017c79434c5b00340";
		result = orderService.fetchOrderById(orderId);

		assertEquals(result.getMessage(), "Orders Not Available");
 
	} 
 
	
	
	
	
	
	
	
	//Save order
	
	/*
	 * @Test
	 * 
	 * @DisplayName("Save Order Resource") public void saveOrderResource() {
	 * 
	 * BindingResult bindingResult; result = orderService.saveOrder(dto, resut);
	 * assertEquals(result.getMessage(), "Order resource created successfully"); }
	 */
	 
	
	//pagination
	
	
	/*
	 * @Test
	 * 
	 * @Order(2)
	 * 
	 * @DisplayName("Get Order resource by page number and page size")
	 * 
	 * public void whenValidPageNumberAndPageSizeGiven() {
	 * 
	 * 
	 * int pagenumber = 0; int pagesize = 5;
	 * 
	 * result = orderService.getAllOrder(pagenumber, pagesize);
	 * assertEquals(result.getMessage(), "Order resource returns Successfully"); }
	 */
	
	
}
