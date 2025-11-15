package EcomUserService.EcomUserService.mapper;

import EcomUserService.EcomUserService.dto.UserDto;
import EcomUserService.EcomUserService.model.User;

public class UserEntityDTOMapper {
    public static UserDto getUserDTOFromUserEntity(User user){
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setRoles(user.getRoles());
        return userDto;
    }
}
