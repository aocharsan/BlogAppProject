package com.seleniumexpress.blog.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "tbl_posts", uniqueConstraints = @UniqueConstraint(name = "title", columnNames = "tbl_title"))
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "tbl_title", nullable = false)
	private String title;

	@Column(name = "tbl_description", nullable = false)
	private String description;

	@Column(name = "tbl_content", nullable = false)
	private String content;
	
	@OneToMany(mappedBy = "post",cascade = CascadeType.ALL,orphanRemoval = true)
	private Set<Comment> comments = new HashSet<>();

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
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}



}
