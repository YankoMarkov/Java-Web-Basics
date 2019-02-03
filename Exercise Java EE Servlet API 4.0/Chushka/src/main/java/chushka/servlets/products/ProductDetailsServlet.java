package chushka.servlets.products;

import chushka.domain.models.views.ProductDetailsViewModel;
import chushka.service.ProductService;
import chushka.util.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/products/details")
public class ProductDetailsServlet extends HttpServlet {
	
	private final ProductService productService;
	private final ModelMapper modelMapper;
	
	@Inject
	public ProductDetailsServlet(ProductService productService, ModelMapper modelMapper) {
		this.productService = productService;
		this.modelMapper = modelMapper;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		ProductDetailsViewModel productDetailsViewModel =
				this.modelMapper.map(this.productService.getProductByName(name), ProductDetailsViewModel.class);
		this.getServletContext().setAttribute("product", productDetailsViewModel);
		req.getRequestDispatcher("productsDetails.jsp").forward(req, resp);
	}
}
