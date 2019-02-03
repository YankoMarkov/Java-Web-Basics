package chushka.servlets;

import chushka.domain.models.views.AllProductViewModel;
import chushka.service.ProductService;
import chushka.util.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/")
public class HomeServlet extends HttpServlet {
	
	private final ProductService productService;
	private final ModelMapper modelMapper;
	
	@Inject
	public HomeServlet(ProductService productService, ModelMapper modelMapper) {
		this.productService = productService;
		this.modelMapper = modelMapper;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<AllProductViewModel> allProducts = this.productService.getAllProducts().stream()
				.map(product -> this.modelMapper.map(product, AllProductViewModel.class))
				.collect(Collectors.toList());
		this.getServletContext().setAttribute("products", allProducts);
		req.getRequestDispatcher("home.jsp").forward(req, resp);
	}
}
