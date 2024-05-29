package com.kkh_chth.articles.grpc.services;


import com.kkh_chth.articles.grpc.proto.Empty;
import com.kkh_chth.articles.grpc.proto.GetAllArticlesResponse;
import com.kkh_chth.articles.grpc.proto.ReactorArticleServiceGrpc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class ArticleServiceGrpcImpl {
    private final ReactorArticleServiceGrpc.ReactorArticleServiceStub serviceStub;
    public Mono<GetAllArticlesResponse>  getAllArticle(){
       return serviceStub.getAllArticles(Empty.newBuilder().build());
    }
}
