package com.blog.infrastructure.view;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public interface UserResponseView {
    UUID getId();
    String getUsername();
    String getEmail();
    List<PostResponseView> getPosts();
    OffsetDateTime getCreatedAt();
    OffsetDateTime getUpdatedAt();
}
