package com.hanshin.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class number3 {
	public static void main(String[] args) {
		String jdbc_driver = "com.mysql.cj.jdbc.Driver";
		String jdbc_url = "jdbc:mysql://localhost:3306/databasetest?serverTimezone=UTC"; // databasetest�� ��Ű�� �̸�

		try {
			Class.forName(jdbc_driver).newInstance();// �ε�
			Connection con = DriverManager.getConnection(jdbc_url, "root", "gsy022774");// ����
			PreparedStatement pst = null; //������ UPDATE�� PreparedStatement ���
			Statement st = con.createStatement(); //������ ��ȸ�ÿ� Statement ���
			ResultSet rs = null;
			String sql = null;

			// 3. email �������� @naver.com���� update ����
			
			//[Statement�� �̿��ؼ� ��� �����Ϳ� ���� ���� email �� ��ȸ]
			int rownum = 5; // ���� ����
			String[] getemail = new String[rownum]; // �̸��� ���� �޾ƿ��� �迭
			sql = "SELECT * FROM databasetest.addressbook";
			rs = st.executeQuery(sql);

			// ���� ���ϰ��� ������ �޾ƿ���
			int tmp = 0;
			while (rs.next()) {
				getemail[tmp] = rs.getString("email");
				tmp++;
			}

			//[PreparedStatement�� �̿��Ͽ� update ����]
			int num = 0;
			while (num < 5) {
				sql = "UPDATE databasetest.addressbook SET email=? WHERE id = ?"; // ������ ����
				pst = con.prepareStatement(sql); // ������ ����
				pst.setString(1, getemail[num] + "@naver.com");
				pst.setInt(2, num + 1);
				pst.executeUpdate(); // ������ ����

				num++;
			}

			//[addressbook���̺��� ��� �����͸� Statement�� �̿��ؼ� ��ȸ - ������]
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
			pst.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}