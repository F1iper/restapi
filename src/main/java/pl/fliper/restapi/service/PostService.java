package pl.fliper.restapi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.fliper.restapi.domain.Comment;
import pl.fliper.restapi.domain.Post;
import pl.fliper.restapi.repository.CommentRepository;
import pl.fliper.restapi.repository.PostRepository;

@Service
@RequiredArgsConstructor
public class PostService {

  public static final int PAGE_SIZE = 4;
  private final PostRepository postRepository;
  private final CommentRepository commentRepository;

  public List<Post> getPosts(int page, Sort.Direction sort) {
    return postRepository.findAllPosts(
        PageRequest.of(page, PAGE_SIZE,
            Sort.by(sort, "id")
        )
    );
  }

  public Optional<Post> getSinglePost(Long id) {
    return postRepository.findById(id);
  }

  public List<Post> getPostsWithComments(int page, Sort.Direction sort) {
    List<Post> allPosts = postRepository.findAllPosts(
        PageRequest.of(page, PAGE_SIZE,
            Sort.by(sort, "id")));
    List<Long> idNumbers = allPosts.stream()
        .map(Post::getId)
        .collect(Collectors.toList());
    List<Comment> comments = commentRepository.findAllByPostIdIn(idNumbers);
    allPosts.forEach(post -> post.setComments(extractComments(comments, post.getId())));
    return allPosts;
  }

  private List<Comment> extractComments(List<Comment> comments, long id) {
    return comments.stream()
        .filter(comment -> comment.getPostId() == id)
        .collect(Collectors.toList());
  }

  public Post addPost(Post post) {
    return postRepository.save(post);
  }

  @Transactional
  public Post updatePost(Post post) {
    Post updatedPost = postRepository.findById(post.getId())
        .orElseThrow(NullPointerException::new);
    updatedPost.setTitle(post.getTitle());
    updatedPost.setContent(post.getContent());
    return updatedPost;
  }

  public void deletePostById(Long id) {
    postRepository.deleteById(id);
  }
}
