package com.ohadr.c3p0.leak_use_case.jobs;

import org.apache.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import com.ohadr.c3p0.leak_use_case.AffiliateManager;
import com.ohadr.c3p0.leak_use_case.entities.CampaignEntity;

public class MyTasklet implements Tasklet
{
	private static Logger log = Logger.getLogger(MyTasklet.class);

	private static final String POKER_STRATEGY_AFF_NAME = "pokerStrategy";

	@Autowired
	private AffiliateManager affiliateManager;

	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception
	{
		log.info("executing simple tasklet...");
		
		CampaignEntity ce = null;
		try
		{
			ce = affiliateManager.getAffiliateActiveSignupCampaign(POKER_STRATEGY_AFF_NAME);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("CampaignEntity: " + ce);

		return null;
	}

}
