package com.marklogic.mocks.mlspringbootdemo;

import com.marklogic.mocks.mlspringbootdemo.model.Pagination;
import com.marklogic.mocks.mlspringbootdemo.model.SearchResults;
import com.marklogic.mocks.mlspringbootdemo.service.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Optional;

@Controller
public class ViewController {
    private String appMode;
    @Autowired
    SongRepository songRepository;

    @Autowired
    public ViewController(Environment environment) {
        appMode = environment.getProperty("app-mode");
    }

    @RequestMapping("/search")
    public String index(@RequestParam(value = "q", required = false,defaultValue = "") String query,@RequestParam(value = "birthdayDate", required = false) String birthDate,@RequestParam(value = "page",defaultValue = "1", required = false) Integer page, Model model) {

        SearchResults searchResults=new SearchResults();
        Pagination pagination =new Pagination();
        model.addAttribute("datetime", new Date());
        model.addAttribute("username", "Ghassen");
        model.addAttribute("mode", appMode);
        if(query!=null&&!"".equals(query)) {
            searchResults = songRepository.search(query, page, "");
        }else if(birthDate!=null&&!"".equals(birthDate)){
            searchResults = songRepository.getBirthdaySong(birthDate, page);
        }
        if (searchResults.getTotal()!=0)
            pagination = calculatePaginationDetails(page, searchResults.getTotal(), searchResults.getPageLength() );
        model.addAttribute("results", searchResults);
        model.addAttribute("page", pagination);

        return "index.html";
    }
    private Pagination calculatePaginationDetails(long startPos, long totalPages, long pageLength) {

        long start, length, total, last, end, next, previous, currpage, pagemin, totpages, rangestart, rangeend;

        start = startPos;
        total = totalPages;
        length = pageLength;

        last = start + length - 1;
        if (total > last )
            end = last;
        else
            end = total;

        previous = 0;
        next = 0;
        if (total > last)
            next = last + 1;
        if (start > 1 && (start - length) > 0 )
            previous = Math.max((start-length), 1);

        double t = total;
        double l = length;
        double s = start;

        totpages = (long) Math.ceil(t/l);
        currpage = (long) Math.ceil(s/l);

        if (currpage > 0 && currpage < 5)
            pagemin = 1;
        else
            pagemin = currpage - 4;

        rangestart = Math.max(pagemin, 1);
        rangeend = Math.min(totpages, rangestart + 4);

        return new Pagination(start, length, total, last, end, next, previous, currpage, totpages, rangestart, rangeend);

    }
}
