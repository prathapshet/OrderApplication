package com.zkteco.order.service.impl;

import java.util.ArrayList;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import com.zkteco.order.count.ErrorCount;
import com.zkteco.order.count.SuccessCount;
import com.zkteco.order.dto.OrdersDTO;
import com.zkteco.order.dto.ResultDTO;
import com.zkteco.order.entity.Orders;
import com.zkteco.order.exception.OrderNotFoundException;
import com.zkteco.order.repository.OrderRepository;
import com.zkteco.order.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderService orderService;

	public OrdersDTO entityToDto(Orders ord) {

		OrdersDTO dto = new OrdersDTO();
		dto.setOrderId(ord.getId());
		dto.setOrderName(ord.getOrderName());
		dto.setOrderAddress(ord.getOrderAddress());
		dto.setOrderCode(ord.getOrderCode());
		dto.setOrderPrice(ord.getOrderPrice());
		dto.setOrderQlty(ord.getOrderQlty());
		dto.setOrderQty(ord.getOrderQty());
		return dto;
	}

	public List<OrdersDTO> entityToDto(List<Orders> ord) {
		return ord.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
	}

	public Orders dtoToEntity(OrdersDTO dto) {
		Orders ord = new Orders();
		ord.setId(dto.getOrderId());
		ord.setOrderName(dto.getOrderName());
		ord.setOrderAddress(dto.getOrderAddress());
		ord.setOrderCode(dto.getOrderCode());
		ord.setOrderPrice(dto.getOrderPrice());
		ord.setOrderQlty(dto.getOrderQlty());
		ord.setOrderQty(dto.getOrderQty());
		return ord;
	}

	public List<Orders> dtoToEntity(List<OrdersDTO> dto) {
		return dto.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
	}

	@Override
	public ResultDTO getAllOrder(int pagenumber, int pagesize) {

		Pageable page = PageRequest.of(pagenumber, pagesize);
		Page<Orders> pages = orderRepository.findAll(page);

		List<Orders> ord = new ArrayList<Orders>();
		for (Orders o : pages) {
			ord.add(o);
		}

		ResultDTO result = new ResultDTO();

		result.setCode("ORD-01");
		result.setMessage("Order resource updated Successfully");
		result.setData(ord);
		return result;

	}

	@Override
	public ResultDTO fetchOrderById(String orderId) throws OrderNotFoundException {

		Optional<Orders> orElse = orderRepository.findById(orderId);

		if (!orElse.isPresent()) {
			throw new OrderNotFoundException("Orders Not Available");
		}

		Orders ord = orElse.get();

		OrdersDTO dto = orderService.entityToDto(ord);
		ResultDTO result = new ResultDTO();
		result.setCode("ODR-01");
		result.setMessage("One Order resource created successfully");
		result.setData(dto);

		return result;
	}

	@Override
	public ResultDTO saveOrder(OrdersDTO dto, BindingResult bindingResult) throws OrderNotFoundException {
		ResultDTO result = new ResultDTO();
		System.err.println("bindingResult===" + bindingResult.getErrorCount());
		result.setCode("ODR-01");
		result.setMessage("Fields should not be null");

		if (bindingResult.getErrorCount() > 0) {
			result.setData(bindingResult.getAllErrors());
			return result;
		}

		Orders order = orderService.dtoToEntity(dto);
		order = orderRepository.save(order);
		OrdersDTO orddto = orderService.entityToDto(order);

		result.setCode("ODR-01");
		result.setMessage("Order resource created successfully");
		result.setData(orddto);
		return result;
	}

	@Override
	public ResultDTO updateOrder(String orderId, OrdersDTO orders) throws OrderNotFoundException {

		Optional<Orders> ord = orderRepository.findById(orderId);

		if (!ord.isPresent()) {
			throw new OrderNotFoundException("Orders Not Available");
		}
		Orders ordDB = ord.get();

		if (Objects.nonNull(orders.getOrderName()) && !"".equalsIgnoreCase(orders.getOrderName())) {
			ordDB.setOrderName(orders.getOrderName());
		}

		if (Objects.nonNull(orders.getOrderAddress()) && !"".equalsIgnoreCase(orders.getOrderAddress())) {
			ordDB.setOrderAddress(orders.getOrderAddress());
		}

		if (Objects.nonNull(orders.getOrderCode()) && !"".equalsIgnoreCase(orders.getOrderCode())) {
			ordDB.setOrderCode(orders.getOrderCode());
		}

		if (Objects.nonNull(orders.getOrderQty()) && !"".equalsIgnoreCase(orders.getOrderQty())) {
			ordDB.setOrderQty(orders.getOrderQty());
		}

		if (Objects.nonNull(orders.getOrderQlty()) && !"".equalsIgnoreCase(orders.getOrderQlty())) {
			ordDB.setOrderQlty(orders.getOrderQlty());
		}

		if (Objects.nonNull(orders.getOrderPrice()) && !"".equalsIgnoreCase(orders.getOrderPrice())) {
			ordDB.setOrderPrice(orders.getOrderPrice());
		}

		Orders or = orderRepository.save(ordDB);
		OrdersDTO dto = orderService.entityToDto(or);
		ResultDTO result = new ResultDTO();

		result.setCode("ORD-01");
		result.setMessage("Order resource updated Successfully");
		result.setData(dto);
		return result;

	}

	@Override
	public ResultDTO deleteOrderById(String orderId) throws OrderNotFoundException {

		String[] str = orderId.split(",");
		int successCount = 0;
		int errorCount = 0;
		ResultDTO result = new ResultDTO();
		List<SuccessCount> sct = new ArrayList<SuccessCount>();
		SuccessCount scount = new SuccessCount();
		ErrorCount ecount = new ErrorCount();
		List<ResultDTO> rest = new ArrayList<ResultDTO>();
		ArrayList<String> lst = new ArrayList<String>();
		for (String ids : str) {

			if (orderRepository.existsById(ids)) {
				orderRepository.deleteById(ids);
				lst.add(ids);
				successCount++;
				scount.setSuccessCount(successCount);
				scount.setSuccess(lst);
				sct.add(scount);
			}

			else {
				errorCount++;
				ecount.setErrorCount(errorCount);
				ecount.setError(str);
				List<ErrorCount> ect = new ArrayList<ErrorCount>();
				ect.add(ecount);
				ResultDTO resut = new ResultDTO();
				resut.setCode("ORD-EROR");
				resut.setMessage("Order Not Found!");
				resut.setData("id: " + ids);
				rest.add(resut);
				ecount.setError(rest);
			}

		}

		List<Object> object = new ArrayList<Object>();
		object.add(scount);
		object.add(ecount);
		result.setCode("ORD-01");
		result.setMessage("One or more Orders are not processed");
		result.setData(object);
		return result;
	}

}
