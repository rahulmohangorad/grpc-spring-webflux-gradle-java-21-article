package com.kkh_chth.articles.grpc.services;

import com.kkh_chth.articles.grpc.proto.*;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import reactor.core.publisher.Mono;

@GrpcService
@Slf4j
public class ArticleServiceGrpcImpl extends ReactorArticleServiceGrpc.ArticleServiceImplBase {

    @Override
    public Mono<CreateArticleResponse> createArticle(Mono<CreateArticleRequest> request) {
        // write your own logic here
        log.info("Inside createArticle service");
       return request.map(req->CreateArticleResponse.newBuilder().setArticle(Article.newBuilder()
                .setAuthor(req.getArticle().getAuthor())
                        .setCategory(req.getArticle().getCategory()).setTitle(req.getArticle().getTitle()).setId(req.getArticle().getId())
                .build()).build());
        }

    @Override
    public Mono<GetArticleByIdResponse> getArticleById(Mono<GetArticleByIdRequest> request) {
        // write your own logic here
        log.info("Inside getArticleById service");
        return Mono.just(GetArticleByIdResponse.newBuilder()
                .setArticle(Article.newBuilder().setId(3L).setTitle("Streamlining Development: Constructing a gRPC Gradle Spring WebFlux Project").setAuthor("KKH CHTH").setCategory("GRPC-GRADLE-REACTIVE").build()).build());

    }

    @Override

    public Mono<GetAllArticlesResponse> getAllArticles(Mono<Empty> request) {
        // write your own logic here
        log.info("Inside getAllArticles service");
        return  Mono.just(GetAllArticlesResponse.newBuilder()
                .addArticles(Article.newBuilder().setId(1L).setTitle("Seamless Solutions: Crafting a gRPC Gradle Spring Boot Project").setAuthor("KKH CHTH").setCategory("GRPC-GRADLE").build())
                .addArticles(Article.newBuilder().setId(2L).setTitle("Efficient Engineering: Building a gRPC Maven Spring Boot Project").setAuthor("KKH CHTH").setCategory("GRPC-MAVEN").build())
                .addArticles(Article.newBuilder().setId(3L).setTitle("Streamlining Development: Constructing a gRPC Gradle Spring WebFlux Project").setAuthor("KKH CHTH").setCategory("GRPC-GRADLE-REACTIVE").build())
                .addArticles(Article.newBuilder().setId(4L).setTitle("Optimal Integration: Developing a gRPC Maven Spring WebFlux Project").setAuthor("KKH CHTH").setCategory("GRPC-MAVEN-REACTIVE").build())
                        .build());
    }

    @Override
    public Mono<UpdateArticleResponse> updateArticle(Mono<UpdateArticleRequest> request) {
        // write your own logic here
        log.info("Inside updateArticle service");
        return Mono.just(UpdateArticleResponse.newBuilder()
                .setArticle(Article.newBuilder().setId(3L).setTitle("Streamlining Development: Constructing a gRPC Gradle Spring WebFlux Project").setAuthor("KKH CHTH").setCategory("GRPC-GRADLE-REACTIVE").build()).build());
    }

    @Override
    public Mono<DeleteArticleResponse> deleteArticle(Mono<DeleteArticleRequest> request) {
        // write your own logic here
        log.info("Inside deleteArticle service");
        return Mono.just(DeleteArticleResponse.newBuilder().build());
    }

}
