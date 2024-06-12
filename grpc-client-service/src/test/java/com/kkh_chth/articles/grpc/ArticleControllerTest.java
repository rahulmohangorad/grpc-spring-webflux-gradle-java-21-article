package com.kkh_chth.articles.grpc;
import com.kkh_chth.articles.grpc.ArticleJson;
import com.kkh_chth.articles.grpc.controller.ArticleController;
import com.kkh_chth.articles.grpc.services.ArticleServiceGrpcImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ArticleControllerTest {

    @Mock
    private ArticleServiceGrpcImpl articleServiceGrpcImpl;

    @InjectMocks
    private ArticleController articleController;

    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        webTestClient = WebTestClient.bindToController(articleController).build();
    }

    @Test
    @DisplayName("Get All Articles")
    void testGetAllArticles() {
        ArticleJson articleJson = ArticleJson.builder()
                .id(1L)
                .title("Title")
                .author("Author")
                .category("Category")
                .build();

        when(articleServiceGrpcImpl.getAllArticle()).thenReturn(Mono.just(Collections.singletonList(articleJson)));

        webTestClient.get().uri("/api/article")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ArticleJson.class)
                .hasSize(1)
                .contains(articleJson);
    }

    @Test
    @DisplayName("Create Articles")
    void testCreateArticle() {
        ArticleJson articleJson = ArticleJson.builder()
                .id(1L)
                .title("Title")
                .author("Author")
                .category("Category")
                .build();

        when(articleServiceGrpcImpl.createArticle(any(ArticleJson.class))).thenReturn(Mono.just(articleJson));

        webTestClient.post().uri("/api/article")
                .bodyValue(articleJson)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ArticleJson.class)
                .isEqualTo(articleJson);
    }
}
