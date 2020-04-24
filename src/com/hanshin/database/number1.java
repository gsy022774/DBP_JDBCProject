package com.hanshin.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class number1 {
	public static void main(String[] args) {
		String jdbc_driver = "com.mysql.cj.jdbc.Driver";
		String jdbc_url = "jdbc:mysql://localhost:3306/databasetest?serverTimezone=UTC"; // databasetest는 스키마 이름

		try {
			Class.forName(jdbc_driver).newInstance();// 로드
			Connection con = DriverManager.getConnection(jdbc_url, "root", "gsy022774");// 연결
			Statement st = con.createStatement();// 서버가 인지할 수있는 sql문을 안고 들어가는 객체(전달체)
			
			// 1. addressbook 이라는 이름의 테이블 생성
			
			//[addressbook 테이블 생성]
			String sql = "CREATE TABLE databasetest.addressbook (" 
					+ "id INT, " 
					+ "name VARCHAR(45), "
					+ "tel VARCHAR(45), " 
					+ "email VARCHAR(60), " 
					+ "address VARCHAR(60)" 
					+ ");";
			
			st.execute(sql); // create, alter, drop에 사용 DDL

			// 연결 해제
			st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}