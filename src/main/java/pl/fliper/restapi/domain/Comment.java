package pl.fliper.restapi.domain;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Comment {

  @Id
  private Long id;
  private String title;
  private LocalDateTime created;

}
