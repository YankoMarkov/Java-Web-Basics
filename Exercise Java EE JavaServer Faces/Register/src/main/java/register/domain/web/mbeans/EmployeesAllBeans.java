package register.domain.web.mbeans;

import org.modelmapper.ModelMapper;
import register.domain.models.viewModels.EmployeesAllViewModel;
import register.domain.services.EmployeeService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class EmployeesAllBeans {
	
	private List<EmployeesAllViewModel> employeesAllViewModels;
	private EmployeeService employeeService;
	private ModelMapper modelMapper;
	
	public EmployeesAllBeans() {
	}
	
	@Inject
	public EmployeesAllBeans(EmployeeService employeeService, ModelMapper modelMapper) {
		this.employeeService = employeeService;
		this.modelMapper = modelMapper;
		this.employeesAllViewModels = this.employeeService.getAllEmployees().stream()
				.map(employee -> this.modelMapper.map(employee, EmployeesAllViewModel.class))
				.collect(Collectors.toList());
	}
	
	public List<EmployeesAllViewModel> getEmployeesAllViewModels() {
		return employeesAllViewModels;
	}
	
	public void setEmployeesAllViewModels(List<EmployeesAllViewModel> employeesAllViewModels) {
		this.employeesAllViewModels = employeesAllViewModels;
	}
	
	public void delete(String id) throws IOException {
		this.employeeService.deleteEmployee(id);
		
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		context.redirect("/");
	}
}
