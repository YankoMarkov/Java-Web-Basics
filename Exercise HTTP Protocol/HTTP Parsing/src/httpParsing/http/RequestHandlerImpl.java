package httpParsing.http;

import java.util.List;
import java.util.Map;

public class RequestHandlerImpl implements RequestHandler {

    private HttpRequest httpRequest;
    private HttpResponse httpResponse;

    public RequestHandlerImpl() {
        this.httpResponse = new HttpResponseImpl();
    }

    @Override
    public HttpResponse getHttpResponse() {
        return httpResponse;
    }

    @Override
    public byte[] handleRequest(String requestContent, List<String> validUrls) {
        this.httpRequest = new HttpRequestImpl(requestContent);

        byte[] result = null;

        if (this.httpRequest.getMethod().equalsIgnoreCase("GET")) {
            result = processGetRequest(validUrls);
        }
        if (this.httpRequest.getMethod().equalsIgnoreCase("POST")) {
            result = processPostRequest(validUrls);
        }
        return result;
    }

    private byte[] processPostRequest(List<String> validUrls) {
        String url = this.httpRequest.getRequestUrl();
        byte[] result = null;
        StringBuilder res = new StringBuilder();
        addResponseHeader();

        if (!validUrls.contains(url)) {
            res.append("The requested functionality was not found.");
            result = notFound(res.toString().getBytes());
        } else if (this.httpRequest.getBodyParameters().isEmpty()) {
            res.append("There was an error with the requested functionality due to malformed request.");
            result = badRequest(res.toString().getBytes());
        } else if (!this.httpRequest.getHeaders().containsKey("Authorization") &&
                !this.httpRequest.getBodyParameters().isEmpty()) {
            res.append("You are not authorized to access the requested functionality.");
            result = unauthorized(res.toString().getBytes());
        } else {
            res.append(String.format("Greetings Pesho! You have successfully created %s with quantity - %s, price - %s.",
                    this.httpRequest.getBodyParameters().get("name"),
                    this.httpRequest.getBodyParameters().get("quantity"),
                    this.httpRequest.getBodyParameters().get("price")));
            result = ok(res.toString().getBytes());
        }

        return result;
    }

    private byte[] processGetRequest(List<String> validUrls) {

        return null;
    }

    private void addResponseHeader() {
        for (Map.Entry<String, String> kvp : this.httpRequest.getHeaders().entrySet()) {
            if (kvp.getKey().equalsIgnoreCase("date") ||
                    kvp.getKey().equalsIgnoreCase("host") ||
                    kvp.getKey().equalsIgnoreCase("content-type")) {
                this.httpResponse.addHeader(kvp.getKey(), kvp.getValue());
            }
        }
    }

    private byte[] ok(byte[] content) {
        this.httpResponse.setStatusCode(HttpStatus.OK);
        this.httpResponse.setContent(content);
        return this.httpResponse.getBytes();
    }

    private byte[] badRequest(byte[] content) {
        this.httpResponse.setStatusCode(HttpStatus.BAD_REQUEST);
        this.httpResponse.setContent(content);
        return this.httpResponse.getBytes();
    }

    private byte[] notFound(byte[] content) {
        this.httpResponse.setStatusCode(HttpStatus.NOT_FOUND);
        this.httpResponse.setContent(content);
        return this.httpResponse.getBytes();
    }

    private byte[] unauthorized(byte[] content) {
        this.httpResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
        this.httpResponse.setContent(content);
        return this.httpResponse.getBytes();
    }
}
