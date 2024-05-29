package com.kkh_chth.articles.grpc.controller;

import com.kkh_chth.articles.grpc.proto.GetAllArticlesResponse;
import com.kkh_chth.articles.grpc.services.ArticleServiceGrpcImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleServiceGrpcImpl articleServiceGrpcImpl;

    @GetMapping
    public Mono<GetAllArticlesResponse> getAllArticles(){
        return articleServiceGrpcImpl.getAllArticle();
    }
}
