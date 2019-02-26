package panda.repository;

import panda.domain.entities.Package;
import panda.domain.entities.Status;

import java.util.List;

public interface PackageRepository extends GenericRepository<Package, String> {

    List<Package> findAllByStatus(Status status);

    List<Package> findAllByUserAndStatus(String username, Status status);

    Package update(Package entity);
}
