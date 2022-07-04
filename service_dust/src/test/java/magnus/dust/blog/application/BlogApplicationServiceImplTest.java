package magnus.dust.blog.application;

import magnus.dust.blog.application.dto.*;
import magnus.dust.blog.core.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

class BlogApplicationServiceImplTest {

    // 给定一个id，需要返回blog
    BlogRepository mock = Mockito.mock(BlogRepository.class);

    BlogApplicationService blogApplicationService = new BlogApplicationServiceImpl(mock);

    Blog blog1;
    Blog blog2;
    Blog blog3;

    List<Blog> blogs;

    @BeforeEach
    void setUp() {
        blog1 = new Blog();
        blog2 = new Blog();
        blog3 = new Blog();

        blog1.setId(1L);
        blog2.setId(2L);
        blog3.setId(3L);
        blogs = new ArrayList<>();
        blogs.add(blog1);
        blogs.add(blog2);
        blogs.add(blog3);
    }

    @Test
    @DisplayName("根据博客ID查找博客内容")
    void getBlogByIdShouldReturnIfExist() {

        Mockito.when(mock.getById(Mockito.argThat(request -> true))).thenReturn(blog1);

        GetBlogByIdRequest getBlogByIdRequest = new GetBlogByIdRequest();
        getBlogByIdRequest.setBlogId(1L);
        GetBlogByIdResponse blogById = blogApplicationService.getBlogById(getBlogByIdRequest);
        BlogDto blog = blogById.getBlog();

        assert blog.getId() == 1L;
    }

    @Test
    @DisplayName("博客ID为null的情况应该返回空博客")
    void givenWrongIdShouldReturnNull() {
        GetBlogByIdRequest getBlogByIdRequest = new GetBlogByIdRequest();
        getBlogByIdRequest.setBlogId(null);
        GetBlogByIdResponse blogById = blogApplicationService.getBlogById(getBlogByIdRequest);
        BlogDto blog = blogById.getBlog();
        assert blog == null;
    }

    @Test
    @DisplayName("博客ID不存在此博客的时候返回空博客")
    void givenNonexistentIdShouldReturnNull() {
        Mockito.when(mock.getById(2L)).thenReturn(new Blog());

        GetBlogByIdRequest getBlogByIdRequest = new GetBlogByIdRequest();
        getBlogByIdRequest.setBlogId(2L);
        GetBlogByIdResponse blogById = blogApplicationService.getBlogById(getBlogByIdRequest);
        BlogDto blog = blogById.getBlog();
        assert blog != null;
    }

    @Test
    @DisplayName("根据作者id查找作者所有博客")
    void getBlogsByAuthorId() {
        Mockito.when(mock.getBlogsByAuthorId(1L)).thenReturn(blogs);

        GetBlogByAuthorIdRequest getBlogByAuthorIdRequest = new GetBlogByAuthorIdRequest();
        getBlogByAuthorIdRequest.setAuthorId(1L);
        GetBlogByAuthorIdResponse blogsByAuthorId = blogApplicationService.getBlogsByAuthorId(getBlogByAuthorIdRequest);
        assert blogsByAuthorId != null;
        List<BlogDto> blogs1 = blogsByAuthorId.getBlogs();
        assert blogs1.size() == 3;

        Mockito.when(mock.getBlogsByAuthorId(1L)).thenReturn(null);
        GetBlogByAuthorIdRequest getBlogByAuthorIdRequest1 = new GetBlogByAuthorIdRequest();
        getBlogByAuthorIdRequest1.setAuthorId(1L);
        GetBlogByAuthorIdResponse blogsByAuthorId1 = blogApplicationService.getBlogsByAuthorId(getBlogByAuthorIdRequest1);
        assert blogsByAuthorId1 != null;
        assert blogsByAuthorId1.getBlogs() == null;

        Mockito.when(mock.getBlogsByAuthorId(1L)).thenReturn(null);
        GetBlogByAuthorIdRequest getBlogByAuthorIdRequest2 = new GetBlogByAuthorIdRequest();
        getBlogByAuthorIdRequest2.setAuthorId(null);
        GetBlogByAuthorIdResponse blogsByAuthorId2 = blogApplicationService.getBlogsByAuthorId(getBlogByAuthorIdRequest2);
        assert blogsByAuthorId2 != null;
        assert blogsByAuthorId2.getBlogs() == null;
    }

    @Test
    @DisplayName("获取用户和博客的关系--是否点赞")
    void getBlogUser() {
        BlogUser blogUser = new BlogUser();
        blogUser.setIsFanned(true);
        blogUser.setIsMarked(false);
        Mockito.when(mock.getBlogUserRelation(1L, 1L)).thenReturn(blogUser);

        BlogUser blogUser1 = new BlogUser();
        blogUser1.setIsMarked(false);
        blogUser1.setIsFanned(false);
        Mockito.when(mock.getBlogUserRelation(null, null)).thenReturn(null);

        GetBlogAndUserRelationRequest getBlogAndUserRelationRequest = new GetBlogAndUserRelationRequest();
        getBlogAndUserRelationRequest.setUserId(1L);
        getBlogAndUserRelationRequest.setBlogId(1L);
        GetBlogAndUserRelationResponse blogAndUserRelation = blogApplicationService.getBlogAndUserRelation(getBlogAndUserRelationRequest);
        BlogUserDto blogUserDto = blogAndUserRelation.getBlogUserDto();
        assert blogUserDto != null;
        assert blogUserDto.getIsFanned();
        assert !blogUserDto.getIsMarked();

        getBlogAndUserRelationRequest.setUserId(null);
        getBlogAndUserRelationRequest.setBlogId(null);
        GetBlogAndUserRelationResponse blogAndUserRelation1 = blogApplicationService.getBlogAndUserRelation(getBlogAndUserRelationRequest);
        BlogUserDto blogUserDto1 = blogAndUserRelation1.getBlogUserDto();
        assert blogUserDto1 != null;
        assert !blogUserDto1.getIsFanned();
        assert !blogUserDto1.getIsMarked();
    }


    @Test
    @DisplayName("测试保存用户和博客的关系")
    void testSaveBlogUser() {
        SetBlogAndUserRelationRequest setBlogAndUserRelationRequest = new SetBlogAndUserRelationRequest();
        BlogUserDto blogUserDto = new BlogUserDto();
        blogUserDto.setBlogId(1L);
        blogUserDto.setUserId(1L);
        setBlogAndUserRelationRequest.setBlogUserDto(blogUserDto);
        Assertions.assertDoesNotThrow(() -> {
            SetBlogAndUserRelationResponse setBlogAndUserRelationResponse = blogApplicationService.setBlogAndUserRelation(setBlogAndUserRelationRequest);
            assert setBlogAndUserRelationResponse != null;
            assert setBlogAndUserRelationResponse.getBlogUserDto() != null;
        });

        blogUserDto.setBlogId(null);
        blogUserDto.setUserId(null);
        setBlogAndUserRelationRequest.setBlogUserDto(blogUserDto);
        Assertions.assertThrows(InvalidUserOrBlogException.class, () -> {
            SetBlogAndUserRelationResponse setBlogAndUserRelationResponse = blogApplicationService.setBlogAndUserRelation(setBlogAndUserRelationRequest);
        });
    }

    @Test
    @DisplayName("测试新建文章")
    void testSaveBlog() throws InvalidUserOrBlogException {
        blog1.setName("blogName");
        blog1.setDescription("BlogDescription");
        CreateBlogRequest createBlogRequest = new CreateBlogRequest();
        BlogDto blogDto = new BlogDto();
        blogDto.setName("blogName");
        blogDto.setDescription("BlogDescription");
        blogDto.setAuthorId(1L);

        createBlogRequest.setBlogDto(blogDto);

        Mockito.when(mock.createBlog(Mockito.any(Blog.class))).thenReturn(blog1);

        CreateBlogResponse blog = blogApplicationService.createBlog(createBlogRequest);
        assert blog != null;
        assert blog.getBlogDto() != null;
        assert blog.getBlogDto().getId() != null;
    }

    @Test
    @DisplayName("删除blog")
    void testDeleteBlog() throws InvalidUserOrBlogException {
        DeleteBlogRequest deleteBlogRequest = new DeleteBlogRequest();
        BlogDto blogDto = new BlogDto();
        blogDto.setId(1L);
        deleteBlogRequest.setBlogDto(blogDto);

        Blog blog = new Blog();
        blog.setId(blogDto.getId());

        Mockito.doNothing().when(mock).deleteBlog(blog);

        DeleteBlogResponse deleteBlogResponse = blogApplicationService.deleteBlog(deleteBlogRequest);
        assert deleteBlogResponse != null;
        assert deleteBlogResponse.isSuccess();
    }
}