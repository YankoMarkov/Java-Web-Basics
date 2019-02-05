package metube.web.servlets.tubes;

import metube.domain.models.viewModels.TubeDetailsViewModel;
import metube.services.TubeService;
import metube.util.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tubes/details")
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
		String name = req.getParameter("name");
		TubeDetailsViewModel tube =
				this.modelMapper.map(this.tubeService.getTubeByName(name), TubeDetailsViewModel.class);
		this.getServletContext().setAttribute("tubes", tube);
		req.getRequestDispatcher("tubeDetails.jsp").forward(req, resp);
	}
}
