package easyestate.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;

@Service
public class StorageService {
	
	@Value("${application.bucket.name}")
	private String bucketName;
	
	@Autowired
	private AmazonS3 client;
	
	public void uploadFile(MultipartFile file, String fileName) throws IOException {
		
		File normalFile = convertMultiPartFileToFile(file);
		
		client.putObject(new PutObjectRequest(bucketName, fileName, normalFile));
		
		normalFile.delete();
		
	}
	
	public byte[] downloadFile(String fileName) {
		
		S3Object s3Object = client.getObject(bucketName, fileName);
		S3ObjectInputStream inputStream = s3Object.getObjectContent();
		
		try {
			
			byte[] content = IOUtils.toByteArray(inputStream);
			
			return content;
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
		return null;
		
	}
	
	public void deleteFile(String fileName) {
		
		client.deleteObject(bucketName, fileName);
		
	}
	
	private File convertMultiPartFileToFile(MultipartFile file) throws IOException {
		
		File convertedFile = new File(file.getOriginalFilename());
		
		try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
			
			fos.write(file.getBytes());
			
		} catch (IOException e) {
			
			throw new IOException("Error converting multipart file to file", e);
			
		}
		
		return convertedFile;
		
	}

}
