package com.bs.spring.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Snack {
	private String name;
	private int price;
	private double weight;
	private double discount;
	private Person p;
}
