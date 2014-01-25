package com.shekhar.confsays.datasource;

import javax.enterprise.inject.Produces;
import javax.inject.Named;

import com.mongodb.DB;
import com.mongodb.DBCollection;

public interface MongoConfig {

    public DB db();

}