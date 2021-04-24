package com.neueda.tinyurl.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neueda.tinyurl.service.TinyUrlService;

/**
 * This API is used to create short URL from Long Url and get the original URL.
 */
@RestController
@RequestMapping("/api")
public class TinyUrlController {
	
	private static final Logger logger = LoggerFactory.getLogger(TinyUrlController.class);
	
	@Autowired
	private TinyUrlService tinyUrlService;
	
	/**
	 * This is API method for short URL creation.
	 * 
	 * @param longUrl
	 * @return ResponseEntity
	 */
	@PostMapping("/longUrl")
	public ResponseEntity<Object> createShortUrl(@RequestBody String longUrl) {
		logger.info("Entering into createShortUrl method and input longURL is : "+longUrl);
		return new ResponseEntity<>(tinyUrlService.createShortUrl(longUrl), HttpStatus.OK);
	}
	
	/**
	 * This is API method for getting original Long url.
	 * 
	 * @param shortUrlId
	 * @return longUrl
	 */
	@GetMapping("/shortUrl/{shortUrlId}")
    public String getOriginalUrl(@PathVariable String shortUrlId) {
		logger.info("Entering into getOriginalUrl method and input shortUrlId is : "+shortUrlId);
        return tinyUrlService.getOriginalUrl(shortUrlId);
    }
    
}
