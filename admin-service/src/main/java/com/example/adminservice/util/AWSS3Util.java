package com.example.adminservice.util;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;

@Service
public class AWSS3Util {

    private String accessKey;

    private String secretKey;

    private String bucketName = "grain.storage.project";

    private AmazonS3 s3Client;

    public AWSS3Util(@Value("${amazonS3.accessKey}") final String accessKey, @Value("${amazonS3.secretKey}") final String secretKey, AmazonS3 s3Client) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        this.s3Client = AmazonS3ClientBuilder.standard()
                .withRegion("us-west-1")
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }

    public void uploadOriginalImage(String s3FileName, InputStream input, ObjectMetadata metadata) {
        s3Client.putObject(bucketName, "original/" + s3FileName, input, metadata);
        s3Client.copyObject(bucketName, "original/" + s3FileName, bucketName, "pending/" + s3FileName);
    }
}
