/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.example.sample.service;

import java.util.List;

public class PostVO extends PostListVO {

    private static final long serialVersionUID = 1L;

    private String postId;	// Sample ID
    private String authorId;	// 이름
    private String title; // 제목
    private String content; // 본문
    private String createdAt; // 생성 시간
    private String updatedAt; // 업데이트 시간
    
    private List<CommentVO> comments; // 댓글 리스트

	public String getBoardId() {
		return postId;
	}

	public void setBoardId(String postId) {
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

	public String getcontent() {
		return content;
	}

	public void setContent(String body) {
		this.content = body;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdated_at() {
		return updatedAt;
	}

	public void setUpdated_at(String updatedAt) {
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