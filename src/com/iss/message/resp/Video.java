package com.iss.message.resp;
//视频model
public class Video {
//媒体文件id
	private String MediaId;
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public String getThumbMediaId() {
		return ThumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
	//缩略图的媒体ID
	private String ThumbMediaId;
	
}
