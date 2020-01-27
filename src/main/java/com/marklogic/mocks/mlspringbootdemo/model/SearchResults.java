package com.marklogic.mocks.mlspringbootdemo.model;

public class SearchResults {
	
	
	public SearchResults(Facet[] facets, Song[] songs, long total,
			long pageLength) {
		this.facets = facets;
		this.songs = songs;
		this.total = total;
		this.pageLength = pageLength;
	}
	public SearchResults(){
		facets=new Facet[]{};
		songs=new Song[]{};
		total=0;
		pageLength=0;
	}
	private Facet[] facets = null;
	private Song[] songs = null;
	private long total;
	private long pageLength;
	

	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public long getPageLength() {
		return pageLength;
	}
	public void setPageLength(long pageLength) {
		this.pageLength = pageLength;
	}

	public Facet[] getFacets() {
		return facets;
	}
	public void setFacets(Facet[] facets) {
		this.facets = facets;
	}
	public Song[] getSongs() {
		return songs;
	}
	public void setSongs(Song[] songs) {
		this.songs = songs;
	}
	
}
