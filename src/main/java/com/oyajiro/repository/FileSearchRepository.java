package com.oyajiro.repository;

import com.oyajiro.document.FileDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by user on 30.07.16.
 */
public interface FileSearchRepository extends ElasticsearchRepository<FileDocument, String> {
}
