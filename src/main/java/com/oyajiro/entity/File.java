package com.oyajiro.entity;

public class File extends BaseEntity {

	private String name;

	private String type;

	private long size;

	private String path;

	public File(String name, String type, long size) {
		this.name = name;
		this.type = type;
		this.size = size;
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
