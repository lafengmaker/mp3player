package com.lafengmaker.download;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.net.Uri;

import com.lafengmaker.model.ConfigInfo;
import com.lafengmaker.util.FileUtil;

public class HttpDownLoader {
	private URL url=null;//ÏÂÔØµÄurl
	private ConfigInfo configInfo;
	public HttpDownLoader(ConfigInfo configInfo) {
		super();
		this.configInfo=configInfo;
	}
	public HttpDownLoader() {
		super();
	}
	public String download(String strUrl){
		strUrl=configInfo.getServeripport()+strUrl;
		System.out.println(strUrl);
		StringBuffer sb=new StringBuffer();
		String line=null;
		BufferedReader bufferedReader=null;
		try {
			url=new URL(strUrl);
			HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
			bufferedReader=new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			while((line=bufferedReader.readLine())!=null){
				sb.append(line);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				bufferedReader.close();
			} catch (Exception e2) {
				bufferedReader=null;
				e2.printStackTrace();
			}
		}
		return sb.toString();
	}
	public int downLoadFile(String filename){
		return downLoadFile(filename, filename);
	}
	public int downLoadFile(String remotename,String localname){
		InputStream inputStream=null;
		try {
			remotename=configInfo.getServeripport()+configInfo.getServermusicdir()+File.separator+Uri.encode(remotename);
			System.out.println(remotename);
			FileUtil fileUtil=new FileUtil();
			if(fileUtil.isFileExist(configInfo.getSddowndir()+File.separator+ localname)){
				return 1;
			}
			url=new URL(remotename);
			HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
			inputStream=urlConnection.getInputStream();
			Integer length=urlConnection.getContentLength();
			if(length<=0){
				throw new RuntimeException("can't got file size");
			}
			File resultFile=fileUtil.write2SDFromInput(configInfo.getSddowndir(), localname, inputStream);
			if(resultFile==null){
				return -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}finally{
			
		}
		return 0;
	}
	public Object[] getInputStreamFromUrl(String strUrl ) throws IOException{
		InputStream in=null;
		url=new URL(strUrl);
		HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
		in=urlConnection.getInputStream();
		int length=urlConnection.getContentLength();
		return new Object[]{in,length};
	}
	public static void main(String[] args) {
		String url="http://localhost:8080/mp3/music/11.lrc";
		HttpDownLoader httpDownLoader=new HttpDownLoader();
		httpDownLoader.download(url);
	}
}
