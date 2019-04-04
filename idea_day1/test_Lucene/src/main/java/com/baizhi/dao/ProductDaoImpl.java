package com.baizhi.dao;

import com.baizhi.entity.Product;
import com.baizhi.utils.DateUtil;
import com.baizhi.utils.LuceneUtil;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.DoubleField;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    @Override
    public List<Product> query(String type, String text) {
       IndexSearcher indexSearcher = LuceneUtil.getIndexSearcher();
        ArrayList<Product> products = new ArrayList<>();
        Query query = new TermQuery(new Term(type,text));
        try {
            TopDocs search = indexSearcher.search(query, 10);
              ScoreDoc[] scoreDocs= search.scoreDocs;
            for (ScoreDoc scoreDoc : scoreDocs) {
                Product product = documentToProduct(indexSearcher.doc(scoreDoc.doc));
                products.add(product);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public void insert(Product product) {
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();
        Document document = productToDocument(product);
        try {
            indexWriter.addDocument(document);
            LuceneUtil.commit(indexWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void update(Product product) {

    }
    public static Document productToDocument(Product product){
        Document document = new Document();
        IndexableField id = new StringField("id", product.getId(), Field.Store.YES);
        IndexableField name = new StringField("name",product.getName(), Field.Store.YES);
        IndexableField price = new DoubleField("price",product.getPrice(), Field.Store.YES);
        IndexableField context = new StringField("context",product.getContext(), Field.Store.YES);
        IndexableField image = new StringField("image",product.getImage(), Field.Store.YES);
        IndexableField status = new StringField("status",product.getStatus(), Field.Store.YES);
        IndexableField createDate = new StringField("createDate", DateUtil.getStringDate(product.getCreateDate()), Field.Store.YES);
        IndexableField produce = new StringField("produce",product.getProduce(), Field.Store.YES);
        document.add(id);
        document.add(name);
        document.add(price);
        document.add(context);
        document.add(image);
        document.add(status);
        document.add(createDate);
        document.add(produce);
        return document ;
    }
    public static Product documentToProduct(Document document){
        String id = document.get("id");
        String name = document.get("name");
        Double price = Double.parseDouble(document.get("price"));
        String context = document.get("context");
        String image = document.get("image");
        String status = document.get("status");
        Date createDate = DateUtil.getDate(document.get("createDate"));
        String produce = document.get("produce");
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setPrice(price);
        product.setContext(context);
        product.setImage(image);
        product.setStatus(status);
        product.setCreateDate(createDate);
        product.setProduce(produce);
        return product;
    }
}
