package com.sks.dao;


import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import  com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

public class PostsDAO {

    MongoClient mongoClient;
    MongoDatabase database;

    public PostsDAO (String url, int port, String dataBaseName) {
        mongoClient = new MongoClient(url,port);
        database=mongoClient.getDatabase(dataBaseName);
    }

    public void listAllDatabases(){
       mongoClient.listDatabaseNames().iterator().forEachRemaining(System.out::println);
    }


    public void createCollection(String collectionName){
        try {
            database.createCollection(collectionName);
            System.out.printf("createCollection :" +"collection "+collectionName+ "created");
        } catch (RuntimeException e) {
            System.out.println("createCollection :" +"something went wrong, sorry ...");
        }

    }

    public void listAllCollections() {
        System.out.println("all collections on "+database.getName());
        database.listCollectionNames().iterator().forEachRemaining(System.out::println);
    }

    public void insertOne(String name, int age, String adresse){
        Document doc = new Document();
        doc.put("name",name);
        doc.put("age",age);
        doc.put("adresse", adresse);

        MongoCollection<Document> mongoCollection=database.getCollection("niceOne");
        mongoCollection.insertOne(doc);

        System.out.println("insertOne done for " + doc.toString());
    }



    public void listAllDocuments(){
        System.out.println("all documents");
        database.getCollection("niceOne").find().iterator().forEachRemaining(System.out::println);
    }

    public void getDocument(String searchNameValue) {
        Bson filter = Filters.eq("name", "stephane");
        MongoCursor it=database.getCollection("niceOne").find( filter).iterator();
        if (it.hasNext()) {
            System.out.println("request found for "+ searchNameValue + " ="+ it.next().toString());
        } else {
            System.out.println("request not found for "+ searchNameValue);
        }

    }

    public void updateOneDocument(String searchNameValue, String newValueForfiledAdresse) {
        Bson filter = Filters.eq("name", "stephane");
        Bson updateDocument= Updates.set("adresse", newValueForfiledAdresse);
        UpdateResult updateResult=database.getCollection("niceOne").updateMany(filter, updateDocument  );
        System.out.println("UpdateResult="+updateResult.toString());;
    }

    public void updateManyDocument(String searchNameValue, String newValueForfiledAdresse) {
        Bson filter = Filters.eq("name", "stephane");
        Bson updateDocument= Updates.set("adresse", newValueForfiledAdresse);
        UpdateResult updateResult=database.getCollection("niceOne").updateMany(filter, updateDocument  );
        System.out.println("UpdateResult="+updateResult.toString());;
    }


    public void deleteMany(String searchNameValue ) {
        Bson filter = Filters.eq("name", "stephane");
        DeleteResult deleteResult=database.getCollection("niceOne").deleteMany(filter);
        System.out.println("Deleteresult="+deleteResult);
    }
}
