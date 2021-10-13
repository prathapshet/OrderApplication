package com.zkteco.order.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zkteco.order.dto.OrdersDTO;
import com.zkteco.order.entity.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, String> {

	Orders findByOrderNameIgnoreCase(String orderName);

	@Query(value = "Select t from Orders t where t.orderName like ?1 OR t.orderAddress like ?1 OR t.orderCode like ?1 OR t.orderQty like ?1 OR t.orderQlty like ?1 OR t.orderPrice like ?1")
	Page<Orders> orderContaining(String search, Pageable paging);

	Orders save(OrdersDTO ordersdto);


}
