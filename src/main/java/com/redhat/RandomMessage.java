package com.redhat;

import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/")
public class RandomMessage {
	@Inject
	RandomMessageService messageService;
	
	public static Logger logger = LoggerFactory.getLogger(RandomMessage.class);

	public String generateRandomWord() {
		Random random = new Random();
	    char[] word = new char[random.nextInt(8)+3]; // words of length 3 through 10
	    for(int i = 0; i < word.length; i++)
	    {
	        word[i] = (char)('a' + random.nextInt(26));
	    }
	    return new String(word);
	}
	
	@GET
	@Path("/json")
	@Produces({ "application/json" })
	public String getJSON() {
		String word = generateRandomWord();
		logger.info("WORD GENERATED:  " + word);
		return "{\"result\":\"" + messageService.createMessage(word) + "\"}";
	}

	@GET
	@Path("/xml")
	@Produces({ "application/xml" })
	public String getXML() {
		String word = generateRandomWord();
		logger.info("WORD GENERATED:  " + word);
		return "<xml><result>" + messageService.createMessage(word)
				+ "</result></xml>";
	}
}
