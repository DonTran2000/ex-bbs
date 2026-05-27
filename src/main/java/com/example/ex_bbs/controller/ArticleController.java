package com.example.ex_bbs.controller;

import com.example.ex_bbs.domain.Article;
import com.example.ex_bbs.domain.Comment;
import com.example.ex_bbs.form.ArticleForm;
import com.example.ex_bbs.form.CommentForm;
import com.example.ex_bbs.repository.ArticleRepository;
import com.example.ex_bbs.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * 投稿コントローラー
 */
@Controller
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommentRepository commentRepository;

    /**
     * 初期画面
     *
     * @param articleForm
     * @param commentForm
     * @param model
     * @return
     */
    @GetMapping("")
    public String index(ArticleForm articleForm, CommentForm commentForm, Model model) {
        List<Article> articleListScreen = articleRepository.findAll();
        model.addAttribute("articleListScreen", articleListScreen);
        return "index";
    }

    /**
     * 投稿者情報を代入
     *
     * @param articleForm
     * @param result
     * @param commentForm
     * @param model
     * @return
     */
    @PostMapping("/insertArticle")
    public String insertArticle(@Validated ArticleForm articleForm, BindingResult result, CommentForm commentForm, Model model) {
        if (result.hasErrors()) {
            List<Article> articleListScreen = articleRepository.findAll();
            model.addAttribute("articleListScreen", articleListScreen);

            return "index";
        }

        Article article = new Article();
        article.setName(articleForm.getArticleName());
        article.setContent(articleForm.getArticleContent());
        articleRepository.insert(article);

        return "redirect:/";
    }

    /**
     * コメント情報を代入
     *
     * @param commentForm
     * @param result
     * @param articleForm
     * @param articleId
     * @param model
     * @return
     */
    @PostMapping("/insertComment")
    public String insertComment(@Validated CommentForm commentForm, BindingResult result,
                                ArticleForm articleForm, String articleId, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errorArticleId", Integer.parseInt(commentForm.getArticleId()));
            return index(articleForm, commentForm, model);
        }

        Comment comment = new Comment();
        comment.setName(commentForm.getCommentName());
        comment.setContent(commentForm.getCommentContent());
        comment.setArticleId(Integer.parseInt(articleId));

        commentRepository.insert(comment);
        return "redirect:/";
    }

    /**
     * 記事を削除
     *
     * @param articleId
     * @return
     */
    @PostMapping("/deleteArticle")
    public String deleteArticle(String articleId) {
        articleRepository.deleteById(Integer.parseInt(articleId));
        return "redirect:/";
    }

}
