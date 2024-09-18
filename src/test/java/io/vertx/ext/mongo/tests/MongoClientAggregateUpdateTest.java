package io.vertx.ext.mongo.tests;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.mongo.UpdateOptions;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * @author bfreuden
 */
public class MongoClientAggregateUpdateTest extends MongoTestBase {

  protected MongoClient mongoClient;

  @Override
  public void setUp() throws Exception {
    super.setUp();
    JsonObject config = getConfig();
    mongoClient = MongoClient.create(vertx, config);
    CountDownLatch latch = new CountDownLatch(1);
    dropCollections(mongoClient, latch);
    awaitLatch(latch);
  }

  @Override
  public void tearDown() throws Exception {
    mongoClient.close();
    super.tearDown();
  }

  @Test
  public void testAggregateUpdateCollection() {
    String collection = randomCollection();
    mongoClient.insert(collection, new JsonObject().put("price", 10).put("quantity", 1)).onComplete(onSuccess(id -> {
      mongoClient.insert(collection, new JsonObject().put("price", 20).put("quantity", 2)).onComplete(onSuccess(id2 -> {
        mongoClient.insert(collection, new JsonObject().put("price", 30).put("quantity", 10)).onComplete(onSuccess(id3 -> {
          mongoClient.updateCollection(collection,
            // reduce price of low quantity items
            new JsonObject().put("quantity", new JsonObject().put("$lte", 2)),
            new JsonArray().add(new JsonObject().put("$set", new JsonObject().put("price", new JsonObject().put("$subtract", new JsonArray().add("$price").add(2))))))
            .onComplete(
            onSuccess(res -> {
              assertEquals(2, res.getDocModified());
              assertEquals(2, res.getDocMatched());
              testComplete();
            }));
        }));
      }));
    }));
    await();
  }

  @Test
  public void testAggregateUpdateCollectionWithOptions() {
    String collection = randomCollection();
    mongoClient.insert(collection, new JsonObject().put("price", 10).put("quantity", 1)).onComplete(onSuccess(id -> {
      mongoClient.insert(collection, new JsonObject().put("price", 20).put("quantity", 2)).onComplete(onSuccess(id2 -> {
        mongoClient.insert(collection, new JsonObject().put("price", 30).put("quantity", 10)).onComplete(onSuccess(id3 -> {
          mongoClient.updateCollectionWithOptions(collection,
            // reduce price of low quantity items
            new JsonObject().put("quantity", new JsonObject().put("$lte", 2)),
            new JsonArray().add(new JsonObject().put("$set", new JsonObject().put("price", new JsonObject().put("$subtract", new JsonArray().add("$price").add(2))))),
            new UpdateOptions()).onComplete(onSuccess(res -> {
              assertEquals(2, res.getDocModified());
              assertEquals(2, res.getDocMatched());
              testComplete();
            }));
        }));
      }));
    }));
    await();
  }

}
