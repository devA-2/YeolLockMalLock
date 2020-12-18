package com.min.edu.vo;

public class FileVO {
	
	private int seq;
	private int b_seq;
	private String img_uuid;
	private String img_ori;
	private String img_url;
	
	public FileVO() {
		super();
	}
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getB_seq() {
		return b_seq;
	}
	public void setB_seq(int b_seq) {
		this.b_seq = b_seq;
	}
	public String getImg_uuid() {
		return img_uuid;
	}
	public void setImg_uuid(String img_uuid) {
		this.img_uuid = img_uuid;
	}
	public String getImg_ori() {
		return img_ori;
	}
	public void setImg_ori(String img_ori) {
		this.img_ori = img_ori;
	}
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	
	@Override
	public String toString() {
		return "FileVO [seq=" + seq + ", b_seq=" + b_seq + ", img_uuid=" + img_uuid + ", img_ori=" + img_ori
				+ ", img_url=" + img_url + "]";
	}
	
}
