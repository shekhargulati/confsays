package com.shekhar.confsays.service;

import com.shekhar.confsays.domain.Conference;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class ArchiverClient {

    private final static String archiverHost = System.getenv("ARCHIVER_HOST") == null ? "http://localhost:8080/archiver-1.0/api/v1/jobs" : System.getenv("ARCHIVER_HOST");

    public String submitNewJob(Conference conference) {
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpContext cntxt = new BasicHttpContext();

            HttpPost httpPost = new HttpPost(archiverHost);
            httpPost.addHeader("Content-Type", "application/json");
            String jsonData = toJson(new Job(conference.getHashtags(), conference.getTitle(), conference.getId()));
            httpPost.setEntity(new StringEntity(jsonData));
            org.apache.http.HttpResponse httpResponse = client.execute(httpPost);
            return EntityUtils.toString(httpResponse.getEntity());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static <T> String toJson(T obj) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            return "Unable to generate json";
        }
    }

    public void stopJob(String jobId) {
        DefaultHttpClient client = new DefaultHttpClient();
        HttpContext cntxt = new BasicHttpContext();
        HttpDelete httpDelete = new HttpDelete(archiverHost + "/" + jobId);
        try {
            client.execute(httpDelete);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
