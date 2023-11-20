package lab4;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.JDBCUtil;

// 아마 거의 모든 코드에 join이 들어감으로써 사용자명과 가게명이 들어가야 될것 같다.

public class ReservaionDAO {
    private JDBCUtil jdbcUtil = null;   // JDBCUtil 필드 선언
    
    public ReservaionDAO() {               // 생성자 
        jdbcUtil = new JDBCUtil();      // JDBCUtil 객체 생성
    }
    
    
    // 예약 추가
    public int addReservation(ReservationDTO reservation) {
        String sql = "INSERT INTO Reservation (reservationId, resDaTi, userId, storeId) VALUES (?, ?, ?, ?)";
        Object[] parameters = new Object[] {reservation.getReservationId(), reservation.getResDaTi(), reservation.getUserId(), reservation.getStoreId()};

        jdbcUtil.setSqlAndParameters(sql, parameters);   // JDBCUtil에 query 설정

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

    // 예약 취소
    public int deleteReservation(int reservationId) {
        String sql = "DELETE FROM Reservation WHERE reservationId = ?";
        Object[] parameters = new Object[] {reservationId};

        jdbcUtil.setSqlAndParameters(sql, parameters);

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
    public List<ReservationDTO> findReservationsByUser(String userId) {
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

    // 가게 ID로 예약 찾기 + Customer랑 Store join해서 이름 출력해야 됨 --> seller
    public List<ReservationDTO> findReservationsByStore(int storeId) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM Reservation WHERE storeId = ?");

        Object[] parameters = new Object[] {storeId};
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

    // 가게별 예약 수 확인+ Store join해서 이름 출력해야 됨 --> seller
    public Map<Integer, Integer> countReservationsByStore() {
        String sql = "SELECT storeId, COUNT(*) FROM Reservation GROUP BY storeId";
        Map<Integer, Integer> countMap = new HashMap<>();

        jdbcUtil.setSqlAndParameters(sql, null);

        try {
            ResultSet rs = jdbcUtil.executeQuery();
            while (rs.next()) {
                int storeId = rs.getInt("storeId");
                int count = rs.getInt(2);
                countMap.put(storeId, count);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }

        return countMap;
    }
    
    // 해당 날짜에 누가 예약했는지 확인 + Customer join해서 이름 출력해야 됨 --> seller
    public List<ReservationDTO> findReservationsByDate(Date date) {
        StringBuilder sqlSelect = new StringBuilder();
        sqlSelect.append("SELECT * FROM Reservation WHERE DATE(resDaTi) = ?");

        Object[] parametersSelect = new Object[] {date};

        jdbcUtil.setSqlAndParameters(sqlSelect.toString(), parametersSelect);

        try {
            List<ReservationDTO> reservationList = new ArrayList<>();
            ResultSet rsSelect = jdbcUtil.executeQuery();

            while (rsSelect.next()) {
                ReservationDTO reservation = new ReservationDTO();
                reservation.setReservationId(rsSelect.getInt("reservationId"));
                reservation.setUserId(rsSelect.getString("userId"));
                reservationList.add(reservation);
            }

            return reservationList;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }

        return null;
    }

    // 해당 날짜에 몇 명이 예약했는지 확인 --> seller
    public int countReservationsByDate(Date date) {
        StringBuilder sqlCount = new StringBuilder();
        sqlCount.append("SELECT COUNT(*) FROM Reservation WHERE DATE(resDaTi) = ?");

        Object[] parametersCount = new Object[] {date};

        jdbcUtil.setSqlAndParameters(sqlCount.toString(), parametersCount);

        try {
            ResultSet rsCount = jdbcUtil.executeQuery();
            int count = 0;
            if (rsCount.next()) {
                count = rsCount.getInt(1);
            }

            return count;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }

        return 0;
    }

    // 전체 예약 보기+ Customer랑 Store join해서 이름 출력해야 됨
    public List<ReservationDTO> viewAllReservations() {
        String sql = "SELECT * FROM Reservation";
        List<ReservationDTO> reservationList = new ArrayList<>();

        jdbcUtil.setSqlAndParameters(sql, null);

        try {
            ResultSet rs = jdbcUtil.executeQuery();

            while (rs.next()) {
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
}