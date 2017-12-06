package common.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建Directory
 创建IndexReader
 根据IndexReader创建IndexSearch
 创建搜索的Query
 根据searcher搜索并且返回TopDocs
 根据TopDocs获取ScoreDoc对象
 根据searcher和ScoreDoc对象获取具体的Document对象
 根据Document对象获取需要的值
 * Created by paul on 2017/11/29.
 */
@Component
public class Search {

    public List<Document> search(String keyWord){
        DirectoryReader directoryReader = null;

        try {
            //创建directory
            Directory directory = FSDirectory.open(FileSystems.getDefault().getPath(Index.INDEX_PATH));
            //创建indexReader
            directoryReader = DirectoryReader.open(directory);
            //根据indexReader创建indexSearcher
            IndexSearcher indexSearcher = new IndexSearcher(directoryReader);
            //创建搜索的query
            Analyzer analyzer = new SmartChineseAnalyzer();
            //创建parser来确定要搜索文件的内容，第一个参数为搜索的域
            QueryParser queryParser = new QueryParser("content", analyzer);
            //创建Query表示搜索域为content包含UIMA的文档
//            Query query = queryParser.parse(keyWord);


            String[] fields = {"fileName", "content"}; // 要搜索的字段，一般搜索时都不会只搜索一个字段
            // 字段之间的与或非关系，MUST表示and，MUST_NOT表示not，SHOULD表示or，有几个fields就必须有几个clauses
            BooleanClause.Occur[] clauses = {BooleanClause.Occur.SHOULD, BooleanClause.Occur.SHOULD};
            // MultiFieldQueryParser表示多个域解析， 同时可以解析含空格的字符串，如果我们搜索"上海 中国"
            Query multiFieldQuery = MultiFieldQueryParser.parse(keyWord, fields, clauses, analyzer);
            Query termQuery = new TermQuery(new Term("content", keyWord));// 词语搜索,完全匹配,搜索具体的域
            Query wildqQuery = new WildcardQuery(new Term("content", keyWord));// 通配符查询
            Query prefixQuery = new PrefixQuery(new Term("content", keyWord));// 字段前缀搜索
            Query fuzzyQuery = new FuzzyQuery(new Term("content", keyWord));// 相似度查询,模糊查询比如OpenOffica，OpenOffice
            BooleanQuery.Builder queryBuilder = new BooleanQuery.Builder();
            queryBuilder.add(multiFieldQuery, BooleanClause.Occur.SHOULD);
            queryBuilder.add(termQuery, BooleanClause.Occur.SHOULD);
            queryBuilder.add(wildqQuery, BooleanClause.Occur.SHOULD);
            queryBuilder.add(prefixQuery, BooleanClause.Occur.SHOULD);
            queryBuilder.add(fuzzyQuery, BooleanClause.Occur.SHOULD);
            BooleanQuery query = queryBuilder.build(); // 这才是最终的query
            TopDocs topDocs = indexSearcher.search(query, 100); // 搜索前100条结果



            //根据seracher搜索并返回topDocs
//            TopDocs topDocs = indexSearcher.search(query, 10);
            System.out.println("查找到的文档总共有："+topDocs.totalHits);

            //根据topDocs获取SocreDocs对象
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            List<Document> documents = new ArrayList<>();
            for (ScoreDoc scoreDoc : scoreDocs) {
                //根据searcher和ScoreDoc对象获取具体的Document对象
                Document document = indexSearcher.doc(scoreDoc.doc);
                documents.add(document);
                //根据Document对象获取需要的值
                System.out.println(document.get("filename") + " " + document.get("filepath"));
            }

            return documents;


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }finally {
            try {
                if (directoryReader != null) {
                    directoryReader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
