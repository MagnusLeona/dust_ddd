package magnus.dust.blog.core;

import magnus.dust.blog.application.dto.BlogUserDto;
import magnus.dust.core.Repository;

import java.util.List;

public interface BlogRepository extends Repository<Blog, Long> {

    @Override
    Blog getById(Long id);

    List<Blog> getBlogsByAuthorId(Long authorId);

    BlogUser getBlogUserRelation(Long userId, Long blogId);

    void setBlogUserRelation(BlogUser blogUser);

    Blog createBlog(Blog blog);

    void deleteBlog(Blog blog);
}
