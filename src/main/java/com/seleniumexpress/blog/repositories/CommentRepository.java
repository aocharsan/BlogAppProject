package com.seleniumexpress.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.seleniumexpress.blog.entities.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

	List<Comment> findByPostId(Long postid);
	
	@Query(
			value="select * from tbl_comments c where c.post_id= :postID",
			nativeQuery = true
			
			)
	List<Comment> getComments(@Param("postID") Long postid);
	

	@Query(
			value="select * from tbl_comments c where c.post_id= :postID and c.id=:commentID",
			nativeQuery = true
			
			)
	Comment getComment(@Param("postID") Long postid,@Param("commentID") Long commentid);
}
