package fdmc.models.bindingModels;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class CatCreateBindingModel {
	
	private String name;
	private String breed;
	private String color;
	private Integer age;
	private String gender;
	private BigDecimal price;
	private Date addedOn;
	private boolean hasPassport;
	
	@NotNull(message = "Name cannot be null")
	@Length(min = 2, max = 10, message = "Length must be between 2 and 10 symbols!")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@NotNull(message = "Breed cannot be null")
	@Length(min = 5, max = 20, message = "Length must be between 5 and 20 symbols!")
	public String getBreed() {
		return breed;
	}
	
	public void setBreed(String breed) {
		this.breed = breed;
	}
	
	@NotNull(message = "Color cannot be null")
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	@NotNull(message = "Age cannot be null")
	@Min(value = 1, message = "Number must be minimum 1")
	@Max(value = 31, message = "Number must be maximum 31")
	public Integer getAge() {
		return age;
	}
	
	public void setAge(Integer age) {
		this.age = age;
	}
	
	@NotNull(message = "Gender cannot be null")
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@NotNull(message = "Price cannot be null")
	@DecimalMin(value = "0.01", message = "Price must be minimum 0.01!")
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	@NotNull(message = "AddedOn cannot be null")
	public Date getAddedOn() {
		return addedOn;
	}
	
	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}
	
	@NotNull(message = "HasPassport cannot be null")
	public boolean isHasPassport() {
		return hasPassport;
	}
	
	public void setHasPassport(boolean hasPassport) {
		this.hasPassport = hasPassport;
	}
}
