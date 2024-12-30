package egovframework.example.post.dto;

import java.util.Date;
import java.util.List;

import egovframework.example.comment.dto.CommentVO;

public class PostVO extends SearchVO {

	private static final long serialVersionUID = 1L;

	private String postId; // Sample ID
	private String authorId; // 이름
	private String title; // 제목
	private String content; // 본문
	private Date createdAt; // 생성 시간 (Date 타입으로 변경)
	private Date updatedAt; // 업데이트 시간 (Date 타입으로 변경)

	private List<CommentVO> comments; // 댓글 리스트

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<CommentVO> getComments() {
		return comments;
	}

	public void setComments(List<CommentVO> comments) {
		this.comments = comments;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}