package com.ohadr.c3p0.leak_use_case;

import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.ohadr.c3p0.leak_use_case.entities.AffiliateCampaignEntity;
import com.ohadr.c3p0.leak_use_case.entities.AffiliateEntity;
import com.ohadr.c3p0.leak_use_case.entities.CampaignEntity;


@Component
public class AffiliateManager 
{
	private static Logger log = Logger.getLogger(AffiliateManager.class);
	/**
	 * UID for AffiliateManager
	 */
	public final static String UID = "affiliateManager";

	@Autowired
	private AffiliateDao dao;
	
	public AffiliateManager()
	{}



	private AffiliateEntity getAffiliate(final String affiliateName)
	{
		if (StringUtils.isEmpty(affiliateName))
		{
			throw new IllegalArgumentException("affiliateName is null or empty.");
		}
		AffiliateEntity result = dao.getAffiliate(affiliateName);
		return result;
	}


	public CampaignEntity getAffiliateActiveSignupCampaign(final String affiliateName) 
	{
        AffiliateEntity affiliate = getAffiliate(affiliateName);
		
		if (affiliate == null)
		{
			String message = String.format("No affiliate with name %s was found.", affiliateName);
			log.error(message);
			throw new RuntimeException(message);
		}

		final Date date = new Date();

		for(AffiliateCampaignEntity ace : affiliate.getAffiliateCampaigns())		//leak is here
		{
			CampaignEntity ce = ace.getCampaign();
			if( isActiveCampaign4Signup(date, ce) )
			{
				log.info("CampaignEntity: " + ce);
				return ce;
			}
		}

		return null;
	}
	
	private boolean isActiveCampaign4Signup(final Date date, final CampaignEntity campaign)
	{
		boolean active = campaign != null;

		if (active)
		{
			Date startDate = campaign.getStartDate();
			if (startDate != null)
			{
				active = date.after(startDate);
			}
		}

		if (active)
		{
			Date endDate = campaign.getEndDate();
			if (endDate != null)
			{
				active = date.before(endDate);
			}
		}
		return active;
	}

}
