package httpParsing.http;

import java.util.*;

public class HttpRequestImpl implements HttpRequest {

    private Map<String, String> headers;
    private Map<String, String> bodyParameters;
    private String method;
    private String requestUrl;

    public HttpRequestImpl(String requestContent) {
        this.setMethod(requestContent);
        this.setRequestUrl(requestContent);
        setHeader(requestContent);
        setBody(requestContent);
    }

    @Override
    public Map<String, String> getHeaders() {
        return Collections.unmodifiableMap(this.headers);
    }

    @Override
    public Map<String, String> getBodyParameters() {
        return Collections.unmodifiableMap(this.bodyParameters);
    }

    @Override
    public String getMethod() {
        return this.method;
    }

    @Override
    public void setMethod(String method) {
        this.method = method.split(" ")[0];
    }

    @Override
    public String getRequestUrl() {
        return this.requestUrl;
    }

    @Override
    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl.split(" ")[1];
    }

    @Override
    public void addHeader(String header, String value) {
        this.headers.putIfAbsent(header, value);
    }

    @Override
    public void addBodyParameter(String parameter, String value) {
        this.bodyParameters.putIfAbsent(parameter, value);
    }

    @Override
    public boolean isResource() {
        if (this.requestUrl.contains(".")) {
            return true;
        }
        return false;
    }

    private void setHeader(String requestContent) {
        this.headers = new LinkedHashMap<>();
        int index = requestContent.lastIndexOf("\r\n");
        String header = requestContent.substring(0, index);
        if (!header.equals("")) {
            String[] input = Arrays.stream(header.split("[\r\n]"))
                    .filter(a -> !a.equals(""))
                    .skip(1)
                    .toArray(String[]::new);

            for (String str : input) {
                String[] result = str.split(": ");
                addHeader(result[0], result[1]);
            }
        }
    }

    private void setBody(String requestContent) {
        this.bodyParameters = new LinkedHashMap<>();
        int index = requestContent.lastIndexOf("\r\n");
        String body = requestContent.substring(index + 2);
        if (!body.equals("")) {
            String[] input = body.split("&");

            for (String str : input) {
                String[] result = str.split("=");
                addBodyParameter(result[0], result[1]);
            }
        }
    }
}
