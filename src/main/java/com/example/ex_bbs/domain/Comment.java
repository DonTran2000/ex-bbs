package com.example.ex_bbs.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * コメントの情報を表すドメインクラスです
 * データベースの「comments」テーブルに対応し
 */
@Getter
@Setter
@ToString
public class Comment {
    /**
     * ID
     */
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
    private Integer articleId;
}
