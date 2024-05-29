package com.kkh_chth.articles.grpc.services;


import com.kkh_chth.articles.grpc.ArticleJson;
import com.kkh_chth.articles.grpc.proto.Empty;
import com.kkh_chth.articles.grpc.proto.GetAllArticlesResponse;
import com.kkh_chth.articles.grpc.proto.ReactorArticleServiceGrpc;
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
}
