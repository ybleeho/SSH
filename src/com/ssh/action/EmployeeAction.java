package com.ssh.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.ssh.entities.Employee;
import com.ssh.service.DepartmentService;
import com.ssh.service.EmployeeService;

public class EmployeeAction extends ActionSupport implements RequestAware,ModelDriven<Employee>,Preparable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EmployeeService employeeService;
	private Map<String,Object> request;
	private Integer id;
    private DepartmentService departmentService;
    public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.request =arg0;
	}
	private InputStream inputStream;
	public InputStream getInputStream() {
		return inputStream;
	}
	public String list(){
		request.put("employees", employeeService.getAll());
		return "list";
	}	
	public String delete(){
		
		try {
			employeeService.delete(id);
			inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
		} catch (Exception e) {
			// TODO: handle exception
			try {
				inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return "ajax-validate";
	}
	public String input(){
		request.put("departments", departmentService.getAll());
		return INPUT;
	}
    public void prepareInput(){
    	if(id!=null){
    		model =employeeService.getEmployee(id);
    	}
    }
	private Employee model;
	@Override
	public Employee getModel() {
		// TODO Auto-generated method stub
		return model;
	}

	@Override
	public void prepare() throws Exception {}
	public String add(){
		if(id==null){
			model.setCreateTime(new Date());
		}
		employeeService.saveOrUpdate(model);
		return "sucess";
	}
	public void prepareAdd(){
		if(id==null){
			model =new Employee();
		}else{
			model =employeeService.getEmployee(id);
		}
		
	}
	private String lastName;
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String validateLastName() throws UnsupportedEncodingException{
		if(employeeService.getEmployeeByLastName(lastName)){
			inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
		}else{
			inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
		}
		return "ajax-validate";
	}
	
}
