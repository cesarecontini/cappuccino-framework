package com.github.cesarecontini.cappuccino.framework.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CappuccinoErrorMessage {
	private String fieldName;
	private String message;
}
