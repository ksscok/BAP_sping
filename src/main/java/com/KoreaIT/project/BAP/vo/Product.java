package com.KoreaIT.project.BAP.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	private int id;
	private String regDate;
	private String updateDate;
	private String name;
	private String address;
	private String roopType;
	private int numberOfRooms;
	private int hostId;
	private int fee;
	
	private int extra__minFee;
}
