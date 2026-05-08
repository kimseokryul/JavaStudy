package com.test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MemberInfo {

    public static void main(String[] args) {
        String uri = "jdbc:mariadb://localhost:3306/webdev";
        String userid = "webmaster";
        String userpw = "1234";
        String query = "select userid, username, age from tbl_test";

        Connection con; // DB와의 연결을 관리
        Statement stmt; // DB에게 명령 전달
        ResultSet rs; // DB로부터 받아온 데이터 저장된. Set Collections의 한 유형

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            con = DriverManager.getConnection(uri, userid, userpw);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            List<Member> list = new ArrayList<>();

            while (rs.next()) {
                list.add(new Member(rs.getString("userid"),
                        rs.getString("username"),
                        rs.getInt("age")));
            }

            for (Member member : list) {
                System.out.println("아이디 : " + member.getUserid() + ", 이름 : " +
                        member.getUsername() + ", 나이 : " +
                        member.getAge());
            }

            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
            if (con != null)
                con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
