package MongoExample;

import com.google.gson.Gson;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import org.bson.Document;

import java.util.Arrays;

import static com.mongodb.client.model.Filters.eq;

public class App {
    public static void main(String[] args) {

        MongoClient mongoClient = MongoClients.create();

        MongoDatabase database = mongoClient.getDatabase("Products");

        MongoCollection<Document> collection = database.getCollection("test");

        collection.drop();

        Document asus = new Document()
                .append("brand", "Asus")
                .append("model", "Zenbook")
                .append("quantity", 5);
        collection.insertOne(asus);

        Document dell = new Document()
                .append("brand", "Dell")
                .append("model", "Inspiron")
                .append("quantity", 3);
        collection.insertOne(dell);

        Product lenovo = new Product("Lenovo", "ThinkPad 13", 15);
        Product lenovo2 = new Product("Lenovo", "ThinkPad 15", 1);
        Product lenovo3 = new Product("Lenovo", "ThinkPad 17", 12);

        Gson gson = new Gson();
        // najpierw Product->Json a potem Json->Document
        Document lenovoDoc = Document.parse(gson.toJson(lenovo));
        Document lenovoDoc2 = Document.parse(gson.toJson(lenovo2));
        Document lenovoDoc3 = Document.parse(gson.toJson(lenovo3));

        collection.insertMany(Arrays.asList(lenovoDoc, lenovoDoc2, lenovoDoc3));

        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        } finally {
            cursor.close();
        }

        System.out.println("*****");

        //zamiana na Product zeby nie bylo to co wyzej
        for (Document cur : collection.find()) {
            //cur nie jest surowym Json wiec zamieniamy go metoda toJson() do formatu String
            Product product = gson.fromJson(cur.toJson(), Product.class);
            System.out.println(product);
        }

        System.out.println("*****");

        FindIterable<Document> availableProducts = collection.find(Filters.gt("quantity", 10));
        for (Document availableProduct : availableProducts) {
            Product product = gson.fromJson(availableProduct.toJson(), Product.class);
            System.out.println(product);

        }

        System.out.println("*****");

        FindIterable<Document> ProductsWith1element = collection.find(eq("quantity", 1));
        for (Document availableProduct : ProductsWith1element) {
            Product product = gson.fromJson(availableProduct.toJson(), Product.class);
            System.out.println(product);
        }

        System.out.println("*****");

        FindIterable<Document> LenovoProducts = collection.find(eq("brand", "Lenovo"));
        for (Document availableProduct : LenovoProducts) {
            Product product = gson.fromJson(availableProduct.toJson(), Product.class);
            System.out.println(product);
        }

        System.out.println("*****");

        collection.deleteOne(eq("quantity", 1));

        for (Document cur : collection.find()) {
            Product product = gson.fromJson(cur.toJson(), Product.class);
            System.out.println(product);
        }

        System.out.println("*****");

        collection.updateOne(eq("quantity", 12), new Document("$set",
                new Document("quantity", 112)));

        FindIterable<Document> Sorted = collection.find().sort(Sorts.ascending("quantity"));
        for (Document cur : Sorted) {
            Product product = gson.fromJson(cur.toJson(), Product.class);
            System.out.println(product);
        }

        System.out.println("*****");

        FindIterable<Document> SortedBrands = collection.find().sort(Sorts.descending("brand"));
        for (Document cur : SortedBrands) {
            Product product = gson.fromJson(cur.toJson(), Product.class);
            System.out.println(product);
        }

    }
}

