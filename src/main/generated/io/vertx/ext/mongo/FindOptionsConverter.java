package io.vertx.ext.mongo;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

/**
 * Converter and mapper for {@link io.vertx.ext.mongo.FindOptions}.
 * NOTE: This class has been automatically generated from the {@link io.vertx.ext.mongo.FindOptions} original class using Vert.x codegen.
 */
public class FindOptionsConverter {

   static void fromJson(Iterable<java.util.Map.Entry<String, Object>> json, FindOptions obj) {
    for (java.util.Map.Entry<String, Object> member : json) {
      switch (member.getKey()) {
        case "collation":
          if (member.getValue() instanceof JsonObject) {
            obj.setCollation(new io.vertx.ext.mongo.CollationOptions((io.vertx.core.json.JsonObject)member.getValue()));
          }
          break;
        case "fields":
          if (member.getValue() instanceof JsonObject) {
            obj.setFields(((JsonObject)member.getValue()).copy());
          }
          break;
        case "sort":
          if (member.getValue() instanceof JsonObject) {
            obj.setSort(((JsonObject)member.getValue()).copy());
          }
          break;
        case "limit":
          if (member.getValue() instanceof Number) {
            obj.setLimit(((Number)member.getValue()).intValue());
          }
          break;
        case "skip":
          if (member.getValue() instanceof Number) {
            obj.setSkip(((Number)member.getValue()).intValue());
          }
          break;
        case "batchSize":
          if (member.getValue() instanceof Number) {
            obj.setBatchSize(((Number)member.getValue()).intValue());
          }
          break;
        case "hint":
          if (member.getValue() instanceof JsonObject) {
            obj.setHint(((JsonObject)member.getValue()).copy());
          }
          break;
        case "hintString":
          if (member.getValue() instanceof String) {
            obj.setHintString((String)member.getValue());
          }
          break;
      }
    }
  }

   static void toJson(FindOptions obj, JsonObject json) {
    toJson(obj, json.getMap());
  }

   static void toJson(FindOptions obj, java.util.Map<String, Object> json) {
    if (obj.getCollation() != null) {
      json.put("collation", obj.getCollation().toJson());
    }
    if (obj.getFields() != null) {
      json.put("fields", obj.getFields());
    }
    if (obj.getSort() != null) {
      json.put("sort", obj.getSort());
    }
    json.put("limit", obj.getLimit());
    json.put("skip", obj.getSkip());
    json.put("batchSize", obj.getBatchSize());
    if (obj.getHint() != null) {
      json.put("hint", obj.getHint());
    }
    if (obj.getHintString() != null) {
      json.put("hintString", obj.getHintString());
    }
  }
}
