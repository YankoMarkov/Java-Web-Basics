package panda.services;

import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import panda.domain.entities.Role;
import panda.domain.entities.User;
import panda.domain.models.serviceModels.UserServiceModel;
import panda.repository.UserRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Inject
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean saveUser(UserServiceModel userService) {
        User user = this.modelMapper.map(userService, User.class);
        user.setPassword(DigestUtils.sha256Hex(userService.getPassword()));
        setUserRole(user);
        if (userExist(userService.getUsername())) {
            return false;
        }
        this.userRepository.save(user);
        return true;
    }

    @Override
    public List<UserServiceModel> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        if (users == null) {
            return null;
        }
        return users.stream()
                .map(user -> this.modelMapper.map(user, UserServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserServiceModel getUserByUsername(String username) {
        User user = this.userRepository.findByUsername(username);
        if (user == null) {
            return null;
        }
        return this.modelMapper.map(user, UserServiceModel.class);
    }

    @Override
    public boolean userExist(String username) {
        return getUserByUsername(username) != null;
    }

    @Override
    public boolean isValidUser(String username, String password) {
        return userExist(username) &&
                getUserByUsername(username).getPassword().equals(DigestUtils.sha256Hex(password));
    }

    private void setUserRole(User user) {
        user.setRole(this.userRepository.size() == 0 ? Role.ADMIN : Role.USER);
    }
}
