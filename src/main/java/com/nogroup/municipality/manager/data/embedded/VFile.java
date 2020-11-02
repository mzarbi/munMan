package com.nogroup.municipality.manager.data.embedded;

import java.io.Serializable;

import com.nogroup.municipality.manager.business.FileBundle;

public class VFile implements Serializable{

	/**
	 * @author medzied
	 */
	private static final long serialVersionUID = 1L;
	
	private String name ;
	private String extension ;
	private String content ;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String decodedContent() {
		return new String(FileBundle.decode(content));
	}
}
