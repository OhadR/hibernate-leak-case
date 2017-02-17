package com.ohadr.c3p0.leak_use_case.jobs;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import com.ohadr.c3p0.leak_use_case.AffiliateManager;
import com.ohadr.c3p0.leak_use_case.entities.AffiliateEntity;
import com.ohadr.c3p0.leak_use_case.entities.CampaignEntity;

public class MyItemReader implements ItemReader<AffiliateEntity>, StepExecutionListener
{
	private static Logger log = Logger.getLogger(MyItemReader.class);
	private static final String POKER_STRATEGY_AFF_NAME = "pokerStrategy";

	@Autowired
	private AffiliateManager affiliateManager;

	@Override
	public AffiliateEntity read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException
	{
		log.info("executing reader...");
		
//		CampaignEntity ce = null;
//		try
//		{
//			ce = affiliateManager.getAffiliateActiveSignupCampaign(POKER_STRATEGY_AFF_NAME);
//		}
//		catch (Exception e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		log.info("CampaignEntity: " + ce);
		return null;
	}
	
	@Override
	public void beforeStep(StepExecution stepExecution)
	{
		log.info("executing before step...");
		
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
		
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
