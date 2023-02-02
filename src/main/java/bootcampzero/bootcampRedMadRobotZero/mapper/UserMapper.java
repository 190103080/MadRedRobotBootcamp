package bootcampzero.bootcampRedMadRobotZero.mapper;

import bootcampzero.bootcampRedMadRobotZero.dto.UserDto;
import bootcampzero.bootcampRedMadRobotZero.models.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);
    User toEntity(UserDto userDto);

    List<UserDto> toDtoList(List<User> userList);
    List<User> toEntityList(List<UserDto> userDtoList);

}
