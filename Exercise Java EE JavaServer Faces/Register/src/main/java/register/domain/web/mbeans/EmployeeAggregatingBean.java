package register.domain.web.mbeans;

import register.domain.models.serviceModels.EmployeeServiceModel;
import register.domain.services.EmployeeService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Named
@RequestScoped
public class EmployeeAggregatingBean {
	
	private EmployeeService employeeService;
	private String sum;
	private String average;
	
	public EmployeeAggregatingBean() {
	}
	
	@Inject
	public EmployeeAggregatingBean(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	public String getSum() {
		List<EmployeeServiceModel> employees = this.employeeService.getAllEmployees();
		return employees.stream()
				.map(EmployeeServiceModel::getSalary)
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO).toString();
	}
	
	public String getAverage() {
		List<EmployeeServiceModel> employees = this.employeeService.getAllEmployees();
		BigDecimal sum = employees.stream()
				.map(EmployeeServiceModel::getSalary)
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
		return sum.divide(BigDecimal.valueOf(employees.size()), RoundingMode.UNNECESSARY).toString();
	}
}
