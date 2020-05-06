package br.com.fiap.serverless.handler;

import br.com.fiap.serverless.dao.TripRepository;
import br.com.fiap.serverless.model.HandlerRequest;
import br.com.fiap.serverless.model.HandlerResponse;
import br.com.fiap.serverless.model.Trip;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.List;

public class GetTripsByPeriod implements RequestHandler<HandlerRequest, HandlerResponse> {

    private TripRepository repository = new TripRepository();

    @Override
    public HandlerResponse handleRequest(HandlerRequest request, Context context) {

        final String starts = request.getQueryStringParameters().get("starts");
        final String ends = request.getQueryStringParameters().get("ends");

        context.getLogger().log("Searching for trips between " + starts + " and " + ends);

        final List<Trip> trips = this.repository.findByPeriod(starts, ends);

        if(trips == null || trips.isEmpty()) {
            return HandlerResponse.builder().setStatusCode(200).setObjectBody(trips).build();
        }

        return HandlerResponse.builder().setStatusCode(200).setObjectBody(trips).build();
    }
}