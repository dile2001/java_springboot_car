package com.dilegent.cardatabase.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Owner {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long ownerId;
	private String firstName, lastName;
	public Owner() {
		
	}
	public Owner(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	/*
	 * The cascade attribute defines how cascading affects the entities in the case of deletions or updates. 
	 * The ALL attribute setting means that all operations are cascaded. 
	 * For example, if the owner is deleted, the cars that are linked to that owner are deleted as well. 
	 * The mappedBy="owner" attribute setting tells us that the Car class has the owner field, 
	 * which is the foreign key for this relationship.
	 * Next, you will learn how to change the relationship to many-to-many. 
	 * In a many-to-many relationship, it is recommended that you use Set instead of List with Hibernate:
	 */
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL, mappedBy="owner")
	private List<Car> cars;
	    
	public List<Car> getCars() {
	    return cars;
	}
	public void setCars(List<Car> cars) {
	    this.cars = cars;
	}

	public Long getOwnerId() {
		return ownerId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}