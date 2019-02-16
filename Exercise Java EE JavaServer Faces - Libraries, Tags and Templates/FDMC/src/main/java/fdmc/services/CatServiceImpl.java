package fdmc.services;

import fdmc.entities.Cat;
import fdmc.models.serviceModels.CatServiceModel;
import fdmc.repository.CatRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class CatServiceImpl implements CatService {

    private CatRepository catRepository;
    private ModelMapper modelMapper;

    @Inject
    public CatServiceImpl(CatRepository catRepository, ModelMapper modelMapper) {
        this.catRepository = catRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveCat(CatServiceModel catService) {
        Cat cat = this.modelMapper.map(catService, Cat.class);
        this.catRepository.save(cat);
    }

    @Override
    public List<CatServiceModel> getAllCats() {
        List<Cat> cats = this.catRepository.findAll();
        if (cats == null) {
            return null;
        }
        return cats.stream()
                .map(cat -> this.modelMapper.map(cat, CatServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public CatServiceModel getCatById(String id) {
        Cat cat = this.catRepository.findById(id);
        if (cat == null) {
            return null;
        }
        return this.modelMapper.map(cat, CatServiceModel.class);
    }
}
