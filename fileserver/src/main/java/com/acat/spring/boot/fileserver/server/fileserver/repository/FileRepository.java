package com.acat.spring.boot.fileserver.server.fileserver.repository;

import com.acat.spring.boot.fileserver.server.fileserver.domain.File;
import org.springframework.data.mongodb.repository.MongoRepository;

 


public interface FileRepository extends MongoRepository<File, String> {
}
