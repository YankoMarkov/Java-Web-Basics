package httpParsing.http;

public interface RequestHandler {
	
	byte[] handleRequest(String requestContent);
}
