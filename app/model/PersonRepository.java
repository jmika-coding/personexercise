package model;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.UpdateOptions;
import connection.DatabaseConnection;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class PersonRepository {

    DatabaseConnection databaseConnection;
    MongoCollection<Document> mongoCollection;

    @Inject
    public PersonRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
        this.mongoCollection = databaseConnection.getDocumentMongoCollection();
    }

    public List<PersonEntity> fetchAllPerson(){

        FindIterable<Document> findIterable = findAll();
        MongoCursor<Document> mongoCursor = findIterable.iterator();

        List<PersonEntity> personList = new ArrayList<>();

        while(mongoCursor.hasNext()) {
            Document personDocument = mongoCursor.next();
            personList.add(personSerializer(personDocument));
        }

        return personList;
    }

    public List<PersonEntity> fetchOnePerson(String id){

        FindIterable<Document> findIterable = findOneById(id);
        MongoCursor<Document> mongoCursor = findIterable.iterator();

        List<PersonEntity> personList = new ArrayList<>();

        while(mongoCursor.hasNext()) {
            Document personDocument = mongoCursor.next();
            personList.add(personSerializer(personDocument));
        }

        return personList;
    }

    public PersonEntity personSerializer(Document document) {
        String name = document.get("name").toString();
        String firstName = document.get("firstName").toString();
        int age = Integer.valueOf(document.get("age").toString());
        String id = document.get("_id").toString();
        return new PersonEntity(name, firstName, age, id);
    }

    public FindIterable<Document> findAll(){
        return mongoCollection.find();
    }

    public FindIterable<Document> findOne(){
        return mongoCollection.find().sort(new BasicDBObject("_id", -1)).limit(1);
    }

    // Can delete if we want
    public FindIterable<Document> findOneByName(String name){
        return mongoCollection.find().filter(new BasicDBObject("name", name)).limit(1);
    }

    public FindIterable<Document> findOneById(String id){
        return mongoCollection.find().filter(new BasicDBObject("_id", new ObjectId(id))).limit(1);
    }

    public void saveCollection(Document document) {

        BasicDBObject newDoc = new BasicDBObject();
        newDoc.put("name", document.get("name").toString());
        newDoc.put("firstName", document.get("firstName").toString());
        newDoc.put("age", Integer.valueOf(document.get("age").toString()));

        // Allow to update or create if not exist
        UpdateOptions options = new UpdateOptions().upsert(true);
        mongoCollection.updateOne(new Document().append("name", document.get("name").toString()), new Document().append("$set", newDoc), options);
    }

    public void delete(String id) {
        mongoCollection.deleteOne(new BasicDBObject("_id", new ObjectId(id)));
    }

}
