package com.kkh_chth.articles.grpc.config;


import com.kkh_chth.articles.grpc.proto.ReactorArticleServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class GrpcClientConfiguration {

    ManagedChannel channel;
    @Value("${grpc-server.host}")
   private String hostName;

    @Value("${grpc-server.port}")
   private int port;

    @Bean
    public ReactorArticleServiceGrpc.ReactorArticleServiceStub getServiceStub(){
        this.channel = ManagedChannelBuilder
                .forAddress(hostName, port)
                .usePlaintext()
                .build();
        return ReactorArticleServiceGrpc.newReactorStub(channel);
    }

}