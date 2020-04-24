package com.hanshin.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class number4 {
	public static void main(String[] args) {
		String jdbc_driver = "com.mysql.cj.jdbc.Driver";
		String jdbc_url = "jdbc:mysql://localhost:3306/databasetest?serverTimezone=UTC"; // databasetest�� ��Ű�� �̸�

		try {
			Class.forName(jdbc_driver).newInstance();// �ε�
			Connection con = DriverManager.getConnection(jdbc_url, "root", "gsy022774");// ����
			Statement st = con.createStatement();// ������ ������ ���ִ� sql���� �Ȱ� ���� ��ü(����ü)
			ResultSet rs = null;
			String sql = null;

			// 4. Statement�� �̿��K�� ���� 2���� ���� ����� �ڵ� ����

			// [���� ������ ���� ���� ��� ������ ��ȸ]
			int lastRowId = 0;// ������ ���� id��
			sql = "SELECT * FROM databasetest.addressbook";
			rs = st.executeQuery(sql);

			while (rs.next()) { // ���� ù��° ������ ���� ����Ű�� Ŀ��
				String id = rs.getString("id");
				lastRowId = Integer.parseInt(id);
			}

			// [���� 2���� �� �����]
			int howmanyRows = 2; // ���� ���� ����
			while (howmanyRows-- > 0) {
				sql = "DELETE FROM databasetest.addressbook WHERE id = " + lastRowId--;
				st.executeUpdate(sql);

			}

			// [��� ���]
			sql = "SELECT * FROM databasetest.addressbook";
			rs = st.executeQuery(sql);

			while (rs.next()) {
				System.out.println("id: " + rs.getString("id") 
				+ " name: " + rs.getString("name") 
				+ " tel: " + rs.getString("tel")
				+ " email: " + rs.getString("email") 
				+ " address: " + rs.getString("address"));
			}

			// ���� ����
			rs.close();
			st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}