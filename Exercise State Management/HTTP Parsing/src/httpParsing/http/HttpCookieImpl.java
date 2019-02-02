package httpParsing.http;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class HttpCookieImpl implements HttpCookie {
	
	private Map<String, String> cookies;
	
	public HttpCookieImpl(HttpRequest httpRequest) {
		this.cookies = new LinkedHashMap<>();
		this.setCookies(httpRequest);
	}
	
	@Override
	public Map<String, String> getCookies() {
		return Collections.unmodifiableMap(this.cookies);
	}
	
	private void addCookie(String key, String value) {
		this.cookies.putIfAbsent(key, value);
	}
	
	private void setCookies(HttpRequest httpRequest) {
		if (!httpRequest.getCookies().isEmpty()) {
			for (String value : httpRequest.getCookies().values()) {
				String[] keyValue = value.split("; ");
				for (String str : keyValue) {
					String[] kvp = str.split("=");
					addCookie(kvp[0], kvp[1]);
				}
			}
		}
	}
}
