package metube.web.servlets.tubes;

import metube.domain.models.serviceModels.TubeServiceModel;
import metube.domain.models.viewModels.tubes.TubeDetailsViewModel;
import metube.services.TubeService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tube/details")
public class TubeDetailsServlet extends HttpServlet {
	
	private final TubeService tubeService;
	private final ModelMapper modelMapper;
	
	@Inject
	public TubeDetailsServlet(TubeService tubeService, ModelMapper modelMapper) {
		this.tubeService = tubeService;
		this.modelMapper = modelMapper;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String tubeId = req.getParameter("tubeId");
		TubeServiceModel tubeServiceModel = this.tubeService.getTubeById(tubeId);
		tubeServiceModel.setViews(tubeServiceModel.getViews() + 1);
		
		TubeDetailsViewModel tubeViewModel =
				this.modelMapper.map(tubeServiceModel, TubeDetailsViewModel.class);
		
		this.tubeService.updateTube(tubeServiceModel);
		
		req.setAttribute("tube", null);
		req.setAttribute("tube", tubeViewModel);
		req.getRequestDispatcher("/tubes/tubeDetails.jsp").forward(req, resp);
	}
}
