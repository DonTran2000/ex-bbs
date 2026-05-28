package com.example.ex_bbs.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * コメントの情報を表すドメインクラスです
 * データベースの「comments」テーブルに対応し
 */
@Entity
@Table(name = "comments")
@Getter
@Setter
@ToString
public class Comment {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 名前
     */
    private String name;

    /**
     * コメント内容
     */
    private String content;

    /**
     * 投稿ID
     */
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;
}
