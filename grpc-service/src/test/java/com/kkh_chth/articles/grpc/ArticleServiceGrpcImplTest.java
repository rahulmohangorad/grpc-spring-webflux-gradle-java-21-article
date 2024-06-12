package com.kkh_chth.articles.grpc;

import com.kkh_chth.articles.grpc.proto.*;
import com.kkh_chth.articles.grpc.services.ArticleServiceGrpcImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArticleServiceGrpcImplTest {
    @InjectMocks
    private ArticleServiceGrpcImpl articleServiceGrpcImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Create Articles")
    void testCreateArticle() {
        CreateArticleRequest request = CreateArticleRequest.newBuilder()
                .setArticle(Article.newBuilder()
                        .setAuthor("Author")
                        .setCategory("Category")
                        .setTitle("Title")
                        .setId(1L)
                        .build())
                .build();

        Mono<CreateArticleRequest> requestMono = Mono.just(request);
        Mono<CreateArticleResponse> responseMono = articleServiceGrpcImpl.createArticle(requestMono);

        StepVerifier.create(responseMono)
                .assertNext(response -> {
                    Article article = response.getArticle();
                    assertEquals("Author", article.getAuthor());
                    assertEquals("Category", article.getCategory());
                    assertEquals("Title", article.getTitle());
                    assertEquals(1L, article.getId());
                })
                .verifyComplete();
    }

    @Test
    @DisplayName("Get Article By ID")
    void testGetArticleById() {
        GetArticleByIdRequest request = GetArticleByIdRequest.newBuilder()
                .setId(3L)
                .build();

        Mono<GetArticleByIdRequest> requestMono = Mono.just(request);
        Mono<GetArticleByIdResponse> responseMono = articleServiceGrpcImpl.getArticleById(requestMono);

        StepVerifier.create(responseMono)
                .assertNext(response -> {
                    Article article = response.getArticle();
                    assertEquals(3L, article.getId());
                    assertEquals("Streamlining Development: Constructing a gRPC Gradle Spring WebFlux Project", article.getTitle());
                    assertEquals("KKH CHTH", article.getAuthor());
                    assertEquals("GRPC-GRADLE-REACTIVE", article.getCategory());
                })
                .verifyComplete();
    }

    @Test
    void testGetAllArticles() {
        Mono<Empty> requestMono = Mono.just(Empty.getDefaultInstance());
        Mono<GetAllArticlesResponse> responseMono = articleServiceGrpcImpl.getAllArticles(requestMono);

        StepVerifier.create(responseMono)
                .assertNext(response -> {
                    assertEquals(4, response.getArticlesCount());
                    assertEquals(1L, response.getArticles(0).getId());
                    assertEquals("Seamless Solutions: Crafting a gRPC Gradle Spring Boot Project", response.getArticles(0).getTitle());
                    assertEquals("KKH CHTH", response.getArticles(0).getAuthor());
                    assertEquals("GRPC-GRADLE", response.getArticles(0).getCategory());
                })
                .verifyComplete();
    }

    @Test
    void testUpdateArticle() {
        UpdateArticleRequest request = UpdateArticleRequest.newBuilder()
                .setArticle(Article.newBuilder()
                        .setId(3L)
                        .setTitle("Streamlining Development: Constructing a gRPC Gradle Spring WebFlux Project")
                        .setAuthor("KKH CHTH")
                        .setCategory("GRPC-GRADLE-REACTIVE")
                        .build())
                .build();

        Mono<UpdateArticleRequest> requestMono = Mono.just(request);
        Mono<UpdateArticleResponse> responseMono = articleServiceGrpcImpl.updateArticle(requestMono);

        StepVerifier.create(responseMono)
                .assertNext(response -> {
                    Article article = response.getArticle();
                    assertEquals(3L, article.getId());
                    assertEquals("Streamlining Development: Constructing a gRPC Gradle Spring WebFlux Project", article.getTitle());
                    assertEquals("KKH CHTH", article.getAuthor());
                    assertEquals("GRPC-GRADLE-REACTIVE", article.getCategory());
                })
                .verifyComplete();
    }

    @Test
    void testDeleteArticle() {
        DeleteArticleRequest request = DeleteArticleRequest.newBuilder()
                .setId(3L)
                .build();

        Mono<DeleteArticleRequest> requestMono = Mono.just(request);
        Mono<DeleteArticleResponse> responseMono = articleServiceGrpcImpl.deleteArticle(requestMono);

        StepVerifier.create(responseMono)
                .assertNext(response -> assertEquals(DeleteArticleResponse.getDefaultInstance(), response))
                .verifyComplete();
    }
}


