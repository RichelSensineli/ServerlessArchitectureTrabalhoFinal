package br.com.fiap.serverless.dao;

import br.com.fiap.serverless.model.Trip;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TripRepository {

    private static final DynamoDBMapper mapper = DynamoDBManager.mapper();

    public Trip save(final Trip trip) {
        mapper.save(trip);
        return trip;
    }

    public List<Trip> findByPeriod(final String starts, final String ends) {

        final Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":val1", new AttributeValue().withS(starts));
        eav.put(":val2", new AttributeValue().withS(ends));

        final Map<String, String> expression = new HashMap<>();
        expression.put("#date", "date");

        final DynamoDBQueryExpression<Trip> queryExpression = new DynamoDBQueryExpression<Trip>()
                .withKeyConditionExpression("#date between :val1 and :val2")
                .withExpressionAttributeValues(eav)
                .withExpressionAttributeNames(expression);

        final List<Trip> studies = mapper.query(Trip.class, queryExpression);

        return studies;
    }

    public List<Trip> findById(String id) {

        final Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":val1", new AttributeValue().withS(id));

        final DynamoDBQueryExpression<Trip> queryExpression = new DynamoDBQueryExpression<Trip>()
                .withKeyConditionExpression("id = :val1").withExpressionAttributeValues(eav);

        final List<Trip> trips = mapper.query(Trip.class, queryExpression);

        return trips;
    }
}