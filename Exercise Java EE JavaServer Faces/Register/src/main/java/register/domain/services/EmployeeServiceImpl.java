package register.domain.services;

import org.modelmapper.ModelMapper;
import register.domain.entities.Employee;
import register.domain.models.serviceModels.EmployeeServiceModel;
import register.domain.repository.EmployeeRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {
	
	private final EmployeeRepository employeeRepository;
	private final ModelMapper modelMapper;
	
	@Inject
	public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
		this.employeeRepository = employeeRepository;
		this.modelMapper = modelMapper;
	}
	
	@Override
	public void saveEmployee(EmployeeServiceModel employeeService) {
		Employee employee = this.modelMapper.map(employeeService, Employee.class);
		this.employeeRepository.save(employee);
	}
	
	@Override
	public List<EmployeeServiceModel> getAllEmployees() {
		List<Employee> employees = this.employeeRepository.findAll();
		if (employees == null) {
			return null;
		}
		return employees.stream()
				.map(employee -> this.modelMapper.map(employee, EmployeeServiceModel.class))
				.collect(Collectors.toList());
	}
	
	@Override
	public EmployeeServiceModel getEmployeeById(String id) {
		Employee employee = this.employeeRepository.findById(id);
		if (employee == null) {
			return null;
		}
		return this.modelMapper.map(employee, EmployeeServiceModel.class);
	}
	
	@Override
	public void deleteEmployee(String id) {
		this.employeeRepository.delete(id);
	}
}
