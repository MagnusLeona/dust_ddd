package magnus.dust.blog.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import magnus.dust.core.ValueObjectImpl;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class BlogUser extends ValueObjectImpl {
    Long userId;
    Long blogId;
    BlogUserRole blogUserRole;
    Boolean isFanned;
    Boolean isMarked;
}
