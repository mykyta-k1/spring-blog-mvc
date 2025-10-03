package com.blog.application.controller;

import com.blog.application.contract.PostService;
import com.blog.application.dto.req.PostCreateRequest;
import com.blog.application.dto.req.PostUpdateRequest;
import com.blog.application.dto.resp.PostResponseDto;
import com.blog.domain.User;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner.Mode;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public String getAll(
        Model model,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "createdAt=desc") String[] sort
    ) {
        var pageable = PageRequest.of(page, size, Sort.by(parseSort(sort)));
        model.addAttribute("postsPage", postService.findAll(pageable));
        return "posts";
    }

    @GetMapping("/{slug}")
    public String getPostBySlug(Model model, @PathVariable String slug) {
        PostResponseDto post = postService.findBySlug(slug);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) auth.getPrincipal();
        boolean isOwner = post.getAuthor().getId().equals(currentUser.getId());

        model.addAttribute("post", post);
        model.addAttribute("isOwner", isOwner);
        return "post-detail";
    }

    @PostMapping("/add-post")
    public String addPost(@Validated PostCreateRequest dto) {
        postService.save(dto);
        return "redirect:/posts";
    }

    @PostMapping("/{slug}/update")
    public String updatePost(Model model, @PathVariable String slug, @Validated PostUpdateRequest dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) auth.getPrincipal();

        PostResponseDto post = postService.update(slug, dto, currentUser.getId());
        boolean isOwner = post.getAuthor().getId().equals(currentUser.getId());
        model.addAttribute("post", post);
        model.addAttribute("isOwner", isOwner);
        return "redirect:/posts";
    }

    private static List<Order> parseSort(String[] sort) {
        List<Sort.Order> orders = new ArrayList<>();
        for (String sortParam : sort) {
            String[] parts = sortParam.split("=");
            Sort.Direction direction =
                parts.length > 1 && parts[1].equals("asc") ? Direction.ASC : Direction.DESC;
            orders.add(new Sort.Order(direction, parts[0]));
        }
        return orders;
    }
}
