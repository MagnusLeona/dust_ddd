package magnus.dust.blog.application;

import magnus.dust.application.ApplicationService;
import magnus.dust.blog.application.dto.*;
import magnus.dust.blog.core.InvalidUserOrBlogException;

public interface BlogApplicationService extends ApplicationService {

    /**
     * @result 根据ID获取获取博客
     * @author Magnus
     */
    GetBlogByIdResponse getBlogById(GetBlogByIdRequest request);

    GetBlogByAuthorIdResponse getBlogsByAuthorId(GetBlogByAuthorIdRequest request);

    GetBlogAndUserRelationResponse getBlogAndUserRelation(GetBlogAndUserRelationRequest request);

    SetBlogAndUserRelationResponse setBlogAndUserRelation(SetBlogAndUserRelationRequest request) throws InvalidUserOrBlogException;

    CreateBlogResponse createBlog(CreateBlogRequest request) throws InvalidUserOrBlogException;

    DeleteBlogResponse deleteBlog(DeleteBlogRequest request) throws InvalidUserOrBlogException;
}
