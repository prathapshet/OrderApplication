package com.zkteco.order.service;

import java.util.List;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import com.zkteco.order.dto.OrdersDTO;
import com.zkteco.order.dto.ResultDTO;
import com.zkteco.order.entity.Orders;
import com.zkteco.order.exception.OrderNotFoundException;

public interface OrderService {

	public OrdersDTO entityToDto(Orders order);

	public List<OrdersDTO> entityToDto(List<Orders> ord);

	public List<Orders> dtoToEntity(List<OrdersDTO> dto);

	public Orders dtoToEntity(OrdersDTO dto);

	public ResultDTO saveOrder(@Valid OrdersDTO orders, BindingResult resut) throws OrderNotFoundException;

	public ResultDTO fetchOrderById(String orderId) throws OrderNotFoundException;

	public ResultDTO deleteOrderById(String orderId) throws OrderNotFoundException;

	public ResultDTO updateOrder(String orderId, OrdersDTO orders) throws OrderNotFoundException;

	public ResultDTO getAllOrder(int pagenumber, int pagesize);

}
