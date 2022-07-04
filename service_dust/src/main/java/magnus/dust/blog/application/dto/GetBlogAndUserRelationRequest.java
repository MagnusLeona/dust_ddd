package magnus.dust.blog.application.dto;

import lombok.Data;

@Data
public class GetBlogAndUserRelationRequest {
    // 查询用户和博客的关系 表
    // 需要用户的id和博客的id
    private Long userId;
    private Long blogId;
}
