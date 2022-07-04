package magnus.dust.blog.application.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Magnus
 * @description return blog by id
 */
@Data
public class GetBlogByIdResponse {

    // dto of the blog;
    BlogDto blog;

    // if this request is emit by the user logined, then should return whether is marked by user;
    Boolean marked;

    // if this request is emit by the user logined, then should return whether is fanned by user;
    Boolean fanned;
}
