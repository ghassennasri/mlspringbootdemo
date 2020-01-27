package com.marklogic.mocks.mlspringbootdemo;

import com.marklogic.mocks.mlspringbootdemo.model.Facet;
import com.marklogic.mocks.mlspringbootdemo.model.Song;
import com.marklogic.mocks.mlspringbootdemo.service.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/songs")
public class MyController {
    private SongRepository songRepository;

    @Autowired
    public MyController(SongRepository songRepository){
        this.songRepository = songRepository;
    }

    @RequestMapping(value = "/facets/{start}", method = RequestMethod.GET)
    public List<Facet> getAll(@PathVariable Integer start){
        return Arrays.asList(songRepository.search("",start,"TRUEALL").getFacets());
    }
    @RequestMapping(value = "/genre/{genre}/{start}", method = RequestMethod.GET)
    public List<String> getByGenre(@PathVariable String genre,@PathVariable Integer start){
        return Arrays.stream(songRepository.search("genre:"+genre, start, "").getSongs()).map(Song::getTitle).collect(Collectors.toList());
    }
}
