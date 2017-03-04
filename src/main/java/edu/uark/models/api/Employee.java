package edu.uark.models.api;

import java.time.LocalDateTime;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import edu.uark.models.api.enums.EmployeeApiRequestStatus;
import edu.uark.models.entities.EmployeeEntity;

public class Employee {
	private UUID id;
	public UUID getId() {
		return this.id;
	}
	public Employee setId(UUID id) {
		this.id = id;
		return this;
	}
	
	private String lookupCode;
	public String getLookupCode() {
		return this.lookupCode;
	}
	public Employee setLookupCode(String lookupCode) {
		this.lookupCode = lookupCode;
		return this;
	}
	
	private int count;
	public int getCount() {
		return this.count;
	}
	public Employee setCount(int count) {
		this.count = count;
		return this;
	}
	
	private LocalDateTime createdOn;
	public LocalDateTime getCreatedOn() {
		return this.createdOn;
	}
	public Employee setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
		return this;
	}
	
	private EmployeeApiRequestStatus apiRequestStatus;
	public EmployeeApiRequestStatus getApiRequestStatus() {
		return this.apiRequestStatus;
	}
	public Employee setApiRequestStatus(EmployeeApiRequestStatus apiRequestStatus) {
		if (this.apiRequestStatus != apiRequestStatus) {
			this.apiRequestStatus = apiRequestStatus;
		}
		
		return this;
	}
	
	private String apiRequestMessage;
	public String getApiRequestMessage() {
		return this.apiRequestMessage;
	}
	public Employee setApiRequestMessage(String apiRequestMessage) {
		if (!StringUtils.equalsIgnoreCase(this.apiRequestMessage, apiRequestMessage)) {
			this.apiRequestMessage = apiRequestMessage;
		}
		
		return this;
	}
	
	public Employee() {
		this.count = -1;
		this.lookupCode = "";
		this.id = new UUID(0, 0);
		this.createdOn = LocalDateTime.now();
		this.apiRequestMessage = StringUtils.EMPTY;
		this.apiRequestStatus = EmployeeApiRequestStatus.OK;
	}
	
	public Employee(EmployeeEntity EmployeeEntity) {
		this.id = EmployeeEntity.getId();
		this.count = EmployeeEntity.getCount();
		this.createdOn = EmployeeEntity.getCreatedOn();
		this.lookupCode = EmployeeEntity.getLookupCode();

		this.apiRequestMessage = StringUtils.EMPTY;
		this.apiRequestStatus = EmployeeApiRequestStatus.OK;
	}
}
