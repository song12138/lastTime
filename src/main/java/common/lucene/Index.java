package common.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;

/** 索引
 * Created by paul on 2017/11/29.
 */
@Component
public class Index {
    public static String INDEX_PATH = "D:\\lucene\\index";

    public static String SCAN_PATH = "D:\\lucene\\scan";



    public void creatIndex(){
        IndexWriter indexWriter = null;
        try {
            //创建directory
            Directory directory = FSDirectory.open(FileSystems.getDefault().getPath(INDEX_PATH));
            //创建indexWriter
            Analyzer analyzer = new SmartChineseAnalyzer();
            IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);

            indexWriter = new IndexWriter(directory,indexWriterConfig);
            indexWriter.deleteAll(); //清除以前的index

            //要搜索的file路径
            File dFile = new File(SCAN_PATH);
            File[] files = dFile.listFiles();
            for (File file : files) {
                //创建document对象
                Document document = new Document();
                //为Document添加Field
                document.add(new Field("content",new FileReader(file), TextField.TYPE_NOT_STORED));
                document.add(new Field("filename", file.getName(), TextField.TYPE_STORED));
                document.add(new Field("filepath", file.getAbsolutePath(), TextField.TYPE_STORED));

                //通过IndexWriter添加文档到索引中
                indexWriter.addDocument(document);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (indexWriter != null) {
                    //IndexerWriter写索引操作关闭，提交写索引（如没关闭会造成索引无法完整创建，查询时出错）
                    indexWriter.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
