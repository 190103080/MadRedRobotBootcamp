package bootcampzero.bootcampRedMadRobotZero.service.impl;

import bootcampzero.bootcampRedMadRobotZero.dto.UserDto;
import bootcampzero.bootcampRedMadRobotZero.mapper.UserMapper;
import bootcampzero.bootcampRedMadRobotZero.models.User;
import bootcampzero.bootcampRedMadRobotZero.repository.UserRepository;
import bootcampzero.bootcampRedMadRobotZero.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean registerUser(UserDto userDto) {
        if(userDto.getPassword().equals(userDto.getRetypePassword())) {
            User checkUser = userRepository.findByEmail(userDto.getEmail());
            if (checkUser == null) {
                User user = new User();
                user.setEmail(userDto.getEmail());
                user.setPassword(passwordEncoder.encode(userDto.getPassword()));
                User newUser = userRepository.save(user);
                return newUser.getId() != null;
            }
        }
        return false;
    }

    @Override
    public User saveUserData(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public UserDto getOneUser(Long id) {
        return userMapper.toDto(getUser(id));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user==null) throw new UsernameNotFoundException("User not found");
        return user;
    }
}
