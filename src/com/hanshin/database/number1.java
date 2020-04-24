package com.hanshin.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class number1 {
	public static void main(String[] args) {
		String jdbc_driver = "com.mysql.cj.jdbc.Driver";
		String jdbc_url = "jdbc:mysql://localhost:3306/databasetest?serverTimezone=UTC"; // databasetest�� ��Ű�� �̸�

		try {
			Class.forName(jdbc_driver).newInstance();// �ε�
			Connection con = DriverManager.getConnection(jdbc_url, "root", "gsy022774");// ����
			Statement st = con.createStatement();// ������ ������ ���ִ� sql���� �Ȱ� ���� ��ü(����ü)
			
			// 1. addressbook �̶�� �̸��� ���̺� ����
			
			//[addressbook ���̺� ����]
			String sql = "CREATE TABLE databasetest.addressbook (" 
					+ "id INT, " 
					+ "name VARCHAR(45), "
					+ "tel VARCHAR(45), " 
					+ "email VARCHAR(60), " 
					+ "address VARCHAR(60)" 
					+ ");";
			
			st.execute(sql); // create, alter, drop�� ��� DDL

			// ���� ����
			st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}