package com.sks.execution;

import com.sks.dao.PostsDAO;

public class Main {

    public static void main(String[] args) {
        System.out.println(">>> begin");

        PostsDAO postsDAO=new PostsDAO("localhost", 27017,"testbdd");

        postsDAO.listAllDatabases();
        postsDAO.createCollection("niceOne");
        postsDAO.listAllCollections();

        /**
         * no "_id" is set. mongoDB will generated one for all inserted documents.
         * example :
         * Document{{_id=6144543683a9b83a5efa5096, name=stephane, age=42, adresse=paris}}
         * Document{{_id=6144543683a9b83a5efa5097, name=corinne, age=37, adresse=neuilly}}
         * Document{{_id=61445479fe81de78a73e2045, name=stephane, age=42, adresse=paris}}
         * Document{{_id=61445479fe81de78a73e2046, name=corinne, age=37, adresse=neuilly}}
         *
         * please note that in this case, multiple document can have same datas, because no check is done
         * to see if document already exists
         *
         */

        postsDAO.deleteMany("stephane, ");

        postsDAO.insertOne("stephane", 42, "paris");
        postsDAO.insertOne("corinne", 37, "neuilly");
        postsDAO.insertOne("machin", 27, "senlis");
        postsDAO.listAllDocuments();

        postsDAO.getDocument("stephane");

        postsDAO.updateOneDocument("stephane, ","barcelone");
        postsDAO.updateManyDocument("stephane, ","bordeaux");

        postsDAO.listAllDocuments();




        System.out.println(">>> end");
    }
}
