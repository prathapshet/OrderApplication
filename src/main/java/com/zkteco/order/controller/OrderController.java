package com.zkteco.order.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zkteco.order.dto.OrdersDTO;
import com.zkteco.order.dto.ResultDTO;
import com.zkteco.order.exception.OrderNotFoundException;
import com.zkteco.order.service.OrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/v1/orders")
@Api(value = "Order Application", description = "show orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping
	@ApiOperation(value = "Returns all the Order Resource")
	public ResultDTO findPaginated(@RequestParam(defaultValue = "0") int pagenumber,
			@RequestParam(defaultValue = "5") int pagesize) throws OrderNotFoundException {
		ResultDTO result = orderService.getAllOrder(pagenumber, pagesize);
		return result;

	}

	@GetMapping("{id}")
	@ApiOperation(value = "Returns the Order resource by ID")
	public ResultDTO fetchOrderById(@PathVariable("id") String orderId) throws OrderNotFoundException {
		return orderService.fetchOrderById(orderId);

	}

	@PostMapping
	@ApiOperation(value = "Fetch the Order Application data")
	public ResultDTO saveOrder(@Valid @RequestBody OrdersDTO orders, BindingResult resut)
			throws OrderNotFoundException {

		return orderService.saveOrder(orders, resut);
	}

	@PutMapping("{id}")
	@ApiOperation(value = "Update the Order resource by ID")
	public ResultDTO updateOrder(@PathVariable("id") String orderId, @RequestBody OrdersDTO orders)
			throws OrderNotFoundException {
		return orderService.updateOrder(orderId, orders);
	}

	@DeleteMapping
	@ApiOperation(value = "Deletes the Order resource by ID")
	public ResultDTO deleteOrderById(@RequestParam String orderId) throws OrderNotFoundException {
		return orderService.deleteOrderById(orderId);

	}
}
