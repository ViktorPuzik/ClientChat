package academy.prog;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Message {
	private Date date = new Date();
	private String from;
	private String to;
	private String text;

	private int counter;

	public Message(String from, String text, String to) {
		this.from = from;
		this.text = text;
		this.to = to;
	}

	public String toJSON() {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson.toJson(this);
	}

	public static Message fromJSON(String s) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson.fromJson(s, Message.class);
	}

	public int send(String url) throws IOException {
		URL obj = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

		conn.setRequestMethod("POST");
		conn.setDoOutput(true);

		try (OutputStream os = conn.getOutputStream()) {
			String json = toJSON();
			os.write(json.getBytes(StandardCharsets.UTF_8));
			return conn.getResponseCode(); // 200?
		}
	}


	@Override
	public String toString() {
		if (to.equals("-1")){
			return new StringBuilder().append("[").append(date)
					.append(", From: ").append(from).append(", To: All ").append(", Counter: ").append(counter)
					.append("] ").append(text)
					.toString();

		} else {
			return new StringBuilder().append("[").append(date)
					.append(", From: ").append(from).append(", To: ").append(to).append(", Counter: ").append(counter)
					.append("] ").append(text)
					.toString();

		}
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public int getCounter() {return counter;}

	public String getTo() {
		return to;
	}

	public void setCounter(int counter) {this.counter = counter;}

	public void setTo(String to) {
		this.to = to;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
