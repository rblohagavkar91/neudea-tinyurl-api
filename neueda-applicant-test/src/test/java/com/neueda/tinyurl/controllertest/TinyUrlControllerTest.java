package com.neueda.tinyurl.controllertest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.neueda.tinyurl.controller.TinyUrlController;
import com.neueda.tinyurl.model.TinyUrlRequest;
import com.neueda.tinyurl.model.TinyUrlResponse;
import com.neueda.tinyurl.service.TinyUrlService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TinyUrlControllerTest {
	
	@Mock
	TinyUrlService mockUrlService;
	
	@InjectMocks
	TinyUrlController urlController;
	
	@Test
	public void urlConversionTest() {
		TinyUrlResponse tinyUrlResponse = new TinyUrlResponse();
		TinyUrlRequest tinyUrlRequest = new TinyUrlRequest();
		String TINYURL = "http://bit.ly/";
		String longUrl = "https://www.geeksforgeeks.org/create-immutable-class-java/";
		tinyUrlRequest.setLongUrl(longUrl);
		tinyUrlResponse.setTinyUrlResponse(TINYURL+"ax");
		when(mockUrlService.createShortUrl(longUrl)).thenReturn(tinyUrlResponse);
		ResponseEntity<Object> response = urlController.createShortUrl(tinyUrlRequest);
		assertEquals(200, response.getStatusCodeValue());
	}

	@Test
	public void getAndRedirectTest() {
		String shortUrl = "http://bit.ly/bg";
		TinyUrlResponse tinyUrlResponse = new TinyUrlResponse();
		tinyUrlResponse.setTinyUrlResponse(shortUrl);
		when(mockUrlService.getOriginalUrl("bg")).thenReturn(tinyUrlResponse);
		ResponseEntity<Object> response = urlController.getOriginalUrl("bg");
		assertEquals(200, response.getStatusCodeValue());
	}
	
}
