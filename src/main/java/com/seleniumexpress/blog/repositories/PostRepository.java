package com.seleniumexpress.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seleniumexpress.blog.entities.*;

@Repository  //but not compulsory as implementing class of JPARepository interface have @Respository annotation
public interface PostRepository extends JpaRepository<Post, Long> {

	
}
