package com.marklogic.mocks.mlspringbootdemo.jaxb;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Iterator;
import java.util.List;

@XmlRootElement(name = "producers", namespace = "http://marklogic.com/MLU/top-songs")
public class Producers {

	private List<String> producer;

	@Override
	public String toString() {
		return "Producers [producer=" + producer + "]";
	}

	public List<String> getProducer() {
		return producer;
	}

	public void setProducer(List<String> producer) {
		this.producer = producer;
	}

	public Producers(List<String> producer) {
		this.producer = producer;
	}

	public Producers() {
	}
	public String toCSL() {
		StringBuilder sb = new StringBuilder();
		for (Iterator<String> i = producer.iterator(); i.hasNext(); ) {
			sb.append(i.next());
			if (i.hasNext())
				sb.append(",");
		}
		return sb.toString();
		
	}

	
}
