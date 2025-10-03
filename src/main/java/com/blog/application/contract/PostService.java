package com.blog.application.contract;

import com.blog.application.dto.req.PostCreateRequest;
import com.blog.application.dto.req.PostUpdateRequest;
import com.blog.application.dto.resp.PostResponseDto;
import com.blog.domain.Post;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

    void save(PostCreateRequest dto);

    PostResponseDto update(String slug, PostUpdateRequest dto, UUID currentUserId);

    Post findById(UUID id);

    PostResponseDto findBySlug(String slug);

    Page<PostResponseDto> findAll(Pageable pageable);
}
