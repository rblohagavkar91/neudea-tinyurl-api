package com.neueda.tinyurl.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neueda.tinyurl.controller.TinyUrlController;
import com.neueda.tinyurl.dao.TinyUrlRepository;
import com.neueda.tinyurl.entity.TinyUrl;
import com.neueda.tinyurl.model.TinyUrlRequest;
import com.neueda.tinyurl.model.TinyUrlResponse;

/**
 * This is service class for TinyUrl which contains business logic for creating short url and getting original url.
 */
@Service
public class TinyUrlService {

	private static final Logger logger = LoggerFactory.getLogger(TinyUrlController.class);
	private static final String TINYURL = "http://bit.ly/";
	
	@Autowired
	private BaseConversion baseConversion;
	
	@Autowired
	private TinyUrlRepository tinyUrlRepository;
	
	/**
     *  This method contains business logic to create tiny url
     *
     */
    public TinyUrlResponse createShortUrl(String longUrl) {
    	logger.info("Entering into createShortUrl of TinyUrlService class");
    	TinyUrlResponse tinyUrlResponse = new TinyUrlResponse();
    	TinyUrl tinyUrl = new TinyUrl();
        Timestamp createdTimestamp = new Timestamp(Calendar.getInstance().getTimeInMillis());
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 7);
        Timestamp expiryTimestamp = new Timestamp(cal.getTimeInMillis());
        
        tinyUrl.setLongUrl(longUrl);
        tinyUrl.setCreatedTimestamp(createdTimestamp);
        tinyUrl.setExpiryTimestamp(expiryTimestamp);
        TinyUrl entity = tinyUrlRepository.save(tinyUrl);
        tinyUrlResponse.setTinyUrlResponse(TINYURL+baseConversion.encode(entity.getUrlPrivacyid()));
        return tinyUrlResponse;
    }
	

	/**
     *  This method contains business logic to get original Url
     *
     */
    public TinyUrlResponse getOriginalUrl(String shortUrl) {
    	logger.info("Entering into getOriginalUrl of TinyUrlService class");
    	TinyUrlResponse tinyUrlResponse = new TinyUrlResponse();
        long id = baseConversion.decode(shortUrl);
        TinyUrl entity = tinyUrlRepository.findById(id).
       		orElseThrow(() -> new EntityNotFoundException("Entity Not found :" + shortUrl));

       if (entity.getExpiryTimestamp() != null && entity.getExpiryTimestamp().before(new Date())){
       	tinyUrlRepository.delete(entity);
           throw new EntityNotFoundException("Oops, Link has been expired!");
       }
       tinyUrlResponse.setTinyUrlResponse(entity.getLongUrl());
       return tinyUrlResponse;
   }
}
