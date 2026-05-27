package com.example.ex_bbs.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 記事者の情報を表すドメインクラスです。
 * データベースの「articles」テーブルに対応し
 */
@Getter
@Setter
@ToString
public class Article {
    /**
     * 投稿ID
     */
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
    private List<Comment> commentList;
}
