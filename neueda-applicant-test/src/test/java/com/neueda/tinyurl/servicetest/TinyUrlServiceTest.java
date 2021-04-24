package com.neueda.tinyurl.servicetest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.neueda.tinyurl.dao.TinyUrlRepository;
import com.neueda.tinyurl.entity.TinyUrl;
import com.neueda.tinyurl.model.TinyUrlResponse;
import com.neueda.tinyurl.service.BaseConversion;
import com.neueda.tinyurl.service.TinyUrlService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TinyUrlServiceTest {
	
	Logger log = LoggerFactory.getLogger(TinyUrlServiceTest.class);
	
	@Mock
	private BaseConversion mockBaseConversion;
	
	@Mock
	private TinyUrlRepository mockUrlRepository;
	
	@InjectMocks
	TinyUrlService mockUrlServiceTest;
    
    @Test
    public void getShortUrlTest() {
        TinyUrl tinyUrl = new TinyUrl();
        String longUrl = "https://www.geeksforgeeks.org/create-immutable-class-java/";
        tinyUrl.setUrlPrivacyid(5);
        tinyUrl.setLongUrl(longUrl);
        tinyUrl.setCreatedTimestamp(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        when(mockUrlRepository.save(any(TinyUrl.class))).thenReturn(tinyUrl);
        when(mockBaseConversion.encode(tinyUrl.getUrlPrivacyid())).thenReturn("f");
        TinyUrlResponse tinyUrlResponse = mockUrlServiceTest.createShortUrl(longUrl);
        assertNotNull(tinyUrlResponse);
    }

    @Test
    public void getOriginalUrlTest() {
        when(mockBaseConversion.decode("h")).thenReturn((long) 7);
        String longUrl = "https://www.geeksforgeeks.org/create-immutable-class-java/";
        TinyUrl tinyUrl = new TinyUrl();
        tinyUrl.setUrlPrivacyid(7);
        tinyUrl.setLongUrl(longUrl);
        tinyUrl.setCreatedTimestamp(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        when(mockUrlRepository.findById((long) 7)).thenReturn(java.util.Optional.of(tinyUrl));
        assertEquals(longUrl, mockUrlServiceTest.getOriginalUrl("h"));
    }
	
}
