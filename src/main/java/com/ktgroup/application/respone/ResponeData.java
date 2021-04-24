package com.ktgroup.application.respone;

import java.util.HashMap;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponeData {
	private String status;
	private String message;
	private Object result;

	public ResponeData() {
		result = new HashMap<>();
	}
}
