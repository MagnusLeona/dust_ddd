package magnus.dust.blog.core;

import lombok.Data;
import lombok.EqualsAndHashCode;
import magnus.dust.core.ValueObjectImpl;

@Data
@EqualsAndHashCode(callSuper = true)
public class BlogFile extends ValueObjectImpl {
    private String fileName;
    private String fileUrl;
    private BlogFileType fileType;
    private byte[] fileContent;
}
