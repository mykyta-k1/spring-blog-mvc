package com.blog.application.service;

import com.blog.application.contract.PostService;
import com.blog.application.contract.UserService;
import com.blog.application.dto.req.PostCreateRequest;
import com.blog.application.dto.req.PostUpdateRequest;
import com.blog.application.dto.resp.PostResponseDto;
import com.blog.application.exception.PostNotFoundException;
import com.blog.application.exception.UserAccessDenied;
import com.blog.application.mapper.PostMapper;
import com.blog.domain.Post;
import com.blog.domain.User;
import com.blog.infrastructure.dao.PostDao;
import jakarta.transaction.Transactional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostDao postDao;
    private final PostMapper postMapper;
    private final UserService userService;

    @Transactional
    @Override
    public void save(PostCreateRequest dto) {
        var a = SecurityContextHolder.getContext().getAuthentication();
        User targetUser = (User) a.getPrincipal();
        User u = userService.findById(targetUser.getId());
        postDao.save(Post.builder()
            .slug(slugGenerator(dto.getTitle(), u.getUsername()))
            .title(dto.getTitle())
            .content(dto.getContent())
            .user(u)
            .build()
        );
    }

    private String slugGenerator(String title, String username) {
        return title.concat("-author="+ username).toLowerCase()
            .trim().replace(" ", "-");
    }

    @Transactional
    @Override
    public PostResponseDto update(String slug, PostUpdateRequest dto, UUID currentUserId) {
        Post p = postDao.findBySlug(slug);
        if (p == null) {
            throw new PostNotFoundException("Post not found by slug: " + slug);
        }

        if (!p.getUser().getId().equals(currentUserId)) {
            throw new UserAccessDenied("You can only edit your own posts");
        }

        if (!p.getTitle().equals(dto.getTitle())) {
            p.setTitle(dto.getTitle());
        }
        if (!p.getContent().equals(dto.getContent())) {
            p.setContent(dto.getContent());
        }

        // Транзакція повинна зберегти сутність автоматично (Dirty Checking)
        return postMapper.postToPostResponseDto(p);
    }

    @Override
    public Post findById(UUID id) {
        return postDao.findById(id).orElseThrow(() -> new PostNotFoundException("Post not found"));
    }

    @Override
    public PostResponseDto findBySlug(String slug) {
        return postMapper.postResponseViewToPostResponseDto(postDao.findPostBySlug(slug));
    }

    @Override
    public Page<PostResponseDto> findAll(Pageable pageable) {
        return postDao.findAll(pageable).map(postMapper::postToPostResponseDto);
    }
}
