package magnus.dust.blog.application;

import magnus.dust.application.ApplicationService;
import magnus.dust.blog.application.dto.*;
import magnus.dust.blog.core.InvalidUserOrBlogException;

public interface BlogApplicationService extends ApplicationService {

    /**
     * @result ����ID��ȡ��ȡ����
     * @author Magnus
     */
    GetBlogByIdResponse getBlogById(GetBlogByIdRequest request);

    GetBlogByAuthorIdResponse getBlogsByAuthorId(GetBlogByAuthorIdRequest request);

    GetBlogAndUserRelationResponse getBlogAndUserRelation(GetBlogAndUserRelationRequest request);

    SetBlogAndUserRelationResponse setBlogAndUserRelation(SetBlogAndUserRelationRequest request) throws InvalidUserOrBlogException;

    CreateBlogResponse createBlog(CreateBlogRequest request) throws InvalidUserOrBlogException;

    DeleteBlogResponse deleteBlog(DeleteBlogRequest request) throws InvalidUserOrBlogException;
}
