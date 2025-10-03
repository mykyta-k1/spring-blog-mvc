package com.blog.application.dto.resp;

import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDto {
    private UUID id;
    private String title;
    private String slug;
    private String content;
    private UserShortDto author;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
