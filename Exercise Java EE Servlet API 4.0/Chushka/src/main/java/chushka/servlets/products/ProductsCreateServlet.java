package chushka.servlets.products;

import chushka.domain.models.services.ProductServiceModel;
import chushka.service.ProductService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/products/create")
public class ProductsCreateServlet extends HttpServlet {
	
	private final ProductService productService;
	
	@Inject
	public ProductsCreateServlet(ProductService productService) {
		this.productService = productService;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("productsCreate.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProductServiceModel productServiceModel = new ProductServiceModel();
		
		productServiceModel.setName(req.getParameter("name"));
		productServiceModel.setDescription(req.getParameter("description"));
		productServiceModel.setType(req.getParameter("type"));
		
		this.productService.saveProduct(productServiceModel);
		resp.sendRedirect("/");
	}
}
