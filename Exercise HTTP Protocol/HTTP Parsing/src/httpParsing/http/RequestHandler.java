package httpParsing.http;

import java.util.List;

public interface RequestHandler {

    byte[] handleRequest(String requestContent, List<String> validUrls);

    HttpResponse getHttpResponse();
}
