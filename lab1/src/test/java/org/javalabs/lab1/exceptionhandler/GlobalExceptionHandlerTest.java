package org.javalabs.lab1.exceptionhandler;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class GlobalExceptionHandlerTest {

    @Test
    void testHandleNoResourceFoundException() {
        NoResourceFoundException ex = mock(NoResourceFoundException.class);
        WebRequest request = mock(WebRequest.class);

        GlobalExceptionHandler handler = new GlobalExceptionHandler();

        ResponseEntity<Object> responseEntity = handler.handleNoResourceFoundException(ex, request);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("404: Not Found", responseEntity.getBody());
    }

    @Test
    public void testHandleRuntimeException() {
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        RuntimeException ex = new RuntimeException("Internal Server Error");

        ResponseEntity<Object> response = handler.handleRuntimeException(ex, null);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("Internal Server Error"));
    }

    @Test
    public void testHandleHttpClientErrorException() {
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        HttpClientErrorException ex = new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Bad Request");

        ResponseEntity<Object> response = handler.handleHttpClientErrorException(ex, null);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("Bad Request"));
    }

    @Test
    public void testHandleMethodNotSupportedException() {
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        HttpRequestMethodNotSupportedException ex = new HttpRequestMethodNotSupportedException("Method Not Allowed");

        ResponseEntity<Object> response = handler.handleMethodNotSupportedException(ex, null);

        assertEquals(HttpStatus.METHOD_NOT_ALLOWED, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("Method Not Allowed"));
    }
}