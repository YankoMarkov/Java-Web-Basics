package fdmc.web.beans;

import fdmc.models.viewModels.CatAllViewModel;
import fdmc.services.CatService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class CatAllBean {

    private List<CatAllViewModel> allCats;
    private CatService catService;
    private ModelMapper modelMapper;

    public CatAllBean() {
    }

    @Inject
    public CatAllBean(CatService catService, ModelMapper modelMapper) {
        this.catService = catService;
        this.modelMapper = modelMapper;
        this.allCats = this.catService.getAllCats().stream()
                .map(cat -> this.modelMapper.map(cat, CatAllViewModel.class))
                .collect(Collectors.toList());
    }

    public List<CatAllViewModel> getAllCats() {
        return allCats;
    }

    public void setAllCats(List<CatAllViewModel> allCats) {
        this.allCats = allCats;
    }
}
