package com.ohadr.c3p0.leak_use_case.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.apache.commons.lang3.StringUtils;

/**
 * Contains data about campaign.
 * 
 */
@Entity
@Table(name = "CAMPAIGNS", uniqueConstraints = @UniqueConstraint(columnNames = { "NAME" }))
public class CampaignEntity implements Serializable
{
	/**
	 * Unique id of the entity.
	 */
	private static final long serialVersionUID = 6861716570336575610L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CAMPAIGN_ID", nullable = false)
	private Long campaignId;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "START_DATE", nullable = true)
	private Date startDate;

	@Column(name = "END_DATE", nullable = true)
	private Date endDate;

	public CampaignEntity()	{}

	/**
	 * Initializes a new instance of the CampaignEntity class.
	 * 
	 * @param name
	 *            the campaign name.
	 * @param startDate
	 *            start date of the campaign, may be null.
	 * @param endDate
	 *            end date of the campaign, may be null.
	 * @param active
	 *            specifies whether the campaign is active.
	 */
	public CampaignEntity(String name, Date startDate, Date endDate, Boolean active)
	{

		if (StringUtils.isEmpty(name))
		{
			throw new IllegalArgumentException("name is null or empty.");
		}

		if (active == null)
		{
			throw new IllegalArgumentException("active is null.");
		}

		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	/**
	 * Initializes a new instance of the CampaignEntity class.
	 * 
	 * @param campaignId
	 *            the campaign ID; null, if a new entity.
	 * @param name
	 *            the campaign name.
	 * @param startDate
	 *            start date of the campaign, may be null.
	 * @param endDate
	 *            end date of the campaign, may be null.
	 * @param active
	 *            specifies whether the campaign is active.
	 * @param serviceEntity
	 */
	public CampaignEntity(Long campaignId, String name, Date startDate, Date endDate, Boolean active)
	{
		this(name, startDate, endDate, active);
		this.campaignId = campaignId;
	}

	/**
	 * @return the campaignId
	 */
	public Long getCampaignId()
	{
		return campaignId;
	}

	/**
	 * @param campaignId
	 *            the campaignId to set
	 */
	public void setCampaignId(Long campaignId)
	{
		this.campaignId = campaignId;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate()
	{
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate()
	{
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("CampaignEntity [campaignId=");
		builder.append(campaignId);
		builder.append(", name=");
		builder.append(name);
		builder.append(", startDate=");
		builder.append(startDate);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append("]");
		return builder.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((campaignId == null) ? 0 : campaignId.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CampaignEntity other = (CampaignEntity) obj;
		if (campaignId == null)
		{
			if (other.campaignId != null)
				return false;
		}
		else if (!campaignId.equals(other.campaignId))
			return false;
		if (endDate == null)
		{
			if (other.endDate != null)
				return false;
		}
		else if (!endDate.equals(other.endDate))
			return false;
		if (name == null)
		{
			if (other.name != null)
				return false;
		}
		else if (!name.equals(other.name))
			return false;

		if (startDate == null)
		{
			if (other.startDate != null)
				return false;
		}
		else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}
}
