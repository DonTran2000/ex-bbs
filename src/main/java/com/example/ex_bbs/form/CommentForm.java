package com.example.ex_bbs.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * コメント情報を受け取るフォーム.
 */
@Getter
@Setter
public class CommentForm {
    /**
     * 投稿者ID
     */
    private String articleId;

    /**
     * コメント者
     */
    @NotBlank(message = "名前を入力してください")
    @Size(max = 50, message = "50字以内で入力してください")
    private String commentName;

    /**
     * コメント内容
     */
    @NotBlank(message = "コメントを入力してください")
    private String commentContent;
}
