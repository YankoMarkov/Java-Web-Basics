package metube.services;

import metube.domain.models.serviceModels.UserServiceModel;

import java.util.List;

public interface UserService {

    boolean saveUser(UserServiceModel userService);

    List<UserServiceModel> getAllUsers();

    UserServiceModel getUserByUsername(String username);

    boolean userExist(String username);

    boolean isValidUser(String username, String password);
}
