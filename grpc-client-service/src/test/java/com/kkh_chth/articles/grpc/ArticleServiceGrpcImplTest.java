package com.kkh_chth.articles.grpc;
import com.kkh_chth.articles.grpc.ArticleJson;
import com.kkh_chth.articles.grpc.proto.*;
import com.kkh_chth.articles.grpc.services.ArticleServiceGrpcImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ArticleServiceGrpcImplTest {

    @Test
    @DisplayName("Get All Articles")
    void testGetAllArticle() {
        // Arrange
        ReactorArticleServiceGrpc.ReactorArticleServiceStub stubMock = Mockito.mock(ReactorArticleServiceGrpc.ReactorArticleServiceStub.class);
        ArticleServiceGrpcImpl articleService = new ArticleServiceGrpcImpl(stubMock);

        // Mock the response from the gRPC service
        GetAllArticlesResponse response = GetAllArticlesResponse.newBuilder()
                .addArticles(Article.newBuilder().setId(1L).setTitle("Article 1").setCategory("Category 1").setAuthor("Author 1").build())
                .addArticles(Article.newBuilder().setId(2L).setTitle("Article 2").setCategory("Category 2").setAuthor("Author 2").build())
                .build();

        try (MockedStatic<ReactorArticleServiceGrpc> grpcMock = Mockito.mockStatic(ReactorArticleServiceGrpc.class)) {
            grpcMock.when(() -> ReactorArticleServiceGrpc.newReactorStub(Mockito.any())).thenReturn(stubMock);
            when(stubMock.getAllArticles(Empty.newBuilder().build())).thenReturn(Mono.just(response));

            // Act
            Mono<List<ArticleJson>> result = articleService.getAllArticle();

            // Assert
            List<ArticleJson> expectedArticles = List.of(
                    ArticleJson.builder().id(1L).title("Article 1").category("Category 1").author("Author 1").build(),
                    ArticleJson.builder().id(2L).title("Article 2").category("Category 2").author("Author 2").build()
            );
            assertEquals(expectedArticles, result.block());
        }
    }

    @Test
    @DisplayName("Create Articles")
    void testCreateArticle() {
        // Arrange
        ReactorArticleServiceGrpc.ReactorArticleServiceStub stubMock = Mockito.mock(ReactorArticleServiceGrpc.ReactorArticleServiceStub.class);
        ArticleServiceGrpcImpl articleService = new ArticleServiceGrpcImpl(stubMock);

        ArticleJson newArticle = ArticleJson.builder().id(1L).title("New Article").category("New Category").author("New Author").build();
        Article grpcArticle = Article.newBuilder().setId(2L).setTitle("New Article").setCategory("New Category").setAuthor("New Author").build();
        CreateArticleResponse response = CreateArticleResponse.newBuilder().setArticle(grpcArticle).build();

        try (MockedStatic<ReactorArticleServiceGrpc> grpcMock = Mockito.mockStatic(ReactorArticleServiceGrpc.class)) {
            grpcMock.when(() -> ReactorArticleServiceGrpc.newReactorStub(Mockito.any())).thenReturn(stubMock);
            when(stubMock.createArticle(Mockito.any(Mono.class))).thenReturn(Mono.just(response));

            // Act
            Mono<ArticleJson> result = articleService.createArticle(newArticle);

            // Assert
            assertEquals(newArticle, result.block());
        }
    }
}