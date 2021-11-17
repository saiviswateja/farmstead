package com.viswateja.farmstead.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.exceptions.CsvValidationException;
import com.viswateja.farmstead.entity.Product;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opencsv.*;

@Service
@Slf4j
public class StorageService {
    @Value("${application.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    public String uploadFile(MultipartFile file) {
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File fileObj = convertMultiPartFileToFile(file);
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
        fileObj.delete();
        return "File Uploaded";
    }

    public List<Product> downloadFile(String fileName) {
//        S3Object s3Object = s3Client.getObject(bucketName, fileName);
//        S3ObjectInputStream inputStream = s3Object.getObjectContent();
//        try {
//            return IOUtils.toByteArray(inputStream);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
        CSVParser parser = new CSVParserBuilder().build();
        S3Object object = s3Client.getObject(bucketName, fileName);
        var br = new InputStreamReader(object.getObjectContent());
        CSVReaderHeaderAware csvReader = (CSVReaderHeaderAware) new CSVReaderHeaderAwareBuilder(br)
                .withCSVParser(parser)
                .build();
        List<Product> products = new ArrayList<>();
        List<Map<String, String>> records = new ArrayList<>();
        try (CSVReaderHeaderAware reader = csvReader) {
            Map<String, String> values;

            while ((values = reader.readMap()) != null) {
                records.add(values);
                products.add(Product.builder()
                        .sku(values.get("sku"))
                        .name(values.get("name"))
                        .cost(Integer.parseInt(values.get("cost")))
                        .msrp(Integer.parseInt(values.get("msrp")))
                        .build());
            }
            return products;
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String deleteFile(String fileName) {
        s3Client.deleteObject(bucketName, fileName);
        return fileName + "removed ...";
    }

    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("Error in putting file");
        }
        return convertedFile;
    }
}
