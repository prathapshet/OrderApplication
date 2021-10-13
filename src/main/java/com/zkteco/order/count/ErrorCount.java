package com.zkteco.order.count;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorCount {

	private int errorCount;
	private Object error;
}
