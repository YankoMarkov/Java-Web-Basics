package register.domain.services;

import register.domain.models.serviceModels.EmployeeServiceModel;

import java.util.List;

public interface EmployeeService {
	
	void saveEmployee(EmployeeServiceModel employeeService);
	
	List<EmployeeServiceModel> getAllEmployees();
	
	EmployeeServiceModel getEmployeeById(String id);
	
	void deleteEmployee(String id);
}
