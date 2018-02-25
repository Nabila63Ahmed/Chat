package com.linkedIn.chat;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonObject;

import com.arangodb.ArangoCollection;
import com.arangodb.ArangoDB;
import com.linkedIn.chat.config.DatabaseConnection;
import com.linkedIn.chat.models.Message;

import redis.clients.jedis.Jedis;

public class SendMessage extends Command{
	int numberOfMessages=0;
	public SendMessage(HashMap<String, String> hMap) {
		super(hMap);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public String execute()  {
		// TODO Auto-generated method stub
	
		try {
			//insert
			//change keys to match collection
			
			ArangoDB aDB= DatabaseConnection.getInstance().getArangodb();
			
			ArangoCollection m=aDB.db().collection("messages");
			ArrayList<Message> allMessages=new ArrayList<Message>();
			//Jedis jedis= DatabaseConnection.getInstance().getRedis();
			while(numberOfMessages<20) {
				Message msg=new Message(hMap.get("from"), hMap.get("to"), new Date(hMap.get("date")), false, null, hMap.get("message"));
				allMessages.add(msg);
				numberOfMessages++;
				
			}
			if(numberOfMessages==19) {
				//int i=0;
			
				// Map<String, String> properties = jedis.hgetAll("*");
				for (Message message : allMessages) {
					JsonObject value=Json.createObjectBuilder()
				            .add("message", message.getMsg())
				            .add("sender", message.getSentFrom())
				            .add("receiver",message.getSentTo())
				            .add("sent_date", message.getSentDate().toString())
				            .add("read", message.isRead())
				            .add("read_date", message.getReadDate().toString())
				            .build();
							m.insertDocument(value);
				}
					
				
			}
			
			//TODO
			
			//send post add url
			 URL url = new URL("");
			 HttpURLConnection connection = null;
			 connection = (HttpURLConnection) url.openConnection();
			    connection.setRequestMethod("POST");
			    connection.setRequestProperty("Content-Type", 
			        "application/x-www-form-urlencoded");
			
//			    connection.setRequestProperty("Content-Length", 
//			            Integer.toString(urlParameters.getBytes().length));
//			        connection.setRequestProperty("Content-Language", "en-US");  
//
//			        connection.setUseCaches(false);
//			        connection.setDoOutput(true);
//
//			        //Send request
//			        DataOutputStream wr = new DataOutputStream (
//			            connection.getOutputStream());
//			        wr.writeBytes(urlParameters);
//			        wr.close();
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//add message to database
		
		
		return null;
	}

}
