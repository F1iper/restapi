package pl.fliper.restapi.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.fliper.restapi.domain.Post;
import pl.fliper.restapi.repository.PostRepository;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;

  public List<Post> getPosts() {
    return postRepository.findAll();
  }

  public Optional<Post> getSinglePost(Long id) {
    return postRepository.findById(id);
  }
}
