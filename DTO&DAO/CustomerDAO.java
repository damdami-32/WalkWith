package lab3Sol;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class CustomerDAO {

    private JDBCUtil jdbcUtil = null;   // JDBCUtil 필드 선언
    
    public CustomerDAO() {               // 생성자 
        jdbcUtil = new JDBCUtil();      // JDBCUtil 객체 생성
    }
    //userId에 있는 petList 전부 받아오는 메소드
    //customerId->likelist해서 어떤 가게를 좋아요했는지 받아오기
    
    // 사용자 ID로 예약 수정 --> DB 전체 수정 + 비고 라인 추가(DB로) --> customer
    public int updateReservationByUser(String userId, Date newDate) {
        StringBuilder query = new StringBuilder();
        query.append("UPDATE Reservation SET resDaTi = ? WHERE userId = ?");

        Object[] parameters = new Object[] {newDate, userId};

        jdbcUtil.setSqlAndParameters(query.toString(), parameters);

        try {
            int result = jdbcUtil.executeUpdate();
            jdbcUtil.commit();
            return result;
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }

        return 0;
    }


    // 사용자 ID로 예약 찾기 + Customer랑 Store join해서 이름 출력해야 됨 --> customer
    public List<ReservationDTO> findReservationsByUserId(String userId) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM Reservation WHERE userId = ?");

        Object[] parameters = new Object[] {userId};
        jdbcUtil.setSqlAndParameters(query.toString(), parameters);

        List<ReservationDTO> reservationList = new ArrayList<>();

        try {
            ResultSet rs = jdbcUtil.executeQuery();   // SELECT 문 실행
            while (rs.next()) {   // 검색 결과가 있으면
                ReservationDTO reservation = new ReservationDTO();
                reservation.setReservationId(rs.getInt("reservationId"));
                reservation.setResDaTi(rs.getDate("resDaTi"));
                reservation.setUserId(rs.getString("userId"));
                reservation.setStoreId(rs.getInt("storeId"));

                reservationList.add(reservation);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }

        return reservationList;
    }


    // 사용자별 예약 수 확인 + Customer join해서 이름 출력해야 됨 --> Customer
    public Map<String, Integer> countReservationsByUser() {
        String sql = "SELECT userId, COUNT(*) FROM Reservation GROUP BY userId";
        Map<String, Integer> countMap = new HashMap<>();

        jdbcUtil.setSqlAndParameters(sql, null);

        try {
            ResultSet rs = jdbcUtil.executeQuery();
            while (rs.next()) {
                String userId = rs.getString("userId");
                int count = rs.getInt(2);    // COUNT(*)의 결과는 두 번째 컬럼에 들어갑니다.
                countMap.put(userId, count);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }

        return countMap;
    }
    
    public CustomerDTO getCustomer(int userId) {
        // userId를 기반으로 데이터베이스에서 고객을 검색하는 구현
        // ...
        StringBuilder query = new StringBuilder();
        query.append("SELECT deptno, manager, COUNT(empno) AS numOfEmps ");
        query.append("FROM EMP0764 JOIN DEPT0764 USING (deptno) ");
        query.append("WHERE dname = ? ");
        query.append("GROUP BY deptno, manager ");
        
        jdbcUtil.setSqlAndParameters(query.toString(), new Object[]{deptName}); // JDBCUtil에 질의문과 파라미터 설정   
        

        // JDBCUtil을 사용한 예제 코드:
        try (Connection connection = jdbcUtil.getConnection()) {
            String sql = "SELECT * FROM customer_table WHERE userId=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, userId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // 데이터베이스에서 고객 정보를 가져와서 CustomerDTO 객체에 설정
                        int customerId = resultSet.getInt("userId");
                        String uName = resultSet.getString("uName");
                        String uPassword = resultSet.getString("uPassword");
                        String uPhone = resultSet.getString("uPhone");
                        String uMail = resultSet.getString("uMail");

                        // 고객 정보를 담은 CustomerDTO 객체 반환
                        return new CustomerDTO(customerId, uName, uPassword, uPhone, uMail, null); // 또는 petList를 가져오는 방식으로 변경
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();       // ResultSet, PreparedStatement, Connection 등 해제
        }

        return null; // 검색 결과가 없을 경우 null 반환
    }

    public void addCustomer(CustomerDTO customer) {
        // 데이터베이스에 새로운 고객을 추가하는 구현
        // ...

        // JDBCUtil을 사용한 예제 코드:
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "INSERT INTO customer_table (userId, uName, uPassword, uPhone, uMail) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, customer.getUserId());
                preparedStatement.setString(2, customer.getuName());
                preparedStatement.setString(3, customer.getuPassword());
                preparedStatement.setString(4, customer.getuPhone());
                preparedStatement.setString(5, customer.getuMail());

                preparedStatement.executeUpdate();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();       // ResultSet, PreparedStatement, Connection 등 해제
        }
    }

    public void updateCustomer(CustomerDTO customer) {
        // 데이터베이스에서 기존 고객을 업데이트하는 구현
        // ...

        // JDBCUtil을 사용한 예제 코드:
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "UPDATE customer_table SET uName=?, uPassword=?, uPhone=?, uMail=? WHERE userId=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, customer.getuName());
                preparedStatement.setString(2, customer.getuPassword());
                preparedStatement.setString(3, customer.getuPhone());
                preparedStatement.setString(4, customer.getuMail());
                preparedStatement.setInt(5, customer.getUserId());

                preparedStatement.executeUpdate();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();       // ResultSet, PreparedStatement, Connection 등 해제
        }
    }

    public void deleteCustomer(int customerId) {
        // customerId로 데이터베이스에서 고객을 삭제하는 구현
        // ...

        // JDBCUtil을 사용한 예제 코드:
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "DELETE FROM customer_table WHERE userId=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, customerId);

                preparedStatement.executeUpdate();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();       // ResultSet, PreparedStatement, Connection 등 해제
        }
    }

    public List<PetDTO> getAllPets() {
        // 데이터베이스에서 모든 애완동물을 가져오는 구현
        // ...

        return new ArrayList<>(); // 실제 구현으로 대체하세요
    }

    public void addPet(PetDTO pet) {
        // 데이터베이스에 새로운 애완동물을 추가하는 구현
        // ...

        // JDBCUtil을 사용한 예제 코드:
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "INSERT INTO pet_table (petId, pImage_path, pName, pAge, pCategory, pDetailCa, pNeureting) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, pet.getPetId());
                preparedStatement.setString(2, pet.getpImage_path());
                preparedStatement.setString(3, pet.getpName());
                preparedStatement.setInt(4, pet.getpAge());
                preparedStatement.setString(5, pet.getpCategory());
                preparedStatement.setString(6, pet.getpDetailCa());
                preparedStatement.setString(7, pet.getpNeureting());

                preparedStatement.executeUpdate();
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();       // ResultSet, PreparedStatement, Connection 등 해제
        }
    }

    public void updatePet(PetDTO pet) {
        // 데이터베이스에서 기존 애완동물을 업데이트하는 구현
        // ...

        // JDBCUtil을 사용한 예제 코드:
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "UPDATE pet_table SET pImage_path=?, pName=?, pAge=?, pCategory=?, pDetailCa=?, pNeureting=? WHERE petId=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, pet.getpImage_path());
                preparedStatement.setString(2, pet.getpName());
                preparedStatement.setInt(3, pet.getpAge());
                preparedStatement.setString(4, pet.getpCategory());
                preparedStatement.setString(5, pet.getpDetailCa());
                preparedStatement.setString(6, pet.getpNeureting());
                preparedStatement.setInt(7, pet.getPetId());

                preparedStatement.executeUpdate();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();       // ResultSet, PreparedStatement, Connection 등 해제
        }
    }

    public void deletePet(int petId) {
        // petId로 데이터베이스에서 애완동물을 삭제하는 구현
        // ...

        // JDBCUtil을 사용한 예제 코드:
        try (Connection connection = JDBCUtil.getConnection()) {
            String sql = "DELETE FROM pet_table WHERE petId=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, petId);

                preparedStatement.executeUpdate();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();       // ResultSet, PreparedStatement, Connection 등 해제
        }
    }
}
