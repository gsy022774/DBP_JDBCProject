package com.hanshin.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class number4 {
	public static void main(String[] args) {
		String jdbc_driver = "com.mysql.cj.jdbc.Driver";
		String jdbc_url = "jdbc:mysql://localhost:3306/databasetest?serverTimezone=UTC"; // databasetest는 스키마 이름

		try {
			Class.forName(jdbc_driver).newInstance();// 로드
			Connection con = DriverManager.getConnection(jdbc_url, "root", "gsy022774");// 연결
			Statement st = con.createStatement();// 서버가 인지할 수있는 sql문을 안고 들어가는 객체(전달체)
			ResultSet rs = null;
			String sql = null;

			// 4. Statement를 이용핳여 하위 2개의 행을 지우는 코드 구현

			// [행의 갯수를 세기 위한 모든 데이터 조회]
			int lastRowId = 0;// 마지막 행의 id값
			sql = "SELECT * FROM databasetest.addressbook";
			rs = st.executeQuery(sql);

			while (rs.next()) { // 현재 첫번째 데이터 셋을 가리키는 커서
				String id = rs.getString("id");
				lastRowId = Integer.parseInt(id);
			}

			// [하위 2개의 행 지우기]
			int howmanyRows = 2; // 지울 행의 갯수
			while (howmanyRows-- > 0) {
				sql = "DELETE FROM databasetest.addressbook WHERE id = " + lastRowId--;
				st.executeUpdate(sql);

			}

			// [결과 출력]
			sql = "SELECT * FROM databasetest.addressbook";
			rs = st.executeQuery(sql);

			while (rs.next()) {
				System.out.println("id: " + rs.getString("id") 
				+ " name: " + rs.getString("name") 
				+ " tel: " + rs.getString("tel")
				+ " email: " + rs.getString("email") 
				+ " address: " + rs.getString("address"));
			}

			// 연결 해제
			rs.close();
			st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}