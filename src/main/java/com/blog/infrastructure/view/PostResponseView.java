package com.blog.infrastructure.view;

import java.time.OffsetDateTime;
import java.util.UUID;

public interface PostResponseView {
    UUID getId();
    String getSlug();
    String getTitle();
    String getContent();
    UserResponseView getUser();
    OffsetDateTime getCreatedAt();
    OffsetDateTime getUpdatedAt();
}
