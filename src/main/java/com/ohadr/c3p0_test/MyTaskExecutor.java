package com.ohadr.c3p0_test;

import org.apache.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class MyTaskExecutor implements ApplicationContextAware
{
	private static Logger log = Logger.getLogger(MyTaskExecutor.class);

	private ApplicationContext applicationContext;

    @Autowired
	private JobLauncher jobLauncher;


    @Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
	{
		this.applicationContext = applicationContext;
	}
    
    public void runTask(String taskName) throws JobExecutionAlreadyRunningException, 
    											JobRestartException, 
    											JobInstanceAlreadyCompleteException, 
    											JobParametersInvalidException
    {
		// create the job according to job name
		Job job = (Job) applicationContext.getBean(taskName);

		JobParameters jobParameters =
				  new JobParametersBuilder()
				  .addLong("time",System.currentTimeMillis()).toJobParameters();

		JobExecution execution = jobLauncher.run(job, jobParameters);
		log.info("Exit Status : " + execution.getStatus());
    }

}
