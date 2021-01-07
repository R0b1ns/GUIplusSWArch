package rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/rest")
public class RestApplication extends Application {
	private static final StatusMessage SUCCESS_MSG = new StatusMessage("success");
	private static final StatusMessage ERROR_MSG = new StatusMessage("error");
	
	public static StatusMessage getReturnMessage(boolean status) {
		if(status)
			return SUCCESS_MSG;
		else
			return ERROR_MSG;
	}
}

