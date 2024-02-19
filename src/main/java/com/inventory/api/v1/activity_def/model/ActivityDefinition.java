package com.inventory.api.v1.activity_def.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
@Table (name = "activity_def", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class ActivityDefinition implements Serializable{

	/**{"id":1,"activity":"Received in Store Warehouse","description":null,"postedDateTime":"2024-02-12T22:19:23"}
	 * Serial Version
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String activity;
	private String description;
	private LocalDateTime postedDateTime;
	
	public ActivityDefinition() {}
	
	/**
	 * Constructor
	 * @param activity
	 * @param description
	 * @param postedDateTime
	 */
	public ActivityDefinition(String activity, String description, LocalDateTime postedDateTime) {
		this.activity = activity;
		this.description = description;
		this.postedDateTime = postedDateTime;
	}
	
	/**
	 * Return the entity id
	 * @return id
	 */
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	
	/**
	 * Set entity id
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * Return activity code
	 * @return activity code
	 */
	@Column(name = "activity")
	public String getActivity() {
		return activity;
	}
	/**
	 * Set activity code
	 * @param activity
	 */
	public void setActivity(String activity) {
		this.activity = activity;
	}
	
	/**
	 * Return description
	 * @return description
	 */
	@Column(name = "acivity_desc")
	public String getDescription() {
		return description;
	}
	
	/**
	 * Set description
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Get posted date
	 * @return date
	 */
	@Column(name = "time")
	public LocalDateTime getPostedDateTime() {
		return postedDateTime;
	}
	
	/**
	 * Set Posted Date and Time
	 * @param postedDateTime
	 */
	public void setPostedDateTime(LocalDateTime postedDateTime) {
		this.postedDateTime = postedDateTime;
	}                                                  
	
	/**
	 * Compare this object with that object
	 * @return comparison result (T                                                                                                                                                                                                                                                                                                             
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		
		ActivityDefinition that = (ActivityDefinition) o;
		
		return Objects.equals(id, that.getId());                                                                                                            
		
	}
	
	/**
	 * Return a list of Application Definitions
	 * @return list
	 */
	public List<Object> toList(){
		return new ArrayList<>(Arrays.asList(activity, description, postedDateTime));
	}
	
	
}
