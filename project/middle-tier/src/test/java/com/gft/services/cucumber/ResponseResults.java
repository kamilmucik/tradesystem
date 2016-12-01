package com.gft.services.cucumber;

import org.apache.commons.io.IOUtils;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

/**
 * Created by kamu on 2016-09-16.
 */
public class ResponseResults
{
    private final ClientHttpResponse theResponse;
    private final String body;

    protected ResponseResults(final ClientHttpResponse response) throws IOException {
        this.theResponse = response;
        final InputStream bodyInputStream = response.getBody();
        if (null == bodyInputStream) {
            this.body = "{}";
        } else {
            final StringWriter stringWriter = new StringWriter();
            IOUtils.copy(bodyInputStream, stringWriter);
            this.body = stringWriter.toString();
        }
    }

    protected ClientHttpResponse getTheResponse()
    {
        return theResponse;
    }

    protected String getBody() {
        return body;
    }
}
