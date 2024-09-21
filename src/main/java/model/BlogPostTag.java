package model;

/**
 *
 * @author Minh
 */

public class BlogPostTag {
    private int blogPostId;
    private int tagId;

    public BlogPostTag() {
    }

    public BlogPostTag(int blogPostId, int tagId) {
        this.blogPostId = blogPostId;
        this.tagId = tagId;
    }

    public int getBlogPostId() {
        return blogPostId;
    }

    public void setBlogPostId(int blogPostId) {
        this.blogPostId = blogPostId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }
    
    
}
