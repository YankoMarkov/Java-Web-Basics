package panda.services;

import panda.domain.entities.Status;
import panda.domain.models.serviceModels.PackageServiceModel;

import java.util.List;

public interface PackageService {

    void savePackage(PackageServiceModel packageService);

    List<PackageServiceModel> getAllPackagesByStatus(Status status);

    List<PackageServiceModel> getAllPackagesByUserAndStatus(String username, Status status);

    PackageServiceModel getPackageById(String id);

    void updatePackage(PackageServiceModel packageService);
}
