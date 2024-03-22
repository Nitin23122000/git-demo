package com.main.job;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl  implements JobService{
	
	@Autowired
	JobRepository jobRepository;

	//private List<Job> jobs = new ArrayList<>();
	//this is to keep tracking of objects
	
	@Override
	public List<Job> findAll() {
		return jobRepository.findAll();
	}

	@Override
	public String addJob(Job job) {
		
		//job.setId(nextId++);
		try {
			 jobRepository.save(job);
			 return "Job Added Succesfully";
		} catch (Exception e) {

			return "Job Not Added Succesfully";
		}
				
	}

	@Override
	public Job findById(Long id) {	
		return jobRepository.findById(id).orElse(null);
	}

	@Override
	public Boolean deleteJob(Long id) {

		try {
			jobRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	

	public Boolean updateJob(Long id, Job updatedJob) {

		Optional<Job> op = jobRepository.findById(id);
		
			if(op.isPresent()) {
				Job jobObj = op.get();
				jobObj.setTitle(updatedJob.getTitle());
				jobObj.setDescription(updatedJob.getDescription());
				jobObj.setMinSalary(updatedJob.getMinSalary());
				jobObj.setMaxSalary(updatedJob.getMaxSalary());
				jobRepository.save(jobObj);
				return  true;
			}
		
		return false;
	}
	

}
