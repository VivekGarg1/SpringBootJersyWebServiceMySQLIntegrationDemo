package com.home.app.service;

import java.util.List;

import com.home.app.model.Topic;

public interface TopicService {
	
	 public abstract List<Topic> getAllTopics();
	 public abstract Topic getTopicById(int topicId);
	 public abstract boolean addTopic(Topic topic);
	 public abstract void updateTopic(Topic topic);
	 public abstract void deleteTopic(int topicId);
}
