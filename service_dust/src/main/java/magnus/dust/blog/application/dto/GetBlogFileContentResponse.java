package magnus.dust.blog.application.dto;

import lombok.Data;
import magnus.dust.blog.core.BlogFile;

@Data
public class GetBlogFileContentResponse {
    // ����ʲô��
    private BlogFile blogFile;
}
