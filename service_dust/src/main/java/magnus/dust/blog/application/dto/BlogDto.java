package magnus.dust.blog.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import magnus.dust.blog.core.BlogFile;
import magnus.dust.blog.core.Tag;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogDto {
    Long id;
    String name;
    String description;
    Long authorId;
    BlogFile blogFile;
    List<Tag> tagList;
    LocalDateTime updateTime;
}
