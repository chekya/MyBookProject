package com.itgroup.dao;

import java.sql.*;

public class SuperDao {
    public SuperDao() {
// 드라이버 관련 OracleDriver 클래스는 ojdbc6.jar 파일 안에 있는 자바 클래스입니다.
        String driver = "oracle.jdbc.driver.OracleDriver"; //이 문자열을 하단 forName에 넣으면 ojdbc6.jar 파일에 있는자바클래스를 동적으로 만들어준다.
        try {
            Class.forName(driver); //동적 객체 생성하는 문법입니다.(JDBC 드라이버를 메모리에 올리는 작업)

        } catch (ClassNotFoundException e) {
            // throw new RuntimeException(e);  //e.fillInStackTrace(); 대신 이렇게 자동생성되는 문구 그대로 사용해도 됨.
            System.out.println("해당 드라이버가 존재하지 않습니다.");
            //e.fillInStackTrace();
            e.printStackTrace();
        }
    }

    public Connection getConnection() {  //DB 연결 정보 설정
        Connection conn = null;  //접속 객체
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String id = "doldori";
        String password = "hello1234";  //해당계정의 비번

        try {
            conn = DriverManager.getConnection(url, id, password);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
    public void close(Connection conn){
        try {
            if (conn != null) conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void close(PreparedStatement pstmt){
        try {
            if (pstmt != null) pstmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void close(ResultSet rs){
        try {
            if (rs != null) rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void close(Connection conn, PreparedStatement pstmt, ResultSet rs){
        close(rs);
        close(pstmt);
        close(conn);
    }
    public void close(Connection conn, PreparedStatement pstmt){
        close(pstmt);
        close(conn);
    }

}
