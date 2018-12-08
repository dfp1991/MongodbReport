package com.mytest;

import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;


public class MongodbController {

    private static final String MONGO_HOST = "localhost";
    private static final Integer MONGO_PORT = 27017;
    private static final String MONGO_DB_NAME = "dfptest";

    public static void main(String args[]){
        try{
            //连接到mongodb服务
            MongoClient mongoClient = new MongoClient(MONGO_HOST , MONGO_PORT);
            //连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase(MONGO_DB_NAME);
            System.out.println(mongoDatabase.getName()+"Connect to database successfully");
            MongoIterable<String> collectionNames = mongoDatabase.listCollectionNames();
            System.out.println(mongoDatabase.getName()+"包含如下集合：");
            for(String collectionName : collectionNames){
                System.out.println(collectionName);
            }

            MongoCollection collection = mongoDatabase.getCollection("clone");
            //插入一条文档
            Document document =new Document();
            document.put("name","dfp");
            document.put("age",24);
            collection.insertOne(document);



        }catch (Exception e){
            System.err.println(e.getClass().getName()+":"+e.getMessage());
        }

    }
}
