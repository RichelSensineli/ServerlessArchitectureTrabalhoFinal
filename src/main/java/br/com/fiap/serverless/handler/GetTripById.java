package br.com.fiap.serverless.handler;

import br.com.fiap.serverless.dao.TripRepository;
import br.com.fiap.serverless.model.HandlerRequest;
import br.com.fiap.serverless.model.HandlerResponse;
import br.com.fiap.serverless.model.Trip;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.List;

public class GetTripById implements RequestHandler<HandlerRequest, HandlerResponse> {

    private final TripRepository repository = new TripRepository();

    @Override
    public HandlerResponse handleRequest(HandlerRequest request, Context context) {

        final String id = request.getPathParameters().get("id");

        context.getLogger().log("Searching for trips with id " + id);

        final List<Trip> trips = this.repository.findById(id);

        if (trips == null || trips.isEmpty()) {
            return HandlerResponse.builder().setStatusCode(404).build();
        }

        return HandlerResponse.builder().setStatusCode(200).setObjectBody(trips).build();
    }
  
}