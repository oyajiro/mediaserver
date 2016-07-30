package com.oyajiro.entity;

import java.util.List;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class File extends BaseEntity {

	@DBRef
	private User owner;

	private String name;

	private String type;

	private long size;

	private String path;

	@DBRef
	private List<Tag> tags;

	public File(String name, String type, long size) {
		this.name = name;
		this.type = type;
		this.size = size;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}


	public List<Tag> getTags() {
		return tags;
	}

	public void addTags(List<Tag> tags) {
		tags.addAll(tags);
	}

	public void removeTag(Tag tag) {
		tags.remove(tag);
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

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("File{");
		sb.append("id=").append(getId());
		sb.append(", name='").append(name).append('\'');
		sb.append(", type='").append(type).append('\'');
		sb.append(", size=").append(size);
		sb.append(", path='").append(path).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
