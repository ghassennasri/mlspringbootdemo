package com.marklogic.mocks.mlspringbootdemo.jaxb;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Iterator;
import java.util.List;

@XmlRootElement(name = "formats", namespace = "http://marklogic.com/MLU/top-songs")
public class Formats {

	private List<String> format;

	@Override
	public String toString() {
		return "Formats [format=" + format + "]";
	}

	public Formats(List<String> format) {
		this.format = format;
	}

	public Formats() {
	}

	public List<String> getFormat() {
		return format;
	}

	public void setFormat(List<String> format) {
		this.format = format;
	}
	
	public String toCSL() {
		StringBuilder sb = new StringBuilder();
		for (Iterator<String> i = format.iterator(); i.hasNext(); ) {
			sb.append(i.next());
			if (i.hasNext())
				sb.append(",");
		}
		return sb.toString();
		
	}

	
	
}
