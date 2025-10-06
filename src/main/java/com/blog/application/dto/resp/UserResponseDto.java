package com.blog.application.dto.resp;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public record UserResponseDto (
    UUID id,
    String username,
    String email,
    String password,
    List<PostResponseDto> posts,
    OffsetDateTime createdAt,
    OffsetDateTime updatedAt
) { }