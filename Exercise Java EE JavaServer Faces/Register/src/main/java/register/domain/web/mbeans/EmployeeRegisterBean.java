package register.domain.web.mbeans;

import org.modelmapper.ModelMapper;
import register.domain.models.bindingModels.EmployeeRegisterBindingModel;
import register.domain.models.serviceModels.EmployeeServiceModel;
import register.domain.services.EmployeeService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.math.BigDecimal;

@Named
@RequestScoped
public class EmployeeRegisterBean {
	
	private EmployeeRegisterBindingModel employeeRegisterBindingModel;
	private EmployeeService employeeService;
	private ModelMapper modelMapper;
	
	public EmployeeRegisterBean() {
	}
	
	@Inject
	public EmployeeRegisterBean(EmployeeService employeeService, ModelMapper modelMapper) {
		this.employeeService = employeeService;
		this.modelMapper = modelMapper;
		this.employeeRegisterBindingModel = new EmployeeRegisterBindingModel();
	}
	
	public EmployeeRegisterBindingModel getEmployeeRegisterBindingModel() {
		return employeeRegisterBindingModel;
	}
	
	public void setEmployeeRegisterBindingModel(EmployeeRegisterBindingModel employeeRegisterBindingModel) {
		this.employeeRegisterBindingModel = employeeRegisterBindingModel;
	}
	
	public void register() throws IOException {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		if (employeeRegisterBindingModel.getFirstName().equals("") ||
				employeeRegisterBindingModel.getLastName().equals("") ||
				employeeRegisterBindingModel.getPosition().equals("") ||
				employeeRegisterBindingModel.getAge() == 0 ||
				employeeRegisterBindingModel.getSalary().equals(BigDecimal.ZERO)) {
			context.redirect("/");
			return;
		}
		this.employeeService
				.saveEmployee(this.modelMapper.map(this.employeeRegisterBindingModel, EmployeeServiceModel.class));
		context.redirect("/");
	}
}
