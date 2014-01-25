package com.shekhar.confsays.dao;


import com.mongodb.*;
import com.shekhar.confsays.domain.Conference;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ConferenceDaoImpl implements ConferenceDao {


    private final DBCollection dbCollection;

    @Inject
    public ConferenceDaoImpl(DB db) {
        this.dbCollection = db.getCollection("conferences");
    }

    @Override
    public void save(Conference conference) {
        dbCollection.insert(Converters.toDbObject(conference));
    }

    @Override
    public List<Conference> findAll() {
        DBCursor dbCursor = dbCollection.find();
        return Converters.toConferences(dbCursor);
    }

    @Override
    public Conference find(String id) {
        DBObject dbObject = dbCollection.findOne(new BasicDBObject("_id", new ObjectId(id)));
        if (dbObject == null) {
            return null;
        }
        return Converters.toConference(dbObject);
    }

    @Override
    public void update(String id, Conference conference) {
        BasicDBObject dbObject = Converters.toDbObject(conference);
        dbObject.append("_id", new ObjectId(id));
        dbCollection.save(dbObject);
    }

    @Override
    public void delete(String id) {
        dbCollection.remove(new BasicDBObject(new BasicDBObject("_id", new ObjectId(id))));
    }

    @Override
    public Conference findByTitle(String name) {
        return Converters.toConference(dbCollection.findOne(new BasicDBObject("urlFragment", name)));
    }

    @Override
    public List<String> findSpeakers(String conferenceId) {
        BasicDBObject query = new BasicDBObject();
        query.append("_id", new ObjectId(conferenceId));
        BasicDBObject fields = new BasicDBObject();
        fields.append("speakers", 1);

        DBObject dbObject = dbCollection.findOne(query, fields);

        BasicDBList speakers = (BasicDBList) dbObject.get("speakers");

        if (speakers != null) {
            List<String> list = new ArrayList<String>();
            for (Object obj : speakers) {

                list.add((String) obj);
            }
            return list;
        }
        return null;
    }

}
