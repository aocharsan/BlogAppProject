package com.seleniumexpress.blog.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(
		name = "tbl_comments",
        uniqueConstraints = @UniqueConstraint(name = "email", columnNames = "tbl_email")
     )

public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@Column(name = "tbl_name", nullable = false)
	private String name;

	@Column(name = "tbl_email", nullable = false)
	private String email;

	@Column(name = "tbl_body", nullable = false)
	private String body;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id", nullable = false)
	private Post post;

	@Column(name = "DataChange_CreatedTime", nullable = false)
	private Date dataChangeCreatedTime;

	@Column(name = "DataChange_LastTime")
	private Date dataChangeLastModifiedTime;

	@PrePersist
	protected void prePersist() {

		if (this.dataChangeCreatedTime == null)
			dataChangeCreatedTime = new Date();
		if (this.dataChangeLastModifiedTime == null)
			dataChangeLastModifiedTime = new Date();
	}

	@PreUpdate
	protected void preUpdate() {
		this.dataChangeLastModifiedTime = new Date();
	}

	@PreRemove
	protected void preRemove() {
		this.dataChangeLastModifiedTime = new Date();
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

}
