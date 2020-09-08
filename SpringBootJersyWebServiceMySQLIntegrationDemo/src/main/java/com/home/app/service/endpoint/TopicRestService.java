package com.home.app.service.endpoint;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.home.app.model.Topic;
import com.home.app.service.TopicService;

@Component
@Path("/topics")
public class TopicRestService {
	
	private static final Logger logger=LoggerFactory.getLogger(TopicRestService.class);
	
	@Autowired
	private TopicService topicService;
	
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response createTopic(Topic topic) {
        boolean isAdded = topicService.addTopic(topic);
        if (!isAdded) {
        	logger.info("Topic already exits.");
	        return Response.status(Status.CONFLICT).build();
        }
        return Response.created(URI.create("/topics/topic/"+ topic.getTopicId())).build();
	}	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getAllTopics() {
		List<Topic> list = topicService.getAllTopics(); 
		return Response.ok(list).build();
	}
	
	@GET
	@Path("/topicId/{topicId}")
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getTopicById(@PathParam("topicId") Integer topicId) {
		Topic topic = topicService.getTopicById(topicId);
		return Response.ok(topic).build();
	}	
	
	@PUT
	@Path("/topic/update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response updateTopic(Topic topic) {
		topicService.updateTopic(topic);
		return Response.ok(topic).build();
	}
	
	@DELETE
	@Path("/topicId/{topicId}")
	@Consumes(MediaType.APPLICATION_JSON)	
	@CrossOrigin
	public Response deleteTopic(@PathParam("topicId") Integer topicId) {
		topicService.deleteTopic(topicId);
		return Response.noContent().build();
	}
}
