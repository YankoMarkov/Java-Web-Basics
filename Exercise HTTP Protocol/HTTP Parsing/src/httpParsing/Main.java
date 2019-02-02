package httpParsing;

import httpParsing.http.RequestHandler;
import httpParsing.http.RequestHandlerImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        List<String> validUrls = getValidUrls();
        String request = getRequest();

        RequestHandler requestHandler = new RequestHandlerImpl();
        requestHandler.handleRequest(request, validUrls);

        byte[] response = requestHandler.getHttpResponse().getBytes();
        StringBuilder result = new StringBuilder();

        for (byte b : response) {
            result.append((char) b);
        }
        System.out.println(result);
    }

    private static List<String> getValidUrls() throws IOException {
        return Arrays.asList(read.readLine().split("\\s+"));
    }

    private static String getRequest() throws IOException {
        StringBuilder request = new StringBuilder();

        String line = null;
        while ((line = read.readLine()) != null && line.length() > 0) {
            request.append(line).append(System.lineSeparator());
        }
        request.append(System.lineSeparator());

        if ((line = read.readLine()) != null && line.length() > 0) {
            request.append(line);
        }
        return request.toString();
    }
}
