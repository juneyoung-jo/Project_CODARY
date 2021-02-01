package com.spring.web.service;

import java.io.IOException;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class S3Service {

	private AmazonS3 s3Client;

	@Value("${cloud.aws.credentials.accessKey}")
	private String accessKey;

	@Value("${cloud.aws.credentials.secretKey}")
	private String secretKey;

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	@Value("${cloud.aws.region.static}")
	private String region;
	
	// @PostContstruct -> @PostConstruct 어노테이션을 설정해놓은 init 메소드는 WAS가 띄워질 때 실행된다.
	@PostConstruct
	public void setS3Client() {
		AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);

		s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(this.region).build();
	}

	public String upload(MultipartFile file) throws IOException {
		String fileName = file.getOriginalFilename(); //오리지널 파일이름
		String ext = fileName.substring(fileName.indexOf('.')); // 확장자
		String saveFileName = getUuid() + ext; // 저장 할 파일이름 -> 중복될 수 있기 때문에 고유값인 UUID를 통해 임시 파일명으로 업로드
		

		s3Client.putObject(new PutObjectRequest(bucket, saveFileName, file.getInputStream(), null)
				.withCannedAcl(CannedAccessControlList.PublicRead));
		
		// 저장된 이미지의 url 리턴
		return s3Client.getUrl(bucket, saveFileName).toString();
	}

	private static String getUuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

}
