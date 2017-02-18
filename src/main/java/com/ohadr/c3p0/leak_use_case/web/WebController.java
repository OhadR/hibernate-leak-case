package com.ohadr.c3p0.leak_use_case.web;

import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.ohadr.c3p0_test.LeakTestCaseRunner;
import com.ohadr.c3p0_test.MyTaskExecutor;
import com.ohadr.common.types.c3p0.ConnectionPoolStatus;
import com.ohadr.common.types.c3p0.ConnectionPoolStatusCollection;
import com.ohadr.common.utils.JsonUtils;
import com.ohadr.common.utils.c3p0.C3P0Utils;


@Controller
public class WebController 
{
	private static Logger log = Logger.getLogger(WebController.class);

    @Autowired
    private LeakTestCaseRunner leakTestCaseRunner;

    @Autowired
    private MyTaskExecutor taskExecutor;
    
	@Autowired
	private DataSource  dataSource;

	
	
    @RequestMapping("/ping")	
	protected void ping(
			HttpServletResponse response) throws Exception{
		log.info( "got to ping" );
		response.getWriter().println("ping response: pong, " + System.currentTimeMillis());
	}
    
    
    @RequestMapping(value = "/connPoolStatus", method = RequestMethod.GET)
    protected void getDataSourceStatus(
    		HttpServletResponse response) throws Exception
    {
		ComboPooledDataSource comboPooledDataSource = (ComboPooledDataSource)dataSource;
    	ConnectionPoolStatus status = C3P0Utils.getConnectionPoolStatus(comboPooledDataSource);
    	ConnectionPoolStatusCollection coll = new ConnectionPoolStatusCollection();
    	coll.collection = new ArrayList<ConnectionPoolStatus>();
    	coll.collection.add(status);
    	String statusJson = JsonUtils.convertToJson(coll);
    	response.getWriter().println( statusJson );    	
    }

    
    @RequestMapping(value = "/leakTest", method = RequestMethod.GET)
    protected void runLeakTestCase(
            @RequestParam String action,
    		HttpServletResponse response) throws Exception
    {
    	if(action.equalsIgnoreCase("START"))
    		leakTestCaseRunner.runLeakTest();
    	
    	if(action.equalsIgnoreCase("STOP"))
        	leakTestCaseRunner.stopLeakTest();

    	response.getWriter().println( "LeakTest: " + action );    	
    }
    
    
    @RequestMapping(value = "/executeTask", method = RequestMethod.GET)
	public void executeTask(@RequestParam("taskName") String taskName,
    		HttpServletResponse response)
	{
		if (StringUtils.isEmpty(taskName))
		{
			String message = "Cannot execute task! 'taskName' parameter in the POST request is null or empty.";
			log.error(message);
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		log.info("About to execute task synchronously from API. Task name: " + taskName);
		
		try
		{
			taskExecutor.runTask(taskName);
			response.setStatus(HttpServletResponse.SC_OK);
		}
		catch (Exception e)
		{
			String message = "Cannot execute task! Error: " + e.getMessage();
			log.error(message);
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}
}