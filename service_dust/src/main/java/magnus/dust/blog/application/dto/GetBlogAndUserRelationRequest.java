package magnus.dust.blog.application.dto;

import lombok.Data;

@Data
public class GetBlogAndUserRelationRequest {
    // ��ѯ�û��Ͳ��͵Ĺ�ϵ ��
    // ��Ҫ�û���id�Ͳ��͵�id
    private Long userId;
    private Long blogId;
}
