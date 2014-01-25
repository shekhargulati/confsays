package com.shekhar.confsays.datasource;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Produces;


@ApplicationScoped
@Alternative
public class LocalMongoConfig implements MongoConfig {

    @Override
    @Produces
    public DB db() {
        try {
            MongoClient mongoClient = new MongoClient();

            mongoClient.setWriteConcern(WriteConcern.SAFE);
            DB db = mongoClient.getDB("archiver");
            return db;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
