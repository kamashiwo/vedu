package com.datang.olv.propaganda.evaluate;

import java.io.Serializable;

/**
 * @author nannan
 *
 */
public class Teacher implements Serializable{
	/**
	 * 授课老师
	 */
	private static final long serialVersionUID = 1L;
	private int id;//数据库中的id
	private String name;//姓名
	private String course;//所授课程名称
	private String image;//从网络读取的图像
	private int face;//从本地读取的图像,测试用
	private boolean hasRemarked;
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
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getFace() {
		return face;
	}
	public void setFace(int face) {
		this.face = face;
	}
	public boolean isHasRemarked() {
		return hasRemarked;
	}
	public void setHasRemarked(boolean hasRemarked) {
		this.hasRemarked = hasRemarked;
	}

}
