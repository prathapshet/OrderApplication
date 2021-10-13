package com.zkteco.order.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Orders {

	@Id

	@GeneratedValue(generator = "system-uuid")

	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "order_Id")
	private String id;

	@Column(name = "Order_Name")
	private String orderName;

	@Column(name = "Order_Address")
	private String orderAddress;

	@Column(name = "Order_Code")
	private String orderCode;

	@Column(name = "Order_Quantity")
	private String orderQty;

	@Column(name = "Order_Quality")
	private String orderQlty;

	@Column(name = "Order_Price")
	private String orderPrice;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Created_Date")
	private Date createdDate = new Date();

	@Column(name = "Updated_Date")
	private Date updatedDate = new Date();

}
