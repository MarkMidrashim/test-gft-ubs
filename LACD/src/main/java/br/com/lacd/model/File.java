package br.com.lacd.model;

import java.io.Serializable;

public class File implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/* CONSTRUCTOR */

	public File() {}

	public File(String fileName, String fileDownloadUri, String fileType, Long size) {
		this.fileName = fileName;
		this.fileDownloadUri = fileDownloadUri;
		this.fileType = fileType;
		this.size = size;
	}
	
	/* PROPERTIES */
	
	private String fileName;
	private String fileDownloadUri;
	private String fileType;
	private Long size;
	
	/* GETTERS & SETTERS */

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the fileDownloadUri
	 */
	public String getFileDownloadUri() {
		return fileDownloadUri;
	}

	/**
	 * @param fileDownloadUri the fileDownloadUri to set
	 */
	public void setFileDownloadUri(String fileDownloadUri) {
		this.fileDownloadUri = fileDownloadUri;
	}

	/**
	 * @return the fileType
	 */
	public String getFileType() {
		return fileType;
	}

	/**
	 * @param fileType the fileType to set
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	/**
	 * @return the size
	 */
	public Long getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(Long size) {
		this.size = size;
	}

}
