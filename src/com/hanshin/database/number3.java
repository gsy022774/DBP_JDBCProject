package com.hanshin.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class number3 {
	public static void main(String[] args) {
		String jdbc_driver = "com.mysql.cj.jdbc.Driver";
		String jdbc_url = "jdbc:mysql://localhost:3306/databasetest?serverTimezone=UTC"; // databasetest는 스키마 이름

		try {
			Class.forName(jdbc_driver).newInstance();// 로드
			Connection con = DriverManager.getConnection(jdbc_url, "root", "gsy022774");// 연결
			PreparedStatement pst = null; //데이터 UPDATE시 PreparedStatement 사용
			Statement st = con.createStatement(); //데이터 조회시에 Statement 사용
			ResultSet rs = null;
			String sql = null;

			// 3. email 도메인을 @naver.com으로 update 수행
			
			//[Statement를 이용해서 모든 데이터에 대한 기존 email 값 조회]
			int rownum = 5; // 행의 갯수
			String[] getemail = new String[rownum]; // 이메일 값을 받아오는 배열
			sql = "SELECT * FROM databasetest.addressbook";
			rs = st.executeQuery(sql);

			// 기존 메일값을 변수에 받아오기
			int tmp = 0;
			while (rs.next()) {
				getemail[tmp] = rs.getString("email");
				tmp++;
			}

			//[PreparedStatement를 이용하여 update 수행]
			int num = 0;
			while (num < 5) {
				sql = "UPDATE databasetest.addressbook SET email=? WHERE id = ?"; // 수행할 쿼리
				pst = con.prepareStatement(sql); // 쿼리문 저장
				pst.setString(1, getemail[num] + "@naver.com");
				pst.setInt(2, num + 1);
				pst.executeUpdate(); // 쿼리문 실행

				num++;
			}

			//[addressbook테이블의 모든 데이터를 Statement를 이용해서 조회 - 결과출력]
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
			pst.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}