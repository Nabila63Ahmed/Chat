package commands;

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

import database.handlers.impl.ArangoChatHandler;
import models.Command;
import models.Message;
import redis.clients.jedis.Jedis;

public class InsertMessageCommand extends Command {
	int numberOfMessages = 0;
	ArrayList<Message> allMessages = new ArrayList<Message>();

	public InsertMessageCommand(HashMap<String, String> hMap) {
		super(hMap);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public String execute() {
		// TODO Auto-generated method stub
		try {
			ArangoChatHandler ac = new ArangoChatHandler();

			if (numberOfMessages < 20) {
				Message msg = new Message(hMap.get("from"), hMap.get("to"), new Date(hMap.get("date")), false,
						hMap.get("message"));
				allMessages.add(msg);
				numberOfMessages++;
			}

			if (allMessages.size() == 20) {
				for (Message message : allMessages) {

					ac.insertMessage(message);
				}

				allMessages = new ArrayList<Message>();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
