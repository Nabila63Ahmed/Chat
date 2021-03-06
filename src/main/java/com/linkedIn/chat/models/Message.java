package com.linkedIn.chat.models;

import java.io.StringReader;
import java.util.Collections;
import java.util.Date;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class Message {
private String sentFrom;
private String sentTo;
private Date sentDate;
private boolean read;
private Date readDate;
private String msg;
public Message(String sentFrom, String sentTo,Date sentDate, boolean read,Date readDate,String msg) {
	this.sentFrom = sentFrom;
	this.sentTo = sentTo;
	this.sentDate = sentDate;
	this.read = read;
	this.readDate = readDate;
	this.msg = msg;
}
public Message () {
	
}
	public String getMsg() {
	return msg;
}

public void setMsg(String msg) {
	this.msg = msg;
}

	public String getSentFrom() {
	return sentFrom;
}

public void setSentFrom(String sentFrom) {
	this.sentFrom = sentFrom;
}

public String getSentTo() {
	return sentTo;
}

public void setSentTo(String sentTo) {
	this.sentTo = sentTo;
}

public Date getSentDate() {
	return sentDate;
}

public void setSentDate(Date sentDate) {
	this.sentDate = sentDate;
}

public boolean isRead() {
	return read;
}

public void setRead(boolean read) {
	this.read = read;
}

public Date getReadDate() {
	return readDate;
}

public void setReadDate(Date readDate) {
	this.readDate = readDate;
}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public static class MessageEncoder implements Encoder.Text<Message> {
		public void init(EndpointConfig config) {
		}
		//@Override
		public String encode(Message message) throws EncodeException {
			  return Json.createObjectBuilder()
                      .add("message", message.getMsg())
                      .add("sender", message.getSentFrom())
                      .add("receiver", message.getSentTo())
                      .add("date", message.getSentDate().toString())
                      .build().toString();
		}
		//@Override
		public void destroy() {
		}
	}
	public static class MessageDecoder implements Decoder.Text<Message> {
		private JsonReaderFactory factory = Json
				.createReaderFactory(Collections.<String, Object> emptyMap());
		public void init(EndpointConfig config) {
		}
		public Message decode(String str) throws DecodeException {
			Message message = new Message();
			JsonReader reader = factory.createReader(new StringReader(str));
			JsonObject json = reader.readObject();
			message.setSentFrom(json.getString("sender"));
			message.setSentTo(json.getString("receiver"));
			message.setMsg(json.getString("message"));
			message.setSentDate(new Date(json.getString("date")));
			return message;
		}
	
		public boolean willDecode(String str) {
			return true;
		}
	
		public void destroy() {
		}
	}


}
