package fdmc.web.beans;

import fdmc.models.bindingModels.CatCreateBindingModel;
import fdmc.models.serviceModels.CatServiceModel;
import fdmc.services.CatService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class CatCreateBean {

    private CatCreateBindingModel catCreateBindingModel;
    private CatService catService;
    private ModelMapper modelMapper;

    public CatCreateBean() {
    }

    @Inject
    public CatCreateBean(CatService catService, ModelMapper modelMapper) {
        this.catService = catService;
        this.modelMapper = modelMapper;
        this.catCreateBindingModel = new CatCreateBindingModel();
    }

    public CatCreateBindingModel getCatCreateBindingModel() {
        return catCreateBindingModel;
    }

    public void setCatCreateBindingModel(CatCreateBindingModel catCreateBindingModel) {
        this.catCreateBindingModel = catCreateBindingModel;
    }

    public void create() throws IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        this.catService.saveCat(this.modelMapper.map(this.catCreateBindingModel, CatServiceModel.class));
        context.redirect("/");
    }
}
