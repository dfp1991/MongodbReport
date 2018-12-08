package com.mytest;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


import java.io.*;

public class Test {

    public static void main(String[] args) {
        MongodbInsert();
    }

    public static void MongodbInsert() {
        String Filepath = "E:/study/temp/StrMotion.json";
        String old_name = "\\$\\{name\\}";
        String new_name = "newname";

        try {

            File file = new File(Filepath);
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            //CharArrayWriter caw = new CharArrayWriter();
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = br.readLine())!=null)
            {
                line = line.replaceAll(old_name, new_name);
                stringBuilder.append(line);
            }
            String json = stringBuilder.toString();
            char s=json.trim().charAt(0);
            if(s==65279){
                if(json.length()>1){
                    json=json.substring(1);
                }
            }
            System.out.println(json);

            //连接到mongodb服务
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            //连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase("dfptest");
            System.out.println(mongoDatabase.getName() + "Connect to database successfully");
            MongoCollection collection = mongoDatabase.getCollection("coll2");
            //插入一条文档
            Document document = Document.parse(json);
            collection.insertOne(document);

            } catch(Exception e){
                System.err.println(e.getClass().getName() + ":" + e.getMessage());

            }
        }
    }