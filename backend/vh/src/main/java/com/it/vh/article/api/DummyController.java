package com.it.vh.article.api;

import com.it.vh.article.domain.ArticleKind;
import com.it.vh.article.domain.entity.Article;
import com.it.vh.article.domain.entity.CloudArticle;
import com.it.vh.article.domain.repository.ArticleRepository;
import com.it.vh.article.domain.repository.CloudArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dummy")
public class DummyController {
    private final ArticleRepository articleRepository;
    private final CloudArticleRepository cloudArticleRepository;

    @GetMapping("/cloud")
    public ResponseEntity<?> second() {
        ArrayList<CloudArticle> list = new ArrayList<>();
        articleRepository.findAll().stream().forEach(article -> {
            for(int i=0;i<15;i++) {
                char c = (char)((Math.random() * 26) + 65);
                list.add(CloudArticle.builder()
                        .word(Character.toString(c))
                        .score(Math.random())
                        .article(article).build());
            }
        });
        cloudArticleRepository.saveAll(list);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/article")
    public ResponseEntity<?> first() {
        ArrayList<Article> list = new ArrayList<>();
        for (int i = 5; i >= 1; i--) {
            for(int j=1;j<=1000;j++) {
                LocalDateTime time = LocalDateTime.now().minusDays(i);
                Article article = Article.builder()
                        .kind(ArticleKind.GENERAL)
                        .title(ArticleKind.GENERAL.getKind() + i)
                        .url("https://naver.com/"+ArticleKind.GENERAL+i)
                        .company("company")
                        .content("cccccccccccccccccccoooooooooooootttttttttteeeeeeeeeeeennnnnnnnnnnnnnttttttttttttttttt")
                        .editor("editor")
                        .issueDate(time)
                        .thumbnail("https://thumbnail")
                        .summary("summary").build();
                list.add(article);
            }
            for(int j=1;j<=1000;j++) {
                LocalDateTime time = LocalDateTime.now().minusDays(i);
                Article article = Article.builder()
                        .kind(ArticleKind.FINANCE)
                        .title(ArticleKind.FINANCE.getKind() + i)
                        .url("https://naver.com/"+ArticleKind.FINANCE+i)
                        .company("company")
                        .content("cccccccccccccccccccoooooooooooootttttttttteeeeeeeeeeeennnnnnnnnnnnnnttttttttttttttttt")
                        .editor("editor")
                        .issueDate(time)
                        .thumbnail("https://thumbnail")
                        .summary("summary").build();
                list.add(article);
            }
            for(int j=1;j<=1000;j++) {
                LocalDateTime time = LocalDateTime.now().minusDays(i);
                Article article = Article.builder()
                        .kind(ArticleKind.GLOBAL)
                        .title(ArticleKind.GLOBAL.getKind() + i)
                        .url("https://naver.com/"+ArticleKind.GLOBAL+i)
                        .company("company")
                        .content("cccccccccccccccccccoooooooooooootttttttttteeeeeeeeeeeennnnnnnnnnnnnnttttttttttttttttt")
                        .editor("editor")
                        .issueDate(time)
                        .thumbnail("https://thumbnail")
                        .summary("summary").build();
                list.add(article);
            }
            for(int j=1;j<=1000;j++) {
                LocalDateTime time = LocalDateTime.now().minusDays(i);
                Article article = Article.builder()
                        .kind(ArticleKind.LIVING)
                        .title(ArticleKind.LIVING.getKind() + i)
                        .url("https://naver.com/"+ArticleKind.LIVING+i)
                        .company("company")
                        .content("cccccccccccccccccccoooooooooooootttttttttteeeeeeeeeeeennnnnnnnnnnnnnttttttttttttttttt")
                        .editor("editor")
                        .issueDate(time)
                        .thumbnail("https://thumbnail")
                        .summary("summary").build();
                list.add(article);
            }
            for(int j=1;j<=1000;j++) {
                LocalDateTime time = LocalDateTime.now().minusDays(i);
                Article article = Article.builder()
                        .kind(ArticleKind.INDUSTRY)
                        .title(ArticleKind.INDUSTRY.getKind() + i)
                        .url("https://naver.com/"+ArticleKind.INDUSTRY+i)
                        .company("company")
                        .content("cccccccccccccccccccoooooooooooootttttttttteeeeeeeeeeeennnnnnnnnnnnnnttttttttttttttttt")
                        .editor("editor")
                        .issueDate(time)
                        .thumbnail("https://thumbnail")
                        .summary("summary").build();
                list.add(article);
            }
        }
        articleRepository.saveAll(list);
        return ResponseEntity.ok().build();
    }
}
