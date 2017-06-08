package com.ssh.service;

import java.util.List;

import com.ssh.dao.EmployeeDao;
import com.ssh.entities.Employee;

public class EmployeeService {
	private EmployeeDao employeeDao;
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
    public List<Employee> getAll(){
    	return employeeDao.getAll();
    }
    public void delete(Integer id){
    	employeeDao.delete(id);
    }
    public void saveOrUpdate(Employee employee){
    	employeeDao.saveOrUpdate(employee);
    }
    public boolean getEmployeeByLastName(String lastName){
    	return employeeDao.getEmployeeByLastName(lastName)==null;
    }
    public Employee getEmployee(Integer id){
    	return employeeDao.getEmployee(id);
    }
}
