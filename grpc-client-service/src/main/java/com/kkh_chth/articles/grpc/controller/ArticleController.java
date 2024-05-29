package com.kkh_chth.articles.grpc.controller;

import com.kkh_chth.articles.grpc.ArticleJson;
import com.kkh_chth.articles.grpc.proto.Article;
import com.kkh_chth.articles.grpc.proto.GetAllArticlesResponse;
import com.kkh_chth.articles.grpc.services.ArticleServiceGrpcImpl;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.optional.qual.Present;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleServiceGrpcImpl articleServiceGrpcImpl;

    @GetMapping
    public Mono<List<ArticleJson>> getAllArticles(){
        return articleServiceGrpcImpl.getAllArticle();

    }
}
