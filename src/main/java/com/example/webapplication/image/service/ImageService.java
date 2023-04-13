package com.example.webapplication.image.service;

import com.example.webapplication.image.entity.Image;
import com.example.webapplication.image.repository.ImageRepository;
import com.example.webapplication.post.entity.Post;
import com.example.webapplication.user.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Date;

@Service
public class ImageService {
    private static final String UPLOAD_DIR = "C:\\Users\\BMici\\Desktop\\images\\";
    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }
    public void attachImage(Post post, MultipartFile[] images) {

        for (MultipartFile image:images){
            try {
                String fileName = image.getOriginalFilename();
                String filePath = UPLOAD_DIR + attachDateToFileName( fileName);


                // Save the file to disk
                Path path = Paths.get(filePath);
                Files.write(path, image.getBytes());

                // Save the URL to the database
                Image img = new Image();

                img.setImageUrl(filePath);

                img.setPost(post);

                img.setUser(post.getUser());

                img.setCreatedAt(new Timestamp(System.currentTimeMillis()));

                imageRepository.save(img);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String attachDateToFileName(String fileName) {
        StringBuilder newFileName=new StringBuilder();
        Date date=new Date();
           newFileName.append(fileName.split("\\.") [0]);
           newFileName.append("_");
           newFileName.append(date.getYear()+1900).append("_")
                   .append(date.getMonth()+1).append("_")
                   .append(date.getDay()).append("_")
                   .append(date.getHours()).append("_")
                   .append(date.getMinutes()).append("_")
                   .append(date.getSeconds()).append(".").append(fileName.split("\\.")[1]);
           return newFileName.toString();
    }

    public Page<Image> getUserImages(int id, int pageNr, int pageSize, String sortBy) {
        Pageable pageable= PageRequest.of(pageNr,pageSize, Sort.by(sortBy).ascending());
        return imageRepository.findByUserId(pageable,id);
    }
}
