package com.lafengmaker.xml;

import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.lafengmaker.model.Mp3Info;

public class Mp3ListContentHandler extends DefaultHandler {
	private List<Mp3Info>mp3InfoList=null;
	private Mp3Info mp3Info=null;
	private String tagName=null;
	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
	}

	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		this.tagName=localName;
		if("resource".equals(tagName)){
			mp3Info=new Mp3Info();
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		this.tagName=localName;
		if("resource".equals(tagName)){
			mp3InfoList.add(mp3Info);
		}
		this.tagName="";
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String temp=new String(ch,start,length);
		if("id".equals(tagName)){
			mp3Info.setId(temp);
		}else if("mp3.name".equals(tagName)){
			mp3Info.setMp3Name(temp);
		}else if("mp3.size".equals(tagName)){
			mp3Info.setMp3size(temp);
		}else if("lrc.name".equals(tagName)){
			mp3Info.setLrcNmae(temp);
		}else if("lrc.size".equals(tagName)){
			mp3Info.setLrcSize(temp);
		}
	}

	public Mp3ListContentHandler(List<Mp3Info> mp3InfoList) {
		super();
		this.mp3InfoList = mp3InfoList;
	}
	
}
