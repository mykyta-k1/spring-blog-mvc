package com.blog.application.mapper;

import com.blog.application.dto.resp.UserResponseDto;
import com.blog.domain.User;
import com.blog.infrastructure.view.UserResponseView;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = PostMapper.class)
public interface UserMapper {

    UserResponseDto userToUserResponseDto(User user);

    UserResponseDto userResponseViewToUserResponseDto(UserResponseView userResponseView);
}
