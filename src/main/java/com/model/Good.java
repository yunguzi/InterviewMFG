package com.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Good")
public class Good {

	@Id
    private String name;

    private Integer age;

	private Date productionDate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}
	
}
