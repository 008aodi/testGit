package com.baizhi.test;

import com.baizhi.Application;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestLucene {
       @Test
        public void insert() throws IOException {

           Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_44);
           IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_44, analyzer);
           Directory directory = FSDirectory.open(new File("D://index"));
           IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
           Document document = new Document();
           StringField stringField = new StringField("id", "1", Field.Store.YES);
           StringField Field1 = new StringField("title", "金缕衣", Field.Store.YES);
           StringField Field2= new StringField("author", "杜秋娘", Field.Store.YES);
           TextField context = new TextField("context", "劝君莫以金缕衣，劝君惜取少年时。花开堪折直须折，莫待无花空折枝。", Field.Store.YES);
           document.add(stringField);
            document.add(Field1);
            document.add(Field2);
            document.add(context);
            indexWriter.addDocument(document);
            indexWriter.commit();
            indexWriter.close();
       }
       @Test
    public void query() throws IOException {
           FSDirectory directory = FSDirectory.open(new File("D://index"));
           DirectoryReader directoryReader = DirectoryReader.open(directory);
           IndexSearcher searcher = new IndexSearcher(directoryReader);
           Query query = new TermQuery(new Term("context", "金"));
           TopDocs search = searcher.search(query, 10);
           ScoreDoc[] scoreDocs=search.scoreDocs;
           for (ScoreDoc scoreDoc : scoreDocs) {
               Document doc = searcher.doc(scoreDoc.doc);
               String title = doc.get("title");
               String author = doc.get("author");
               String context = doc.get("context");
               System.out.println(title);
               System.out.println(author);
               System.out.println(context);
           }

       }
       @Test
       public void delete() throws IOException {
           FSDirectory directory = FSDirectory.open(new File("D://index"));
           StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_44);
           IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_44, analyzer);
           IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
           indexWriter.deleteDocuments(new Term("id","1"));
         /*  indexWriter.deleteAll();*/
           indexWriter.commit();
           indexWriter.close();
       }
       @Test
    public void update() throws IOException {
           FSDirectory directory = FSDirectory.open(new File("D://index"));
           StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_44);
           IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_44, analyzer);
           IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
           Document document = new Document();
           IndexableField stringField = new StringField("id", "1", Field.Store.YES);
           IndexableField stringField1 = new StringField("title", "出师表", Field.Store.YES);
           IndexableField stringField2 = new StringField("author", "诸葛亮", Field.Store.YES);
           IndexableField context = new TextField("context", "先帝创业未半，而中道崩殂。", Field.Store.YES);
           document.add(stringField);
           document.add(stringField1);
           document.add(stringField2);
           document.add(context);
           indexWriter.updateDocument(new Term("id","1"),document);
           indexWriter.commit();
           indexWriter.close();
       }
       @Test
    public void test(){
           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
           Date date = new Date();
           String format = sdf.format(date);
           System.out.println(format);
       }
       @Test
    public void testDate(){

       }
}
