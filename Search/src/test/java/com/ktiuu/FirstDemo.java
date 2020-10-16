package com.ktiuu;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * @Create by pankun
 * @DATE 2020/9/25
 */
public class FirstDemo {
    private String path = "d:\\search\\index";
    String str1 = "hello world";
    String str2 = "hello java world";
    String str3 = "hello lucene world";

    @Test
    public void test1() throws IOException {
        Directory directory = FSDirectory.open(Paths.get(path));
        StandardAnalyzer standardAnalyzer = new StandardAnalyzer();

        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(standardAnalyzer);
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);

        Document doc1 = new Document();
        doc1.add(new TextField("title", "doc1", Field.Store.YES));
        doc1.add(new TextField("content", str1, Field.Store.YES));
        indexWriter.addDocument(doc1);

        Document doc2 = new Document();
        doc2.add(new TextField("title", "doc2", Field.Store.YES));
        doc2.add(new TextField("content", str2, Field.Store.YES));
        indexWriter.addDocument(doc2);


        Document doc3 = new Document();
        doc3.add(new TextField("title", "doc3", Field.Store.YES));
        doc3.add(new TextField("content", str3, Field.Store.YES));
        indexWriter.addDocument(doc3);
        indexWriter.commit();
        indexWriter.close();
    }


    @Test
    public void test2() throws ParseException, IOException {
        String keyWork = "hello java";
        String f = "content";

        StandardAnalyzer standardAnalyzer = new StandardAnalyzer();
        QueryParser queryParser = new QueryParser(f, standardAnalyzer);
        Query query = queryParser.parse("content:" + keyWork);
        FSDirectory fsDirectory = FSDirectory.open(Paths.get(path));

        IndexReader indexReader = DirectoryReader.open(fsDirectory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        TopDocs topDocs = indexSearcher.search(query, 100);
        System.out.println(topDocs.totalHits);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for(ScoreDoc doc : scoreDocs){
            int i = doc.doc;
            System.out.println(doc.score);
            Document doc1 = indexSearcher.doc(i);
            System.out.println(doc1.get("title"));
            System.out.println(doc1.get("content"));
        }
    }
}
