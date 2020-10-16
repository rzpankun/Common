package com.ktiuu;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;

/**
 * @Create by pankun
 * @DATE 2020/9/25
 */
public class SearchDemo {
    @Test
    public void test1() throws IOException {
//        File indexFile = new File("d:\\Search\\index");

        Directory directory = FSDirectory.open(Paths.get("d:\\search\\index"));

        StandardAnalyzer standardAnalyzer = new StandardAnalyzer();
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(standardAnalyzer);
        indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);

        File dataFile = new File("d:\\search");
        File[] dataFiles = dataFile.listFiles();
        long start = new Date().getTime();
        for (int i = 0; i < dataFile.length() ; i++) {
            if(dataFiles[i].isFile() && dataFiles[i].getName().endsWith(".txt")){
                Document document = new Document();
                FileReader fileReader = new FileReader(dataFiles[i]);
                document.add(new TextField("path", dataFiles[i].getCanonicalPath(), Field.Store.YES));
                document.add(new TextField("contents", fileReader));
                indexWriter.addDocument(document);
            }
        }
        indexWriter.commit();
        indexWriter.close();
    }


    @Test
    public void test2() throws IOException, ParseException {
        FSDirectory open = FSDirectory.open(Paths.get("d:\\search\\index"));
        StandardAnalyzer standardAnalyzer = new StandardAnalyzer();

        IndexReader reader = DirectoryReader.open(open);
        IndexSearcher indexSearcher = new IndexSearcher(reader);
        QueryParser queryParser = new QueryParser("contents", standardAnalyzer);
        Query lucene = queryParser.parse("lucene");
        TopDocs search = indexSearcher.search(lucene, 10);
        System.out.println(search.totalHits);
        System.out.println(search);
        ScoreDoc[] scoreDocs = search.scoreDocs;
        for (ScoreDoc soc : scoreDocs){
            int doc = soc.doc;
            Document doc1 = indexSearcher.doc(doc);
            System.out.println(doc1.get("contents"));
        }

    }
}
