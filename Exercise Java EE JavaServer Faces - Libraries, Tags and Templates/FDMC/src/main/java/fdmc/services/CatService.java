package fdmc.services;

import fdmc.models.serviceModels.CatServiceModel;

import java.util.List;

public interface CatService {

    void saveCat(CatServiceModel catService);

    List<CatServiceModel> getAllCats();

    CatServiceModel getCatById(String id);
}
