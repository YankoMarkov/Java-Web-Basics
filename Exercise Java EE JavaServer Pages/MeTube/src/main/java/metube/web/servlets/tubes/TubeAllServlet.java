package metube.web.servlets.tubes;

import metube.domain.models.viewModels.TubeAllViewModel;
import metube.services.TubeService;
import metube.util.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/tubes/all")
public class TubeAllServlet extends HttpServlet {
	
	private final TubeService tubeService;
	private final ModelMapper modelMapper;
	
	@Inject
	public TubeAllServlet(TubeService tubeService, ModelMapper modelMapper) {
		this.tubeService = tubeService;
		this.modelMapper = modelMapper;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<TubeAllViewModel> tubeAllViewModels = this.tubeService.getAllTubes().stream()
				.map(tube -> this.modelMapper.map(tube, TubeAllViewModel.class))
				.collect(Collectors.toList());
		this.getServletContext().setAttribute("tubes", tubeAllViewModels);
		req.getRequestDispatcher("tubeAll.jsp").forward(req, resp);
	}
}
