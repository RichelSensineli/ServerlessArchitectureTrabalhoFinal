package br.com.fiap.serverless.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "trip")
public class Trip {

  @DynamoDBHashKey(attributeName = "id")
	private String id;

  @DynamoDBRangeKey(attributeName = "date")
  private String date;
  
  @DynamoDBAttribute(attributeName = "country")
  private String country;
  
  @DynamoDBAttribute(attributeName = "city")
  private String city;
  
  @DynamoDBAttribute(attributeName = "url")
  private String url;

  public Trip(String id, String date, String country, String city, String url) {
    this.id = id;
    this.date = date;
    this.country = country;
    this.city = city;
    this.url = url;
  }

  public Trip() {
    super();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}