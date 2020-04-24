package com.hanshin.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class number2 {
	public static void main(String[] args) {
		String jdbc_driver = "com.mysql.cj.jdbc.Driver";
		String jdbc_url = "jdbc:mysql://localhost:3306/databasetest?serverTimezone=UTC"; // databasetest는 스키마 이름

		try {
            Class.forName(jdbc_driver).newInstance();//로드
            Connection con = DriverManager.getConnection(jdbc_url, "root", "gsy022774");//연결
            PreparedStatement pst = null;
            Statement st = con.createStatement(); //데이터 조회시 사용
            ResultSet rs = null;
           
            // 2. PreparedStatement를 이용해서 5개의 열을 채우기
            
            //[쿼리 생성 단계]
            String sql = null;
           
            sql = "INSERT INTO databasetest.addressbook VALUES(?, ?, ?, ?, ?)";
            pst = con.prepareStatement(sql);
           
            //id가 1인 데이터
            pst.setInt(1, 1);
            pst.setString(2, "Su");
            pst.setString(3, "010-1111-1111");
            pst.setString(4, "sumail");
            pst.setString(5, "서울시 동작구");
            pst.executeUpdate();//쿼리 실행
           
            //id가 2인 데이터
            pst.setInt(1, 2);
            pst.setString(2, "Lee");
            pst.setString(3, "010-2222-2222");
            pst.setString(4, "leemail");
            pst.setString(5, "서울시 마포구");
            pst.executeUpdate();//쿼리 실행
           
            //id가 3인 데이터
            pst.setInt(1, 3);
            pst.setString(2, "Park");
            pst.setString(3, "010-3333-3333");
            pst.setString(4, "parkmail");
            pst.setString(5, "서울시 노원구");
            pst.executeUpdate();//쿼리 실행
           
            //id가 4인 데이터
            pst.setInt(1, 4);
            pst.setString(2, "Kyung");
            pst.setString(3, "010-4444-4444");
            pst.setString(4, "kyungmail");
            pst.setString(5, "서울시 광진구");
            pst.executeUpdate();//쿼리 실행
           
            //id가 5인 데이터
            pst.setInt(1, 5);
            pst.setString(2, "Kim");
            pst.setString(3, "010-5555-5555");
            pst.setString(4, "kimmail");
            pst.setString(5, "서울시 서초구");
            pst.executeUpdate();//쿼리 실행
           
            //[addressbook테이블의 모든 데이터를 Statement를 이용해서 조회 - 결과출력]
            sql = "SELECT * FROM databasetest.addressbook";
            rs = st.executeQuery(sql);
            while(rs.next()) {
                System.out.println("id: " + rs.getString("id")
                + " name: " + rs.getString("name")
                + " tel: " + rs.getString("tel")
                + " email: " + rs.getString("email")
                + " address: " + rs.getString("address"));
            }
           
            //연결 해제
            rs.close();
            pst.close();
            con.close();           
        } catch (Exception e) {
            e.printStackTrace();
        }

	}
}