package pro.controller;

import common.lucene.LuceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by paul on 2017/11/29.
 */
@Controller
public class SerarchController {

    @Autowired
    private LuceneService luceneService;

    @Autowired
    private ApplicationContext applicationContext;


    @RequestMapping("/search")
    public String  search(){
        return "search";
    }

    @RequestMapping("/search/key")
    public String  doSearch(@RequestParam String searchKey, Model model) {
        model.addAttribute("doc", luceneService.doSearch(searchKey));
        return "search";
    }
}
