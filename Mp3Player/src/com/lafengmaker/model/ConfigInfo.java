package com.lafengmaker.model;

import java.io.Serializable;

import com.lafengmaker.mp3player.R;

import android.content.Context;

public class ConfigInfo implements Serializable {
	private static final long serialVersionUID = -4817025967113974825L;
	private String serveripport="";
	private String servermusicdir="";
	private String sddowndir="";
	public ConfigInfo(Context context) {
		super();
		this.serveripport =context.getString(R.string.server_ip_port);
		this.servermusicdir =context.getString(R.string.server_music_dir);
		this.sddowndir = context.getString(R.string.mp3_download_path);
	}
	public String getServermusicdir() {
		return servermusicdir;
	}
	public void setServermusicdir(String servermusicdir) {
		this.servermusicdir = servermusicdir;
	}
	public String getSddowndir() {
		return sddowndir;
	}
	public void setSddowndir(String sddowndir) {
		this.sddowndir = sddowndir;
	}
	public String getServeripport() {
		return serveripport;
	}
	public void setServeripport(String serveripport) {
		this.serveripport = serveripport;
	}
	
	
}
