package ir.maktabsharif.controller.mapper;

import ir.maktabsharif.base.mapper.BaseMapper;
import ir.maktabsharif.domain.User;
import ir.maktabsharif.service.dto.UserDTO;
import ir.maktabsharif.service.dto.extra.UserWithoutPasswordDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User, UserDTO, Long> {

    List<UserWithoutPasswordDTO> convertListEntityToUserWithoutPassword(List<User> users);

    List<User> convertListUserWithoutPasswordToEntity(List<UserWithoutPasswordDTO> userWithoutPasswordDTOS);

    UserWithoutPasswordDTO convertEntityToUserWithoutPassword(User user);

    User convertUserWithoutPasswordToEntity(UserWithoutPasswordDTO withoutPasswordDTO);

}
