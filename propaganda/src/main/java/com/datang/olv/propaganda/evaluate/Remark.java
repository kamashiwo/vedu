package com.datang.olv.propaganda.evaluate;

import java.io.Serializable;

/**
 * @author nannan
 *
 */
public class Remark implements Serializable{
	/**
	 * 
	 */
	public  static final int RANKNONE=0;
	public  static final int RANK1=1;
	public  static final int RANK2=2;
	public  static final int RANK3=3;
	public  static final int RANK4=4;
	private static final long serialVersionUID = 1L;
	private int id;//评价项的id
	private String content;//评价内容
	private int rank=RANKNONE;//评价等级：1，2，3，4
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}

}
