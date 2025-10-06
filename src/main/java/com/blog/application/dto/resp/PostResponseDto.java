package com.blog.application.dto.resp;

import java.time.OffsetDateTime;
import java.util.UUID;

public record PostResponseDto (
    UUID id,
    String title,
    String slug,
    String content,
    UserShortDto author,
    OffsetDateTime createdAt,
    OffsetDateTime updatedAt
) { }
