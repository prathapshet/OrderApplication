package com.zkteco.order.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdersDTO {

	private String orderId;

	@NotNull(message = "Please Add the Name")
	private String orderName;

	@NotNull(message = "Please Add the Address!")
	private String orderAddress;

	@NotNull(message = "Please Add the Code!")
	private String orderCode;

	@NotNull(message = "Please Add the Quantity!")
	private String orderQty;

	@NotNull(message = "Please Add the Quality!")
	private String orderQlty;

	@NotNull(message = "Please Add the Price!")
	private String orderPrice;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Created_Date")
	private Date createdDate = new Date();

	@Column(name = "Updated_Date")
	private Date updatedDate = new Date();

}
