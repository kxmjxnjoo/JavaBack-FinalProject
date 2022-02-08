package com.ezen;

import com.ezen.action.Action;
import com.ezen.action.mainAction;

public class ActionFactory {
	private ActionFactory() {}
	private static ActionFactory itc = new ActionFactory();
	public static ActionFactory getInstance() { return itc; }
	
	public Action getAction(String command) {
		Action ac = null;
		
		if(command.equals("main"))			ac = new mainAction();
		
				
		return ac;
	}
}
