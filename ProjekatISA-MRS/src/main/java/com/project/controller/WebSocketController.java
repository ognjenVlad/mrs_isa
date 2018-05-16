package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.service.NotificationService;

@RestController
public class WebSocketController {

	@Autowired
	NotificationService producer;
	
	/*
	 * Primer slanja poruke gadjanjem REST kontrolera.
	 */
	@RequestMapping("/send/{topic}")
	public String sender(@PathVariable String topic, @RequestParam String message){
		producer.sendMessageTo(topic, message);
		return "Message successfully sent.";
	}
	
	/*
	 * Mapiranje za poruke u sklopu anotacije @MessageMapping (slicno mapiranju za REST poziv pomocu @RequestMapping anotacije)
	 * ali se ovog puta gadja registrovani endpoint i topic kojem se salje poruka.
	 */
	@MessageMapping("/nekiEndpoint/{topic}")
	/*
	 * Putanja do topica kojem se prosledjuje poruka
	 */
	@SendTo("/topic/{topic}")
	public void send(
	        @DestinationVariable("topic") String topic, String message)
	        throws Exception {
		producer.sendMessageTo(topic, message);
	}

}
