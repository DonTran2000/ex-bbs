package com.example.ex_bbs.repository;

import com.example.ex_bbs.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 * * コメント情報をデータベースから取得・操作するリポジトリクラス。
 * * * commentsテーブルへのアクセスを担当します。
 */
@Repository
public class CommentRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Comment> COMMENT_ROW_MAPPER =
            (rs, rowNum) -> {
                Comment comment = new Comment();
                comment.setId(rs.getInt("id"));
                comment.setName(rs.getString("name"));
                comment.setContent(rs.getString("content"));
                // 投稿者
                comment.setArticleId(rs.getInt("article_id"));
                return comment;
            };

    /**
     * commentsテーブルに代入
     *
     * @param comment
     */
    public void insert(Comment comment) {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("name", comment.getName())
                .addValue("content", comment.getContent())
                .addValue("article_id", comment.getArticleId());
        String sql = """
                INSERT INTO comments(name, content, article_id) VALUES (:name, :content, :article_id)
                """;
        template.update(sql, param);
    }
}
