package magnus.dust.blog.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import magnus.dust.blog.core.BlogUserRole;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogUserDto {

    Long userId;
    Long blogId;
    BlogUserRole blogUserRole;
    Boolean isFanned;
    Boolean isMarked;
}
