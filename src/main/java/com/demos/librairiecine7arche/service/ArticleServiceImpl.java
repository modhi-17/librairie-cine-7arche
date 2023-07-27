package com.demos.librairiecine7arche.service;

import com.demos.librairiecine7arche.model.Article;
import com.demos.librairiecine7arche.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public List<Article> findAllArticles() {
        return (List<Article>) articleRepository.findAll();
    }

    @Override
    public Article saveArticle(Article article) {
        articleRepository.save(article);
        return article;
    }

    @Override
    public List<Article> findByKeyword(String keyword) {
        return articleRepository.findByKeyword(keyword);
    }

    @Override
    public Optional<Article> getArticleById(Long id) {
        List<Article> articles = new ArrayList<>();
        //récupère tous les articles ajoute chaque article à la liste articles en utilisant forEach().
        articleRepository.findAll().forEach(articles::add);
        return articles.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();
    }





















}
