package com.example.ex_bbs.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 投稿者の情報を受け取るフォーム
 */

@Getter
@Setter
public class ArticleForm {
    /**
     * 投稿者名
     */
    @NotBlank(message = "投稿者名を入力してください")
    private String articleName;

    /**
     * 投稿内容
     */
    @NotBlank(message = "投稿内容を入力してください")
    private String articleContent;
}
