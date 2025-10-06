package com.blog.application.dto.resp;

import java.util.UUID;

public record UserShortDto (
    UUID id,
    String username
) { }
