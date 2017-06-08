package com.ssh.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ssh.entities.Employee;

public class EmployeeDao extends BaseDao{

	public List<Employee> getAll(){
		String hql=" FROM Employee e Left outer join fetch e.department";
		
         Session s =getSession();
        
		return s.createQuery(hql).list();

	}
	public void delete(Integer id){
		String hql="delete from Employee where id=?";
		getSession().createQuery(hql).setInteger(0, id).executeUpdate();
	}
	public void saveOrUpdate(Employee employee){
		getSession().saveOrUpdate(employee);
	}
	public Employee getEmployeeByLastName(String lastName){
		String hql="From Employee e where lastName=?";
		Query query =getSession().createQuery(hql);
		query.setString(0, lastName);
		return (Employee) query.uniqueResult();
		
	}
	public Employee getEmployee(Integer id){
		return (Employee) getSession().get(Employee.class,id);
		
	}

}
