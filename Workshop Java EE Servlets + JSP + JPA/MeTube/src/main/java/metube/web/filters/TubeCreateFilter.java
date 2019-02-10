package metube.web.filters;

import metube.domain.models.bindingModels.TubeCreateBindingModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/tube/upload")
public class TubeCreateFilter implements Filter {
	
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) servletRequest;
		HttpServletResponse resp = (HttpServletResponse) servletResponse;
		
		if (req.getMethod().equalsIgnoreCase("post")) {
			TubeCreateBindingModel tubeCreateBindingModel = new TubeCreateBindingModel();
			
			tubeCreateBindingModel.setTitle(req.getParameter("title"));
			tubeCreateBindingModel.setAuthor(req.getParameter("author"));
			tubeCreateBindingModel.setDescription(req.getParameter("description"));
			tubeCreateBindingModel.setYouTubeLink(req.getParameter("youtube-link"));
			
			req.setAttribute("tubeCreateModel", tubeCreateBindingModel);
		}
		
		filterChain.doFilter(req, resp);
	}
}
