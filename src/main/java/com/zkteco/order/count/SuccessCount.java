package com.zkteco.order.count;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuccessCount {

	private int successCount;
	private Object success;
}
