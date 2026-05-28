package com.example.ex_bbs.repository;

import com.example.ex_bbs.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepositoryIn extends JpaRepository<Comment, Integer> {
}
