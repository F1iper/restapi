package pl.fliper.restapi.domain;

import java.time.LocalDateTime;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostDto {

  @Id
  private long id;
  private String title;
  private String content;
  private LocalDateTime created;

}
