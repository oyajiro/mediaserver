package com.oyajiro.service;

import com.oyajiro.document.FileDocument;
import com.oyajiro.entity.File;
import com.oyajiro.repository.FileRepository;
import com.oyajiro.repository.FileSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

import static com.oyajiro.document.FileDocument.fromFile;
import static com.oyajiro.utils.EntityUtils.getOrThrowNotFound;
import static com.oyajiro.utils.StringUtils.isBlank;

/**
 * Created by user on 30.07.16.
 */
@Service
public class FileRepositoryService {

    private FileRepository fileRepository;
    private FileSearchRepository searchRepository;

    @Autowired
    protected void setFileRepository(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Autowired
    public void setSearchRepository(FileSearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    public File findById(String id) {
        return getOrThrowNotFound(fileRepository.findById(id), id);
    }

    public List<File> findAll() {
        return fileRepository.findAll();
    }

    public Iterable<FileDocument> search() {
        return searchRepository.findAll();
    }

    public void save(File entityFile) {
        fileRepository.save(entityFile);
        searchRepository.save(fromFile(entityFile));
    }

    public File updateFile(File from, File to) {
        updateFileDocument(from);
        if (!from.equals(to)) {
            setIfNotEmpty(from.getName(), to::setName);
            setIfNotNull(from.getTags(), to::replaceTags);
            setIfNotEmpty(from.getPath(), to::setPath);
        }
        return to;
    }

    private void updateFileDocument(File from) {
        FileDocument fileDocument = searchRepository.findOne(from.getId());
        if (fileDocument != null) {
            setIfNotEmpty(from.getName(), fileDocument::setName);
            setIfNotEmpty(from.getPath(), fileDocument::setPath);
            setIfNotNull(from.getTags(), fileDocument::setTags);
        } else {
            fileDocument = fromFile(from);
        }
        searchRepository.save(fileDocument);
    }

    private static <T> void setIfNotNull(T from, Consumer<T> to) {
        if (from != null) {
            to.accept(from);
        }
    }

    private static void setIfNotEmpty(String from, Consumer<String> to) {
        if (!isBlank(from)) {
            to.accept(from);
        }
    }
}
