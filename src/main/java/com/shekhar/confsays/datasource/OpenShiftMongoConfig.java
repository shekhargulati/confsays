package com.shekhar.confsays.datasource;


import com.mongodb.*;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.net.UnknownHostException;

@ApplicationScoped
public class OpenShiftMongoConfig implements MongoConfig{

    @Produces
    public DB db() {
        try {
            String host = System.getenv("OPENSHIFT_MONGODB_DB_HOST");
            int port = Integer.parseInt(System.getenv("OPENSHIFT_MONGODB_DB_PORT"));
            String dbname = System.getenv("OPENSHIFT_APP_NAME");
            String username = System.getenv("OPENSHIFT_MONGODB_DB_USERNAME");
            String password = System.getenv("OPENSHIFT_MONGODB_DB_PASSWORD");
            MongoClientOptions mongoClientOptions = MongoClientOptions.builder().connectionsPerHost(20).build();
            MongoClient mongoClient = new MongoClient(new ServerAddress(host, port), mongoClientOptions);
            DB db = mongoClient.getDB(dbname);
            if (db.authenticate(username, password.toCharArray())) {
                return db;
            } else {
                throw new RuntimeException("Not able to authenticate with MongoDB");
            }

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
