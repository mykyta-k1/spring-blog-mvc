package com.blog.application.dto.req;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostCreateRequest {

    @NotBlank(message = "Title cannot be empty")
    @Size(min = 1, max = 100, message = "The title can be in the range from 1 to 100")
    private String title;

    @NotBlank(message = "Content cannot be empty")
    @Size(min = 1, max = 5000, message = "The content can be in the range from 1 to 5000")
    private String content;
}
