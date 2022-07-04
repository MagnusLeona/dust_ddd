package magnus.dust.blog.application.dto;

import lombok.Data;

import java.util.List;

@Data
public class GetBlogByAuthorIdResponse {

    Long authorId;

    List<BlogDto> blogs;
}
