package com.main.job;

import java.util.List;

public interface JobService {

	List<Job> findAll();
	String addJob(Job job);
	Job findById(Long id);
	Boolean deleteJob(Long id);
	Boolean updateJob(Long id,Job updatedJob);
}
