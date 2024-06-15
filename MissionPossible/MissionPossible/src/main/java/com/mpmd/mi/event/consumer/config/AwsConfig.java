package com.mpmd.mi.event.consumer.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.deadline.model.AwsCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfig{

    @Value("${cloud.aws.access-key}")
    private String accessKey;

    @Value("${cloud.aws.secret-access-key}")
    private String secretAccessKey;

    @Value("${cloud.aws.region}")
    private String region;

    public AmazonS3 s3(){
        return AmazonS3ClientBuilder.standard().withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey,secretAccessKey)))
                .build();
    }
}

