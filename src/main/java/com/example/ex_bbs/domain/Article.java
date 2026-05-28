package com.example.ex_bbs.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 記事者の情報を表すドメインクラスです。
 * データベースの「articles」テーブルに対応し
 */
@Entity
@Table(name = "articles")
@Getter
@Setter
@ToString
public class Article {
    /**
     * 投稿ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 名前
     */
    private String name;

    /**
     * 記事内容
     */
    private String content;

    /**
     * コメント
     */
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList;
}
