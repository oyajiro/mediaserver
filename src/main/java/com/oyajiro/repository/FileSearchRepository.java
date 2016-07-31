package com.oyajiro.repository;

import com.oyajiro.document.FileDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * Created by user on 30.07.16.
 */
public interface FileSearchRepository extends ElasticsearchRepository<FileDocument, String> {
    List<FileDocument> findByNameContaining(String name);
    List<FileDocument> findByOwnerLogin(String ownerLogin);
    List<FileDocument> findByType(String name);
    List<FileDocument> findByTagsContaining(String tag);
}
