package com.blog.infrastructure.dao;

import com.blog.domain.Post;
import com.blog.infrastructure.view.PostResponseView;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostDao extends JpaRepository<Post, UUID> {

    PostResponseView findPostBySlug(String slug);

    Post findBySlug(String slug);
}
