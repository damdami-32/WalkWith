package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestJDBCUtil {
    public static void main(String[] args) {
        JDBCUtil jdbcUtil = new JDBCUtil();  // JDBCUtil 인스턴스 생성
        ConnectionManager connMan = new ConnectionManager();  // ConnectionManager 인스턴스 생성

        try {
            // 데이터베이스 연결
            Connection conn = connMan.getConnection();
            if (conn != null) {
                System.out.println("연결 성공!");
            } else {
                System.out.println("연결 실패...");
            }

            // SQL 쿼리 실행
            String sql = "SELECT * FROM Customer";  // your_table을 실제 테이블 이름으로 변경해야 합니다.
            jdbcUtil.setSqlAndParameters(sql, null);
            ResultSet rs = jdbcUtil.executeQuery();  // SELECT 쿼리의 경우 executeQuery()를 사용합니다.

            // 결과 확인
            while (rs.next()) {
                // 결과를 출력합니다. 여기서는 첫 번째 열의 값을 출력하였습니다.
                System.out.println(rs.getString(1));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // 연결 종료
            connMan.close();
        }
    }
}

