package com.kkh_chth.articles.grpc.services;


import com.kkh_chth.articles.grpc.ArticleJson;
import com.kkh_chth.articles.grpc.proto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;


import static java.util.stream.Collectors.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ArticleServiceGrpcImpl {
    private final ReactorArticleServiceGrpc.ReactorArticleServiceStub serviceStub;
    public Mono<List<ArticleJson>>  getAllArticle(){

       return serviceStub.getAllArticles(Empty.newBuilder().build()) .map(GetAllArticlesResponse::getArticlesList)
               .map(articles -> articles.stream()
                       .map(article -> ArticleJson.builder()
                               .id(article.getId())
                               .title(article.getTitle())
                               .category(article.getCategory())
                               .author(article.getAuthor())
                               .build())
                       .collect(toList()));
    }

    public Mono<ArticleJson> createArticle(ArticleJson articleJson){
        return serviceStub.createArticle(Mono.just(CreateArticleRequest.newBuilder()
                .setArticle(Article.newBuilder().setTitle(articleJson.getTitle())
                        .setCategory(articleJson.getCategory())
                        .setAuthor(articleJson.getAuthor())
                        .setId(articleJson.getId())
                        .build()).build())).map(article-> ArticleJson.builder()
                .author(article.getArticle().getAuthor())
                .category(article.getArticle().getCategory())
                .id(article.getArticle().getId())
                .title(article.getArticle().getTitle())
               .build());
    }
}
