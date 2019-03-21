package com.bugrasessevmez.issuemanagement;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;

@SpringBootApplication
public class IssueManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(IssueManagementApplication.class, args);
	}
	
	@Bean
	public ModelMapper getModelMapper() {
		ModelMapper mapper =new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return mapper;
	}
	
	@Bean
	public Jackson2RepositoryPopulatorFactoryBean reFactoryBean() {
		Jackson2RepositoryPopulatorFactoryBean bean = new Jackson2RepositoryPopulatorFactoryBean();
		bean.setResources(new Resource[] {new ClassPathResource("projects.json")});
		return bean;
	}
}
