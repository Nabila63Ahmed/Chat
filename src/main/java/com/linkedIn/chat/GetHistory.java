package com.linkedIn.chat;

import java.io.IOException;
import java.util.HashMap;

import com.linkedIn.chat.chatinterface.ArangoChatHandler;

public class GetHistory extends Command{

	public GetHistory(HashMap<String, String> hMap) {
		super(hMap);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public String execute() {
		// TODO Auto-generated method stub
		 String userId1 = hMap.get("userId1");
		 String userId2 = hMap.get("userId2");
		 String limit = hMap.get("limit");
		 String offset = hMap.get("offset");
		
		 try {
			ArangoChatHandler ac=new ArangoChatHandler();
			 if((limit.equals(null)&&offset.equals(null)||(limit.isEmpty()&&offset.isEmpty()))){
				 ac.getChatHistory(userId1, userId2);
			 }
			 else {
				 ac.getChatHistory(userId1, userId2,Integer.parseInt(limit),Integer.parseInt(offset));
			 }
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

}
