package com.kkh_chth.articles.grpc.services;

import com.kkh_chth.articles.grpc.proto.*;
import io.grpc.CallOptions;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import reactor.core.publisher.Mono;

@GrpcService
@Slf4j
public class ArticleServiceGrpcImpl extends ReactorArticleServiceGrpc.ArticleServiceImplBase {

    @Override
    public Mono<CreateArticleResponse> createArticle(Mono<CreateArticleRequest> request) {
        return Mono.just(CreateArticleResponse.newBuilder().setArticle(Article.newBuilder().setId(12L).setAuthor("dummy author").setCategory("grpc gradle").build()).build());
    }

    @Override
    public Mono<GetArticleByIdResponse> getArticleById(Mono<GetArticleByIdRequest> request) {
        return Mono.just(GetArticleByIdResponse.newBuilder()
                .setArticle(Article.newBuilder().setId(12L).setAuthor("dummy author").setCategory("getArticleById")
                        .build()).build());

    }

    @Override
    public Mono<GetAllArticlesResponse> getAllArticles(Mono<Empty> request) {
        return  Mono.just(GetAllArticlesResponse.newBuilder()
                .addArticles(Article.newBuilder().setId(12L).setAuthor("dummy author").setCategory("getAllArticles"))
                        .build());
    }

    @Override
    public Mono<UpdateArticleResponse> updateArticle(Mono<UpdateArticleRequest> request) {
        return Mono.just(UpdateArticleResponse.newBuilder()
                .setArticle(Article.newBuilder().setId(12L).setAuthor("dummy author").setCategory("updateArticle")
                        .build()).build());
    }

    @Override
    public Mono<DeleteArticleResponse> deleteArticle(Mono<DeleteArticleRequest> request) {
        return Mono.just(DeleteArticleResponse.newBuilder().build());
    }

}
