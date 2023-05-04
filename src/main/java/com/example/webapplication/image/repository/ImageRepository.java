package com.example.webapplication.image.repository;

import com.example.webapplication.image.entity.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<Image,Integer> {


    @Query(value = "select * from images where user_id=:id",nativeQuery = true)
    Page<Image> findByUserId(Pageable pageable, int id);


}
