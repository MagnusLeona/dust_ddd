package magnus.dust.blog.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetBlogAndUserRelationResponse {
    BlogUserDto blogUserDto;
}
