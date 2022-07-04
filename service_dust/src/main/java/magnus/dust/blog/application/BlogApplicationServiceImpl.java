package magnus.dust.blog.application;

import magnus.dust.application.ApplicationServiceBase;
import magnus.dust.blog.application.dto.*;
import magnus.dust.blog.core.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class BlogApplicationServiceImpl extends ApplicationServiceBase implements BlogApplicationService {

    private BlogRepository blogRepository;

    @Override
    public GetBlogByIdResponse getBlogById(GetBlogByIdRequest request) {
        //  �����������blogIdΪnull

        GetBlogByIdResponse blogByIdResponse = new GetBlogByIdResponse();
        if (request.getBlogId() == null) {
            return blogByIdResponse;
        }

        // ͨ��repository��ѯ����
        Blog blog = blogRepository.getById(request.getBlogId());

        if (blog == null) {
            return blogByIdResponse;
        }

        // ��Blogת����BlogDto���ظ����ö�
        BlogDto blogDto = mapBlogToDto(blog);
        blogByIdResponse.setBlog(blogDto);
        return blogByIdResponse;
    }

    @Override
    public GetBlogByAuthorIdResponse getBlogsByAuthorId(GetBlogByAuthorIdRequest request) {
        GetBlogByAuthorIdResponse getBlogByAuthorIdResponse = new GetBlogByAuthorIdResponse();
        if (request == null || request.getAuthorId() == null) {
            // Ϊ�գ�ֱ�ӷ��ؿ�
            return getBlogByAuthorIdResponse;
        }
        // ����Repositoryȥ��ѯ����
        List<Blog> blogsByAuthorId = blogRepository.getBlogsByAuthorId(request.getAuthorId());
        if (blogsByAuthorId == null) {
            return getBlogByAuthorIdResponse;
        }

        List<BlogDto> collect = blogsByAuthorId.stream().map(this::mapBlogToDto).collect(Collectors.toList());
        getBlogByAuthorIdResponse.setBlogs(collect);
        getBlogByAuthorIdResponse.setAuthorId(request.getAuthorId());
        return getBlogByAuthorIdResponse;
    }

    @Override
    public GetBlogAndUserRelationResponse getBlogAndUserRelation(GetBlogAndUserRelationRequest request) {
        GetBlogAndUserRelationResponse getBlogAndUserRelationResponse = new GetBlogAndUserRelationResponse();
        // ͨ��repository��ѯ�ͻ��Ƿ����/�ղ�
        Long userId = request.getUserId();
        Long blogId = request.getBlogId();
        if (userId == null || blogId == null) {
            getBlogAndUserRelationResponse.setBlogUserDto(new BlogUserDto(userId, blogId, BlogUserRole.ROLE_NOTHING, null, null));
            return getBlogAndUserRelationResponse;
        }

        BlogUser blogUserRelation = blogRepository.getBlogUserRelation(userId, blogId);

        if (blogUserRelation == null) {
            getBlogAndUserRelationResponse.setBlogUserDto(new BlogUserDto(userId, blogId, BlogUserRole.ROLE_NOTHING, null, null));
            return getBlogAndUserRelationResponse;
        }

        BlogUserDto blogUserDto = new BlogUserDto();
        blogUserDto.setIsFanned(blogUserRelation.getIsFanned());
        blogUserDto.setIsMarked(blogUserRelation.getIsMarked());
        getBlogAndUserRelationResponse.setBlogUserDto(blogUserDto);
        return getBlogAndUserRelationResponse;
    }

    @Override
    public SetBlogAndUserRelationResponse setBlogAndUserRelation(SetBlogAndUserRelationRequest request) throws InvalidUserOrBlogException {
        // �����û���blog�Ĺ�ϵ�����Ϊnull�����ʾ���޸ģ������Ϊnull�����ʾ��Ҫ�޸ġ�
        BlogUserDto blogUserDto = request.getBlogUserDto();
        if (blogUserDto == null || blogUserDto.getUserId() == null || blogUserDto.getBlogId() == null) {
            throw new InvalidUserOrBlogException();
        }

        BlogUser blogUser = new BlogUser(blogUserDto.getUserId(),
                blogUserDto.getBlogId(),
                blogUserDto.getBlogUserRole(),
                blogUserDto.getIsFanned(),
                blogUserDto.getIsMarked());

        SetBlogAndUserRelationResponse setBlogAndUserRelationResponse = new SetBlogAndUserRelationResponse();
        // ����
        blogRepository.setBlogUserRelation(blogUser);

        setBlogAndUserRelationResponse.setBlogUserDto(blogUserDto);
        return setBlogAndUserRelationResponse;
    }

    @Override
    public CreateBlogResponse createBlog(CreateBlogRequest request) throws InvalidUserOrBlogException {
        // ��������
        if (request == null || request.getBlogDto() == null) {
            throw new InvalidUserOrBlogException();
        }

        BlogDto blogDto = request.getBlogDto();
        // ��blogDtoת��Ϊblogʵ�塣
        Blog blog = mapDtoToBlog(blogDto);

        CreateBlogResponse createBlogResponse = new CreateBlogResponse();
        try {
            Blog savedBlog = blogRepository.createBlog(blog);
            // ����
            assert savedBlog != null;
            createBlogResponse.setBlogDto(mapBlogToDto(savedBlog));
        } catch (Exception e) {
            throw new InvalidUserOrBlogException();
        }

        return createBlogResponse;
    }

    @Override
    public DeleteBlogResponse deleteBlog(DeleteBlogRequest request) throws InvalidUserOrBlogException {
        // ɾ����blog
        if (request == null || request.getBlogDto() == null) {
            throw new InvalidUserOrBlogException();
        }
        Blog blog = mapDtoToBlog(request.getBlogDto());
        blogRepository.deleteBlog(blog);
        return new DeleteBlogResponse(true);
    }

    private BlogDto mapBlogToDto(Blog blog) {
        BlogDto blogDto = new BlogDto();
        blogDto.setId(blog.getId());
        blogDto.setBlogFile(blog.getFile());
        blogDto.setName(blog.getName());
        blogDto.setAuthorId(blog.getAuthorId());
        blogDto.setDescription(blog.getDescription());
        blogDto.setUpdateTime(blog.getUpdateTime());
        blogDto.setTagList(blog.getTags());
        return blogDto;
    }

    private Blog mapDtoToBlog(BlogDto blogDto) {
        Blog blog = new Blog();
        blog.setName(blogDto.getName());
        blog.setDescription(blogDto.getDescription());
        blog.setTags(blogDto.getTagList());
        blog.setAuthorId(blogDto.getAuthorId());
        blog.setUpdateTime(LocalDateTime.now());
        blog.setFile(blogDto.getBlogFile());
        return blog;
    }

    public BlogApplicationServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }
}
