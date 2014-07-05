package com.lafengmaker.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.XMLReader;

import android.os.Environment;
import android.os.StatFs;

public class FileUtil {
	private String SDROOT;
	
	public String getSDPATH() {
		return SDROOT;
	}

	public FileUtil() {
		this.SDROOT=Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator;
	}
	public File createFileInSdCard(String filename,String dir) throws IOException{
		File f=new File(SDROOT+dir+File.separator+filename);
		System.out.println(f.getAbsolutePath());
		f.createNewFile();
		return f;
	}
	public File createSDDir(String dirname){
		File f=new File(SDROOT+dirname+File.separator);
		System.out.println(f.getAbsolutePath());
		if(dirname.indexOf(File.separator)>-1){
			f.mkdirs();
		}else{
			f.mkdir();
		}
		return f;
	}
	public boolean isFileExist(String filenmame){
		File f=new File(SDROOT+filenmame);
		return f.exists();
	}
	public File write2SDFromInput(String path,String filename,InputStream in){
		File file=null;
		OutputStream outputStream=null;
		try {
			createSDDir(path);
			file=createFileInSdCard(filename, path);
			outputStream=new FileOutputStream(file);
			byte buffer[]=new byte[4*1024];
			int temp;
			while((temp=in.read(buffer))!=-1){
				outputStream.write(buffer,0,temp);
			}
			outputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				outputStream.close();
				outputStream=null;
			} catch (Exception e2) {
				e2.printStackTrace();
				outputStream=null;
			}
		}
		return file;
				
	}
	public static long getSDAvalibleSizeKB() {  
        File path = Environment.getExternalStorageDirectory();  
        StatFs sf = new StatFs(path.getPath());  
        long blockSize = sf.getBlockSize();  
        long avaliableSize = sf.getAvailableBlocks();  
        return (avaliableSize * blockSize) / 1024;// KB  
    }  
	public static long getSDAllSizeKB() {  
        // get path of sdcard  
        File path = Environment.getExternalStorageDirectory();  
        StatFs sf = new StatFs(path.getPath());  
        // get single block size(Byte)  
        long blockSize = sf.getBlockSize();  
        // 获取所有数据块数  
        long allBlocks = sf.getBlockCount();  
        // 返回SD卡大小  
        return (allBlocks * blockSize) / 1024; // KB  
    }  
	public static XMLReader getXMLReader() throws Exception{
		SAXParserFactory saxParserFactory=SAXParserFactory.newInstance();
		return saxParserFactory.newSAXParser().getXMLReader();
	}
	
}
