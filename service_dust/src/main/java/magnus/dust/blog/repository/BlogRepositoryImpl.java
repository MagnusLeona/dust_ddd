package magnus.dust.blog.repository;

import magnus.dust.blog.core.Blog;
import magnus.dust.blog.core.BlogRepository;
import magnus.dust.blog.core.BlogUser;

import java.util.List;

public class BlogRepositoryImpl implements BlogRepository {
    @Override
    public Blog getById(Long id) {
        return null;
    }

    @Override
    public List<Blog> getBlogsByAuthorId(Long authorId) {
        return null;
    }

    @Override
    public BlogUser getBlogUserRelation(Long userId, Long blogId) {
        return null;
    }

    @Override
    public void setBlogUserRelation(BlogUser blogUser) {

    }

    @Override
    public Blog createBlog(Blog blog) {
        return null;
    }

    @Override
    public void deleteBlog(Blog blog) {

    }
}
