package magnus.dust.blog.application.dto;

import lombok.Data;
import magnus.dust.blog.core.BlogFileType;

@Data
public class GetBlogFileContentRequest {

    private String fileName;
    private String fileUrl;
    private BlogFileType blogFileType;
}
