package br.com.fiap.serverless.dao;

import java.net.URL;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.SdkClientException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;

public class S3Manager {

	Regions clientRegion = Regions.US_EAST_1;
    String bucketLocation = "*** Path to file to upload ***";
    String objectKey = "*** Object key ***";
    URL url;

    public String createBucket(String name) {
	    try {
	        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
	                .withRegion(clientRegion)
	                .build();
	        
	        java.util.Date expiration = new java.util.Date();
            long expTimeMillis = expiration.getTime();
            expTimeMillis += 1000 * 60 * 60;
            expiration.setTime(expTimeMillis);
	        
	        if (!s3Client.doesBucketExistV2(name)) {
	            s3Client.createBucket(new CreateBucketRequest(name));
	
	            GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(name, objectKey)
	                    .withMethod(HttpMethod.PUT)
	                    .withExpiration(expiration);
	            url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);
	        }
	    } catch (AmazonServiceException e) {
	        e.printStackTrace();
	    } catch (SdkClientException e1) {
	        e1.printStackTrace();
	    }
	    return url.toString();
    }
}
