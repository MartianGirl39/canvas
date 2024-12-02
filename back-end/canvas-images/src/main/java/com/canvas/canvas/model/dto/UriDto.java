package com.canvas.canvas.model.dto;

public class UriDto {
	
	private String uri;
	private String outcome;
	private long fileSize;
	private String fileType;
	
	public UriDto() {}
	public UriDto(String uri, String outcome, long fileSize, String fileType) {
		this.uri = uri;
		this.outcome = outcome;
		this.fileSize = fileSize;
		this.fileType = fileType;
	}
	
	public String getUri() {
		return uri;
	}
	
	public String getOutcome() {
		return outcome;
	}
	
	public long getFileSize() {
		return fileSize;
	}
	
	public String getFileType() {
		return fileType;
	}
	
	public void setUri(String uri) {
		this.uri = uri;
	}
	
	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}
	
	public void setFileSize(long size) {
		this.fileSize = size;
	}
	
	public void setFileType(String type) {
		this.fileType = type;
	}
}
