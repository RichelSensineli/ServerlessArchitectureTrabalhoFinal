package br.com.fiap.serverless.handler;

import br.com.fiap.serverless.dao.TripRepository;
import br.com.fiap.serverless.model.HandlerRequest;
import br.com.fiap.serverless.model.HandlerResponse;
import br.com.fiap.serverless.model.Trip;
import br.com.fiap.serverless.model.TripDTO;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class CreateTrip implements RequestHandler<HandlerRequest, HandlerResponse> {

    private final TripRepository repository = new TripRepository();

    @Override
    public HandlerResponse handleRequest(final HandlerRequest request, final Context context) {

        Trip trip = null;
        try {
            trip = new ObjectMapper().readValue(request.getBody(), Trip.class);
        } catch (IOException e) {
            return HandlerResponse.builder().setStatusCode(400).setRawBody("There is a error in your Trip!").build();
        }
        context.getLogger().log("Creating a new trip to the country " + trip.getCountry());
        final Trip tripRecorded = repository.save(trip);
        final TripDTO tripDTO = new TripDTO(tripRecorded.getId(), tripRecorded.getUrl());
        return HandlerResponse.builder().setStatusCode(201).setObjectBody(tripDTO).build();
    }
  
}