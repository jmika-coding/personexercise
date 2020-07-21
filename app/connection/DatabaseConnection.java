package connection;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class DatabaseConnection {

    private MongoCollection<Document> documentMongoCollection;

    public DatabaseConnection(){
        MongoClient mongoClient = MongoClients.create("mongodb://root:example@localhost:27000");
        MongoDatabase mongoDatabase = mongoClient.getDatabase("mydatabase");
        this.documentMongoCollection = mongoDatabase.getCollection("Person");
        System.err.println("Creation database");
    }

    public MongoCollection<Document> getDocumentMongoCollection() {
        return documentMongoCollection;
    }
}
