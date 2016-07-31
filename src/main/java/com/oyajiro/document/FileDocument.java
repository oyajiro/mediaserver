package com.oyajiro.document;

import com.oyajiro.entity.MediaFile;
import com.oyajiro.entity.Tag;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by user on 30.07.16.
 */
@Document(indexName = "file", type = "file")
public class FileDocument {
    private String id;

    private String ownerLogin;

    private String name;

    private String type;

    private String path;

    @Field(type= FieldType.Nested)
    private List<String> tags;

    public static FileDocument fromFile(MediaFile mediaFile) {
        FileDocument fileDocument = new FileDocument();
        fileDocument.id = mediaFile.getId();
        fileDocument.ownerLogin = mediaFile.getOwner().getLogin();
        fileDocument.name = mediaFile.getName();
        fileDocument.type = mediaFile.getType();
        fileDocument.path = mediaFile.getPath();
        fileDocument.tags = mediaFile.getTags().stream().map(tag -> tag.getName()).collect(Collectors.toList());
        return fileDocument;
    }

    public String getOwnerLogin() {
        return ownerLogin;
    }

    public void setOwnerLogin(String ownerLogin) {
        this.ownerLogin = ownerLogin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags.stream().map(tag -> tag.getName()).collect(Collectors.toList());;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FileDocument that = (FileDocument) o;

        if (!id.equals(that.id)) return false;
        if (ownerLogin != null ? !ownerLogin.equals(that.ownerLogin) : that.ownerLogin != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        return !(tags != null ? !tags.equals(that.tags) : that.tags != null);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (ownerLogin != null ? ownerLogin.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        return result;
    }
}
