package magnus.dust.blog.application.dto;

import lombok.Data;

@Data
public class GetBlogByIdRequest {

    /**
     * @author Magnus
     * @description blog id is required
     */
    public Long blogId;
}
