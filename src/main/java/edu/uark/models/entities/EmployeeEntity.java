package edu.uark.models.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import edu.uark.dataaccess.entities.BaseEntity;
import edu.uark.models.api.Employee;
import edu.uark.models.entities.fieldnames.EmployeeFieldNames;
import edu.uark.models.repositories.EmployeeRepository;

public class EmployeeEntity extends BaseEntity<EmployeeEntity> {
	@Override
	protected void fillFromRecord(ResultSet rs) throws SQLException {
		this.lookupCode = rs.getString(EmployeeFieldNames.LOOKUP_CODE);
		this.count = rs.getInt(EmployeeFieldNames.COUNT);
		this.createdOn = rs.getTimestamp(EmployeeFieldNames.CREATED_ON).toLocalDateTime();
	}

	@Override
	protected Map<String, Object> fillRecord(Map<String, Object> record) {
		record.put(EmployeeFieldNames.LOOKUP_CODE, this.lookupCode);
		record.put(EmployeeFieldNames.COUNT, this.count);
		record.put(EmployeeFieldNames.CREATED_ON, Timestamp.valueOf(this.createdOn));
		
		return record;
	}

	private String lookupCode;
	public String getLookupCode() {
		return this.lookupCode;
	}
	public EmployeeEntity setLookupCode(String lookupCode) {
		if (!StringUtils.equals(this.lookupCode, lookupCode)) {
			this.lookupCode = lookupCode;
			this.propertyChanged(EmployeeFieldNames.LOOKUP_CODE);
		}
		
		return this;
	}

	private int count;
	public int getCount() {
		return this.count;
	}
	public EmployeeEntity setCount(int count) {
		if (this.count != count) {
			this.count = count;
			this.propertyChanged(EmployeeFieldNames.COUNT);
		}
		
		return this;
	}

	private LocalDateTime createdOn;
	public LocalDateTime getCreatedOn() {
		return this.createdOn;
	}
	
	public Employee synchronize(Employee apiEmployee) {
		this.setCount(apiEmployee.getCount());
		this.setLookupCode(apiEmployee.getLookupCode());
		
		apiEmployee.setCreatedOn(this.createdOn);
		
		return apiEmployee;
	}
	
	public EmployeeEntity() {
		super(new EmployeeRepository());
		
		this.count = -1;
		this.lookupCode = StringUtils.EMPTY;
		this.createdOn = LocalDateTime.now();
	}
	
	public EmployeeEntity(UUID id) {
		super(id, new EmployeeRepository());
		
		this.count = -1;
		this.lookupCode = StringUtils.EMPTY;
		this.createdOn = LocalDateTime.now();
	}

	public EmployeeEntity(Employee apiEmployee) {
		super(apiEmployee.getId(), new EmployeeRepository());
		
		this.count = apiEmployee.getCount();
		this.lookupCode = apiEmployee.getLookupCode();

		this.createdOn = LocalDateTime.now();
	}
}
