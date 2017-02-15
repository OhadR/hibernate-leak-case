package com.ohadr.c3p0.leak_use_case.jobs;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class MyTasklet implements Tasklet
{

	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

}
