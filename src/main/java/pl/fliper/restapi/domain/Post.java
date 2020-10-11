package pl.fliper.restapi.domain;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Post {

  @Id
  private long id;
  private String title;
  private String content;
  private LocalDateTime created;

  @OneToMany
  @JoinColumn(name = "post_id")
  private List<Comment> comments;


}
