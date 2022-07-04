package magnus.dust.blog.application.dto;

import lombok.Data;

@Data
public class GetBlogByAuthorIdRequest {
    public Long authorId;
}
