package httpParsing.http;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class HttpResponseImpl implements HttpResponse {

    private Map<String, String> headers;
    private HttpStatus statusCode;
    private byte[] content;

    public HttpResponseImpl() {
        this.headers = new LinkedHashMap<>();
        this.content = new byte[0];
    }

    @Override
    public Map<String, String> getHeaders() {
        return Collections.unmodifiableMap(this.headers);
    }

    @Override
    public HttpStatus getStatusCode() {
        return this.statusCode;
    }

    @Override
    public byte[] getContent() {
        return this.content;
    }

    @Override
    public byte[] getBytes() {
        byte[] headerBytes = getHeaderBytes();
        byte[] bodyBytes = this.getContent();
        byte[] fullResponse = new byte[headerBytes.length + bodyBytes.length];

        System.arraycopy(headerBytes, 0, fullResponse, 0, headerBytes.length);
        System.arraycopy(bodyBytes, 0, fullResponse, headerBytes.length, bodyBytes.length);

        return fullResponse;
    }

    @Override
    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public void addHeader(String header, String value) {
        this.headers.putIfAbsent(header, value);
    }

    private byte[] getHeaderBytes() {
        String response = String.format("HTTP/1.1 %s%s",
                this.getStatusCode().getStatusPhrase(),
                System.lineSeparator());
        StringBuilder header = new StringBuilder(response);

        for (Map.Entry<String, String> entry : this.headers.entrySet()) {
            header.append(entry.getKey())
                    .append(": ")
                    .append(entry.getValue())
                    .append(System.lineSeparator());
        }
        header.append(System.lineSeparator());

        return header.toString().getBytes();
    }
}
