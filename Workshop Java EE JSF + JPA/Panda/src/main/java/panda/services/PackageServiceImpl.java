package panda.services;

import org.modelmapper.ModelMapper;
import panda.domain.entities.Package;
import panda.domain.entities.Status;
import panda.domain.models.serviceModels.PackageServiceModel;
import panda.repository.PackageRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class PackageServiceImpl implements PackageService {

    private final PackageRepository packageRepository;
    private final ModelMapper modelMapper;

    @Inject
    public PackageServiceImpl(PackageRepository packageRepository, ModelMapper modelMapper) {
        this.packageRepository = packageRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void savePackage(PackageServiceModel packageService) {
        Package aPackage = this.modelMapper.map(packageService, Package.class);
        this.packageRepository.save(aPackage);
    }

    @Override
    public void updatePackage(PackageServiceModel packageService) {
        Package aPackage = this.modelMapper.map(packageService, Package.class);
        this.packageRepository.update(aPackage);
    }

    @Override
    public List<PackageServiceModel> getAllPackagesByStatus(Status status) {
        List<Package> packages = this.packageRepository.findAllByStatus(status);
        if (packages == null) {
            return null;
        }
        return packages.stream()
                .map(aPackage -> this.modelMapper.map(aPackage, PackageServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PackageServiceModel> getAllPackagesByUserAndStatus(String username, Status status) {
        List<Package> packages = this.packageRepository.findAllByUserAndStatus(username, status);
        if (packages == null) {
            return null;
        }
        return packages.stream()
                .map(aPackage -> this.modelMapper.map(aPackage, PackageServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public PackageServiceModel getPackageById(String id) {
        Package aPackage = this.packageRepository.findById(id);
        if (aPackage == null) {
            return null;
        }
        return this.modelMapper.map(aPackage, PackageServiceModel.class);
    }
}
