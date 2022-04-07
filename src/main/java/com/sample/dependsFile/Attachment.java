package com.sample.dependsFile;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;


public class Attachment implements Serializable{

	
	private String assetName;
	
	private String id;
	
	private String assetType;

	private String assetUrl;

	private Date createdDate;
	
	private Date modifiedDate;
	
	private String localPath;
	
	private String description;

	private long size;
	
	
	public Attachment() {
		UUID uid = UUID.randomUUID();
		this.id=uid.toString();
		
	}
	
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	public String getId() {
		return id;
	}
	public void setId(String assetId) {
		this.id = assetId;
	}
	public String getAssetType() {
		return assetType;
	}
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
	public String getAssetUrl() {
		return assetUrl;
	}
	public void setAssetUrl(String assetUrl) {
		this.assetUrl = assetUrl;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getLocalPath() {
		return localPath;
	}
	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}
}
	