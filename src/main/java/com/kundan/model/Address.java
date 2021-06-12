package com.kundan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; 
     
    @Column(name = "house_number", nullable = false)
    private Integer houseNumber;
     
    @Column(name = "street_address", nullable = false)
    private String streetAddress;
     
    @Column(name = "city", nullable = false)
    private String city;
     
    @Column(name = "state", nullable = false)
    private String state;
     
    @Column(name = "zip_code", nullable = false)
    private String zipCode;

}
