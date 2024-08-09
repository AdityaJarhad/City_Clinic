package com.app.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;   //To solve boilerplate code issue

@MappedSuperclass //class level run time annotation
//(meant for hibernate) -- to tell hibernate following is super 
//class , from which all other entities will inherit , 
//don't create any table

@Getter
@Setter
public class BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)   //Only applied for primary key, where Identity represent provider must assign PK.
	private Long id;
}
