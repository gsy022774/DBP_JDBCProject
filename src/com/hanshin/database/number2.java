package com.hanshin.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class number2 {
	public static void main(String[] args) {
		String jdbc_driver = "com.mysql.cj.jdbc.Driver";
		String jdbc_url = "jdbc:mysql://localhost:3306/databasetest?serverTimezone=UTC"; // databasetest�� ��Ű�� �̸�

		try {
            Class.forName(jdbc_driver).newInstance();//�ε�
            Connection con = DriverManager.getConnection(jdbc_url, "root", "gsy022774");//����
            PreparedStatement pst = null;
            Statement st = con.createStatement(); //������ ��ȸ�� ���
            ResultSet rs = null;
           
            // 2. PreparedStatement�� �̿��ؼ� 5���� ���� ä���
            
            //[���� ���� �ܰ�]
            String sql = null;
           
            sql = "INSERT INTO databasetest.addressbook VALUES(?, ?, ?, ?, ?)";
            pst = con.prepareStatement(sql);
           
            //id�� 1�� ������
            pst.setInt(1, 1);
            pst.setString(2, "Su");
            pst.setString(3, "010-1111-1111");
            pst.setString(4, "sumail");
            pst.setString(5, "����� ���۱�");
            pst.executeUpdate();//���� ����
           
            //id�� 2�� ������
            pst.setInt(1, 2);
            pst.setString(2, "Lee");
            pst.setString(3, "010-2222-2222");
            pst.setString(4, "leemail");
            pst.setString(5, "����� ������");
            pst.executeUpdate();//���� ����
           
            //id�� 3�� ������
            pst.setInt(1, 3);
            pst.setString(2, "Park");
            pst.setString(3, "010-3333-3333");
            pst.setString(4, "parkmail");
            pst.setString(5, "����� �����");
            pst.executeUpdate();//���� ����
           
            //id�� 4�� ������
            pst.setInt(1, 4);
            pst.setString(2, "Kyung");
            pst.setString(3, "010-4444-4444");
            pst.setString(4, "kyungmail");
            pst.setString(5, "����� ������");
            pst.executeUpdate();//���� ����
           
            //id�� 5�� ������
            pst.setInt(1, 5);
            pst.setString(2, "Kim");
            pst.setString(3, "010-5555-5555");
            pst.setString(4, "kimmail");
            pst.setString(5, "����� ���ʱ�");
            pst.executeUpdate();//���� ����
           
            //[addressbook���̺��� ��� �����͸� Statement�� �̿��ؼ� ��ȸ - ������]
            sql = "SELECT * FROM databasetest.addressbook";
            rs = st.executeQuery(sql);
            while(rs.next()) {
                System.out.println("id: " + rs.getString("id")
                + " name: " + rs.getString("name")
                + " tel: " + rs.getString("tel")
                + " email: " + rs.getString("email")
                + " address: " + rs.getString("address"));
            }
           
            //���� ����
            rs.close();
            pst.close();
            con.close();           
        } catch (Exception e) {
            e.printStackTrace();
        }

	}
}