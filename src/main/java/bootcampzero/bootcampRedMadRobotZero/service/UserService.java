package bootcampzero.bootcampRedMadRobotZero.service;

import bootcampzero.bootcampRedMadRobotZero.dto.UserDto;
import bootcampzero.bootcampRedMadRobotZero.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    boolean registerUser(UserDto userDto);
    User saveUserData(User user);
    List<User> getUsers();
    User getUser(Long id);

    UserDto getOneUser(Long id);

}
