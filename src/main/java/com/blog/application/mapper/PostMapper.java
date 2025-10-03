package com.blog.application.mapper;

import com.blog.application.dto.resp.PostResponseDto;
import com.blog.application.dto.resp.UserShortDto;
import com.blog.domain.Post;
import com.blog.domain.User;
import com.blog.infrastructure.view.PostResponseView;
import com.blog.infrastructure.view.UserResponseView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(source = "user", target = "author")
    PostResponseDto postToPostResponseDto(Post post);

    @Mapping(source = "user", target = "author")
    PostResponseDto postResponseViewToPostResponseDto(PostResponseView postResponseView);

    UserShortDto userResponseViewToUserShortDto(UserResponseView userResponseView);

    UserShortDto userToUserShortDto(User user);

}
