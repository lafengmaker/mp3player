package com.lafengmaker.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.lafengmaker.util.Caculate;

public class Mp3Info implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String mp3Name;
	private String mp3size;
	private String lrcNmae;
	private String lrcSize;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMp3Name() {
		return mp3Name;
	}
	public void setMp3Name(String mp3Name) {
		this.mp3Name = mp3Name;
	}
	public String getMp3size() {
		return mp3size;
	}
	public void setMp3size(String mp3size) {
		this.mp3size = mp3size;
	}
	public String getLrcNmae() {
		return lrcNmae;
	}
	public void setLrcNmae(String lrcNmae) {
		this.lrcNmae = lrcNmae;
	}
	public String getLrcSize() {
		return lrcSize;
	}
	public void setLrcSize(String lrcSize) {
		this.lrcSize = lrcSize;
	}
	public Mp3Info(String id, String mp3Name, String mp3size, String lrcNmae,
			String lrcSize) {
		super();
		this.id = id;
		this.mp3Name = mp3Name;
		this.mp3size = mp3size;
		this.lrcNmae = lrcNmae;
		this.lrcSize = lrcSize;
	}
	public Mp3Info() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Mp3Info [id=" + id + ", mp3Name=" + mp3Name + ", mp3size="
				+ mp3size + ", lrcNmae=" + lrcNmae + ", lrcSize=" + lrcSize
				+ "]";
	}
	public Map<String, String> toValueMap(){
		Map<String, String>m=new HashMap<String, String>();
		m.put("mp3Name", this.mp3Name);
		double mbsize=1024*1024;
		m.put("mp3size", Double.toString( Caculate.div(mp3size, Double.toString(mbsize))));
		return m;
		
	}
	
	
}
