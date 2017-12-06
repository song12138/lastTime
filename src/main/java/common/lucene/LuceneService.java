package common.lucene;

import org.apache.lucene.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by paul on 2017/11/29.
 */
@Service
public class LuceneService {

    @Autowired
    private Index index;

    @Autowired
    private Search search;

    @PostConstruct
    public void creatIndex(){

        System.out.println("@postConstruct******************************************************");


        ExecutorService pool = Executors.newCachedThreadPool();

//        pool.execute(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("*****************************************************************************************************************");
//                index.creatIndex();
//                System.out.println("*****************************************************************************************************************");
//            }
//        });

        pool.execute(()->{
            System.out.println("*****************************************************************************************************************");
            index.creatIndex();
            System.out.println("*****************************************************************************************************************");
        });
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("@PreDestroy**************************************************");
    }

    public List<Document> doSearch(String keyWord){
        return search.search(keyWord);
    }
}
