package com.datang.olv.propaganda.mooc;

/**
 * @author nannan
 * @createTime 2014-8-27 上午9:17:45 
 * 视频(课程)实体
 */
public class Video {
	private int id;
	private String name;//
	private String cover;
	private int coverByRes;
	private String classify;
	private int playCount;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public int getCoverByRes() {
		return coverByRes;
	}
	public void setCoverByRes(int coverByRes) {
		this.coverByRes = coverByRes;
	}
	public String getClassify() {
		return classify;
	}
	public void setClassify(String classify) {
		this.classify = classify;
	}
	public int getPlayCount() {
		return playCount;
	}
	public void setPlayCount(int playCount) {
		this.playCount = playCount;
	}
	
}
