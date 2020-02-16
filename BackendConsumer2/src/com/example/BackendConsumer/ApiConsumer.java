package com.example.BackendConsumer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

public class ApiConsumer {
	//private Client client;
	private static String SERVICE_URL = "https://tom-backend2020.herokuapp.com/";
	//public ApiConsumer() {client = ClientBuilder.newClient();}
	HttpURLConnection con;
	/*public void postUser(String Name, String Proffesion) {
		Form form = new Form();
		form.param("Name", Name);
		form.param("Proffesion", Proffesion);
	    	Client client = ClientBuilder.newClient();
	    	WebTarget target = client.target(SERVICE_URL + "/addUser");
	    	Builder request = target.request(MediaType.APPLICATION_FORM_URLENCODED);
	    	Response response = request.post(Entity.json(""));
	    	Builder accept = request.accept(MediaType.APPLICATION_JSON);
	    	Invocation buildPost = accept.buildPost(Entity.form(form));
	    	Future<String> body = buildPost.submit(String.class);
	    	try {
	    		System.out.println(body.get());
	    } catch (Exception e) {e.printStackTrace();}
	
	}*/
	public String postUser(String Name, String Proffesion) {
		try {
			URL connectTo = new URL(SERVICE_URL + "/addUser");
			con = (HttpURLConnection) connectTo.openConnection();
			System.out.println("Connected");
			con.setDoOutput(true);
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			con.setRequestProperty("Accept", "application/json");
			String jsonString = "";
			try {
				JSONObject jsonO = new JSONObject();
				jsonO.put("name", Name);
				jsonO.put("proffesion", Proffesion);
				jsonString = jsonO.toString();
				System.out.println(jsonString + " --SENT TO DATABASE");
				
				try(OutputStream os = con.getOutputStream()) {
					OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
					osw.write(jsonString);
					osw.flush();
					osw.close();
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			try(BufferedReader br = new BufferedReader(
					  new InputStreamReader(con.getInputStream(), "utf-8"))) {
					    StringBuilder response = new StringBuilder();
					    String responseLine = null;
					    while ((responseLine = br.readLine()) != null) {
					        response.append(responseLine.trim());
					    }
					    return response.toString();
					}
				
			} catch (IOException e) {
				e.printStackTrace();
				return "IOEXCEPTION REACHED";
			}
	}
	public String getUserList() {
		String output = null;
		try {
			URL fullUrl = new URL(SERVICE_URL + "/userList");
			BufferedReader bReader = new BufferedReader(new InputStreamReader(fullUrl.openStream(), "UTF-8"));
			StringBuilder sb = new StringBuilder();
			String sTemp = "";
			while ((sTemp = bReader.readLine()) != null) {
				sb.append(sTemp);
			}
			output = sb.toString();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return output;
	}
	public String getClearTable() {
		String output = null;
		try {
			URL fullUrl = new URL(SERVICE_URL + "/clearUsers");
			BufferedReader bReader = new BufferedReader(new InputStreamReader(fullUrl.openStream(), "UTF-8"));
			StringBuilder sb = new StringBuilder();
			String sTemp = "";
			while ((sTemp = bReader.readLine()) != null) {
				sb.append(sTemp);
			}
			output = sb.toString();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return output;
	}
}
