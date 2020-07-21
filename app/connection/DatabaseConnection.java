package connection;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class DatabaseConnection {

    private MongoCollection<Document> documentMongoCollection;

    public /*MongoCollection<Document>*/ DatabaseConnection(){
        MongoClient mongoClient = MongoClients.create("mongodb://root:example@localhost:27000");
        //MongoCredential mongoCredential = MongoCredential.createCredential("root", "mydatabase", "example".toCharArray());
        MongoDatabase mongoDatabase = mongoClient.getDatabase("mydatabase");
        /*MongoCollection<Document> mongoCollection =*/
        //return mongoDatabase.getCollection("Person");
        this.documentMongoCollection = mongoDatabase.getCollection("Person");
        System.err.println("Creation database");
    }

    public MongoCollection<Document> getDocumentMongoCollection() {
        return documentMongoCollection;
    }
}
