package com.ohadr.c3p0_test.web;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.ohadr.c3p0_test.LeakTestCaseRunner;


@Controller
public class WebController 
{
	private static Logger log = Logger.getLogger(WebController.class);

    @Autowired
    private LeakTestCaseRunner leakTestCaseRunner;

    
    @RequestMapping("/ping")	
	protected void ping(
			HttpServletResponse response) throws Exception{
		log.info( "got to ping" );
		response.getWriter().println("ping response: pong, " + System.currentTimeMillis());
	}
    

    @RequestMapping(value = "/leakTest", method = RequestMethod.GET)
    protected void runLeakTestCase(
            @RequestParam String action,
    		HttpServletResponse response) throws Exception
    {
    	if(action.equalsIgnoreCase("START"))
    		leakTestCaseRunner.runLeakTest(  );
    	
    	if(action.equalsIgnoreCase("STOP"))
        	leakTestCaseRunner.stopLeakTest();

    	response.getWriter().println( "LeakTest: " + action );    	
    }
}