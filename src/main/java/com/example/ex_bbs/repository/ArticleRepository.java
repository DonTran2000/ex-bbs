package com.example.ex_bbs.repository;

import com.example.ex_bbs.domain.Article;
import com.example.ex_bbs.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class ArticleRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final ResultSetExtractor<List<Article>> ARTICLE_RESULT_SET_EXTRACTOR = rs -> {
        Map<Integer, Article> map = new LinkedHashMap<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            Article article = map.get(id);

            if (article == null) {
                article = new Article();
                article.setId(id);
                article.setName(rs.getString("name"));
                article.setContent(rs.getString("content"));
                article.setCommentList(new ArrayList<>());
                map.put(id, article);
            }

            int comId = rs.getInt("com_id");
            if (!rs.wasNull()) {
                Comment comment = new Comment();
                comment.setId(comId);
                comment.setName(rs.getString("com_name"));
                comment.setContent(rs.getString("com_content"));
                comment.setArticleId(rs.getInt("article_id"));

                article.getCommentList().add(comment);
            }
        }

        return new ArrayList<>(map.values());
    };

    /**
     * articlesてブール + commentsテーブルを結合して、リストを取得
     *
     * @return
     */
    public List<Article> findAll() {
        String sql = """
                SELECT articles.id id
                ,articles.name name
                ,articles.content content
                ,com.id com_id
                ,com.name com_name
                ,com.content com_content
                ,com.article_id article_id
                FROM articles
                LEFT OUTER JOIN comments as com
                    ON articles.id = com.article_id
                ORDER BY id DESC, com_id DESC;
                """;

        return template.query(sql, ARTICLE_RESULT_SET_EXTRACTOR);
    }

    /**
     * articlesテーブルに投稿者情報を代入
     *
     * @param article
     */
    public void insert(Article article) {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("name", article.getName())
                .addValue("content", article.getContent());
        String sql = """
                INSERT INTO articles(name,content)
                VALUES (:name,:content)
                """;

        template.update(sql, param);
    }

    /**
     * 記事を削除すると、コメントも
     *
     * @param id 投稿者ID
     */
    public void deleteById(Integer id) {
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        String sql = """
                DELETE FROM articles WHERE id = :id
                """;
        template.update(sql, param);
    }
}
