package com.marklogic.mocks.mlspringbootdemo.service;

import com.marklogic.client.document.JSONDocumentManager;
import com.marklogic.client.io.SearchHandle;
import com.marklogic.client.query.QueryManager;
import com.marklogic.client.query.StringQueryDefinition;
import com.marklogic.mocks.mlspringbootdemo.Search;
import com.marklogic.mocks.mlspringbootdemo.jaxb.Topsong;
import com.marklogic.mocks.mlspringbootdemo.model.Query;
import com.marklogic.mocks.mlspringbootdemo.model.SearchResults;
import com.marklogic.mocks.mlspringbootdemo.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class SongRepository {

    @Autowired
    protected Search searchService;

    public SearchResults search(String arg, long start, String facetsOnly) {
        return searchService.search(arg,start,facetsOnly);
    }

    public SearchResults getBirthdaySong(String date,long start){
        return searchService.getBirthdaySong(date,start);
    }




}
