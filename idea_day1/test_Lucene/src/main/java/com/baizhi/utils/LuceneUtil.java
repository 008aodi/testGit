package com.baizhi.utils;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;

public class LuceneUtil {
    private static Directory directory;
    private static Analyzer analyzer;
    private static Version version = Version.LUCENE_44;
    private static IndexWriterConfig indexWriterConfig;
    private static String url = "D://index";
    private static IndexReader indexReader;
    static {
        try {
            directory = FSDirectory.open(new File(url));
            indexReader = DirectoryReader.open(directory);
        } catch (IOException e) {
            e.printStackTrace();
        }
        analyzer = new StandardAnalyzer(version);
        indexWriterConfig = new IndexWriterConfig(version,analyzer);
    }
    public static IndexWriter getIndexWriter(){
        IndexWriter indexWriter = null;
        try {
            indexWriter = new IndexWriter(directory,indexWriterConfig);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return indexWriter;
    }

    public static IndexSearcher getIndexSearcher(){
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        return indexSearcher;
    }

    public static void commit(IndexWriter indexWriter){
        try {
            indexWriter.commit();
        } catch (IOException e) {
            e.printStackTrace();
            try {
                indexWriter.rollback();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }finally {
            try {
                indexWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
