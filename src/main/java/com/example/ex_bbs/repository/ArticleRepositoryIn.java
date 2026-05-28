package com.example.ex_bbs.repository;

import com.example.ex_bbs.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepositoryIn extends JpaRepository<Article, Integer> {
    List<Article> findAllByOrderByIdDesc();
}
