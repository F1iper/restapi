package pl.fliper.restapi.mapper;

import java.util.List;
import java.util.stream.Collectors;
import pl.fliper.restapi.domain.Post;
import pl.fliper.restapi.domain.PostDto;

public class PostMapper {

  private PostMapper() {
  }

  public static List<PostDto> mapPostToPostDto(List<Post> posts) {
    return posts.stream()
        .map(PostMapper::mapToPostDto)
        .collect(Collectors.toList());
  }

  public static PostDto mapToPostDto(Post post) {
    return PostDto.builder()
        .id(post.getId())
        .title(post.getTitle())
        .content(post.getContent())
        .created(post.getCreated())
        .build();
  }

}
