package com.douzone.mysite.action.board;

import com.douzone.mysite.action.main.MainAction;
import com.douzone.web.action.Action;
import com.douzone.web.action.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		switch (actionName) {
		case "write":
			return new WriteAction();
		case "writeform":
			return new WriteFormAction();
		case "delete":
			return new DeleteAction();
		case "view":
			return new ViewAction();
		case "modify":
			return new ModifyAction();
		case "modifyform":
			return new ModifyFormAction();
		case "reply":
			return new ReplyAction();
		case "replyform":
			return new ReplyFormAction();
		case "list":
			return new ListAction();
		default:
			return new MainAction();
		}
	}
}
