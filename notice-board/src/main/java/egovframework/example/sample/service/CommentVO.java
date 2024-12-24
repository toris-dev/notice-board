package egovframework.example.sample.service;

public class CommentVO {
	private String postId;
    private String commentId;  // 댓글 ID
    private String authorId;  // 작성자
    private String title; // 글 제목
    private String content; // 글 본문
    private String createdAt;
	private String replyComment; // 대댓글
    private Number count; // 댓글 갯수
    
    public String getCreatedAt() {
    	return createdAt;
    }
    public void setCreatedAt(String createdAt) {
    	this.createdAt = createdAt;
    }
    
	public String getAuthorId() {
		return authorId;
	}
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getReplyComment() {
		return replyComment;
	}
	public void setReplyComment(String replyComment) {
		this.replyComment = replyComment;
	}
	
	public Number getCount() {
		return count;
	}
	public void setCount(Number count) {
		this.count = count;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}


}
