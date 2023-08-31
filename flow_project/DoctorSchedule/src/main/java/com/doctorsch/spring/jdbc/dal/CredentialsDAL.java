package com.doctorsch.spring.jdbc.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

public class CredentialsDAL {
	private String username;
	private String password;
	HashMap<String, String> h1 = new HashMap<>();

	public CredentialsDAL(String n, String p) {
		this.username = n;
		this.password = p;
	}

	public boolean verification() {

		boolean check = false;
		getdatafromDB();
		if (h1.containsKey(username)) {

			if (h1.get(username).equals(password)) {
				check = true;
			}

		}

		return check;
	}
	
	public void getdatafromDB() {
		try {
			String url = "jdbc:postgresql://localhost:5432/postgres";
			String username = "postgres";
			String password = "#Mastani14";
			Class.forName("org.postgresql.Driver");

			Connection c = DriverManager.getConnection(url, username, password);
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery("select username,pass from credentialss");

			while (rs.next()) {
				h1.put(rs.getString(1), rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
