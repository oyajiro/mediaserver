package com.oyajiro.entity;

import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;


public class File extends BaseEntity {

	@DBRef
	private User owner;

	private String name;

	private String type;

	private String path;

	@DBRef
	private List<Tag> tags;

	public File(String name, String type, long size) {
		this.name = name;
		this.type = type;
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

	public void replaceTags(List<Tag> tags) {
		this.tags = tags;
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

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("File{");
		sb.append("id=").append(getId());
		sb.append(", name='").append(name).append('\'');
		sb.append(", type='").append(type).append('\'');
		sb.append(", path='").append(path).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
