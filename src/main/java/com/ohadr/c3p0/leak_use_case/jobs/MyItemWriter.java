package com.ohadr.c3p0.leak_use_case.jobs;

import java.util.List;
import org.springframework.batch.item.ItemWriter;
import com.ohadr.c3p0.leak_use_case.entities.AffiliateEntity;

public class MyItemWriter implements ItemWriter<AffiliateEntity>
{

	@Override
	public void write(List<? extends AffiliateEntity> affiliates) throws Exception
	{
		// TODO Auto-generated method stub
		
	}
}
