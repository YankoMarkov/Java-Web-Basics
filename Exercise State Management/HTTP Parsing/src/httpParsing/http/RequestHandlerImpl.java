package httpParsing.http;

import java.util.Map;

public class RequestHandlerImpl implements RequestHandler {
	
	private HttpRequest httpRequest;
	private HttpCookie httpCookie;
	
	public RequestHandlerImpl() {
	}
	
	@Override
	public byte[] handleRequest(String requestContent) {
		this.httpRequest = new HttpRequestImpl(requestContent);
		this.httpCookie = new HttpCookieImpl(this.httpRequest);
		
		byte[] result = null;
		
		if (this.httpRequest.getMethod().equalsIgnoreCase("GET")) {
			result = processGetRequest();
		}
		if (this.httpRequest.getMethod().equalsIgnoreCase("POST")) {
			result = processPostRequest();
		}
		return result;
	}
	
	private byte[] processPostRequest() {
		StringBuilder result = new StringBuilder();
		
		if (this.httpRequest.getBodyParameters().isEmpty()) {
			result.append("There was an error with the requested functionality due to malformed request.");
		} else if (this.httpCookie.getCookies().isEmpty()) {
			result.append("There was no cookies.");
		} else {
			for (Map.Entry<String, String> kvp : this.httpCookie.getCookies().entrySet()) {
				result.append(kvp.getKey()).append(" <-> ").append(kvp.getValue()).append(System.lineSeparator());
			}
		}
		
		return result.toString().getBytes();
	}
	
	private byte[] processGetRequest() {
		StringBuilder result = new StringBuilder();
		
		if (this.httpCookie.getCookies().isEmpty()) {
			result.append("There was no cookies.");
		} else {
			for (Map.Entry<String, String> kvp : this.httpCookie.getCookies().entrySet()) {
				result.append(kvp.getKey()).append(" <-> ").append(kvp.getValue()).append(System.lineSeparator());
			}
		}
		
		return result.toString().getBytes();
	}
}
