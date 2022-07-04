package magnus.dust.blog.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetBlogAndUserRelationResponse {
    // 关系，是否点赞，是否收藏。
    BlogUserDto blogUserDto;
}
