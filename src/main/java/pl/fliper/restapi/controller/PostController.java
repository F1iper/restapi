package pl.fliper.restapi.controller;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.fliper.restapi.domain.Post;
import pl.fliper.restapi.domain.PostDto;
import pl.fliper.restapi.mapper.PostMapper;
import pl.fliper.restapi.service.PostService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

  private final PostService postService;

  @GetMapping
  public List<PostDto> getPosts(@RequestParam(required = false) Integer page, Sort.Direction sort) {
    Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
    int pageNumber = page != null && page >= 0 ? page : 0;
    return PostMapper.mapPostToPostDto(postService.getPosts(pageNumber, sortDirection));
  }

  @GetMapping("/comments")
  public List<Post> getPostsWithComment(@RequestParam(required = false) Integer page, Sort.Direction sort) {
    Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
    int pageNumber = page != null && page >= 0 ? page : 0;
    return postService.getPostsWithComments(pageNumber, sortDirection);
  }

  @GetMapping("/{id}")
  public Optional<Post> getSinglePost(@PathVariable Long id) {
    return postService.getSinglePost(id);
  }

  @PostMapping
  public Post addPost(@RequestBody Post post) {
    return postService.addPost(post);
  }

  @PutMapping
  public Post updatePost(@RequestBody Post post) {
    return postService.updatePost(post);
  }

  @DeleteMapping("/{id}")
  public void deletePost(@PathVariable Long id) {
    postService.deletePostById(id);
  }
}
