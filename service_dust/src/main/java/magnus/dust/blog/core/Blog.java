package magnus.dust.blog.core;

import magnus.dust.core.EntityImpl;

import java.time.LocalDateTime;
import java.util.List;

public class Blog extends EntityImpl<Long> {
    private String name;
    private String description;
    private LocalDateTime updateTime;
    private BlogFile file;
    private List<Tag> tags;
    private Long authorId;

    public String getName() {
        return name;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public BlogFile getFile() {
        return file;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public String getDescription() {
        return description;
    }

    public Long getAuthorId() {
        return authorId;
    }

    @Override
    public Long getId() {
        return super.getId();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFile(BlogFile file) {
        this.file = file;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }
}
