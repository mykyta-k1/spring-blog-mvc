package com.blog.application.dto.req;

import com.blog.application.dto.resp.UserShortDto;
import java.time.OffsetDateTime;
import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostUpdateRequest {

    @NotNull
    private UUID id;

    @NotBlank(message = "Title cannot be empty")
    @Size(min = 1, max = 100, message = "The title can be in the range from 1 to 100")
    private String title;

    @NotBlank(message = "Content cannot be empty")
    @Size(min = 1, max = 5000, message = "The content can be in the range from 1 to 5000")
    private String content;

    @NotNull
    private UserShortDto author;

    @Past
    private OffsetDateTime createdAt;

    @PastOrPresent
    private OffsetDateTime updatedAt;
}
