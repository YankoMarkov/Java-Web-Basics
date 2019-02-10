package metube.web.servlets.tubes;

import metube.domain.models.bindingModels.TubeCreateBindingModel;
import metube.domain.models.serviceModels.TubeServiceModel;
import metube.domain.models.serviceModels.UserServiceModel;
import metube.services.TubeService;
import metube.services.UserService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tube/upload")
public class TubeCreateServlet extends HttpServlet {
	
	private final TubeService tubeService;
	private final UserService userService;
	private final ModelMapper modelMapper;
	
	@Inject
	public TubeCreateServlet(TubeService tubeService, UserService userService, ModelMapper modelMapper) {
		this.tubeService = tubeService;
		this.userService = userService;
		this.modelMapper = modelMapper;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/tubes/tubeCreate.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TubeCreateBindingModel tubeCreateBindingModel = (TubeCreateBindingModel) req.getAttribute("tubeCreateModel");
		TubeServiceModel tubeServiceModel = this.modelMapper.map(tubeCreateBindingModel, TubeServiceModel.class);
		UserServiceModel userServiceModel = this.userService.getUserByUsername((req.getSession().getAttribute("user")).toString());
		
		tubeServiceModel.setYouTubeLink(tubeCreateBindingModel.getYouTubeLink().split("=")[1]);
		tubeServiceModel.setUploader(userServiceModel);
		
		if (this.tubeService.saveTube(tubeServiceModel)) {
			resp.sendRedirect("/home");
			return;
		}
		resp.sendRedirect("/tube/upload");
	}
}
