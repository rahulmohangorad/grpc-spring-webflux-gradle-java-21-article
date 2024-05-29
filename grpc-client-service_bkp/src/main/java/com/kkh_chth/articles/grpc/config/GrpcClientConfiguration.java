package com.kkh_chth.articles.grpc.config;


import com.kkh_chth.articles.grpc.proto.ReactorArticleServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class GrpcClientConfiguration {

    private ManagedChannel channel;


    @Bean
    public ReactorArticleServiceGrpc.ReactorArticleServiceStub getServiceStub(){
        this.channel = ManagedChannelBuilder
                .forAddress("localhost", 9090)
                .usePlaintext()
                .build();
        return ReactorArticleServiceGrpc.newReactorStub(channel);
    }

}