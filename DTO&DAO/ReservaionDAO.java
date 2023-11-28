package lab4;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;

import util.JDBCUtil;

public class ReservaionDAO {
    private JDBCUtil jdbcUtil = null;

    public ReservaionDAO() {
        jdbcUtil = new JDBCUtil();
    }

    // 예약 추가
    public int addReservation(ReservationDTO reservation) { // 이미지도 추가...?
        String sql = "INSERT INTO Reservation (reservationId, resDaTi, userId, storeId) VALUES (?, ?, ?, ?)";
        Object[] parameters = new Object[] {reservation.getReservationId(), Date.valueOf(reservation.getResDaTi()), reservation.getUserId(), reservation.getStoreId()};

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

    // 사용자 ID로 예약 수정 --> DB 전체 수정 + 비고 라인 추가(DB로) --> customer + 얘 한번 확인하기
    public int updateReservationByUser(String userId, LocalDate newDate, String comment) {
        StringBuilder query = new StringBuilder();
        query.append("UPDATE Reservation SET resDaTi = ?, comment = ? WHERE userId = ?");

        Object[] parameters = new Object[] {Date.valueOf(newDate), comment, userId};

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
    } // 얘 다시 확인

    // 사용자 ID로 예약 찾기
    public List<ReservationDTO> findReservationsByUser(String userId) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT r.*, c.uName, s.sName ");
        query.append("FROM Reservation r ");
        query.append("JOIN Customer c ON r.userId = c.userId ");
        query.append("JOIN Store s ON r.storeId = s.storeId ");
        query.append("WHERE r.userId = ?");

        Object[] parameters = new Object[] {userId};
        jdbcUtil.setSqlAndParameters(query.toString(), parameters);

        List<ReservationDTO> reservationList = new ArrayList<>();

        try {
            ResultSet rs = jdbcUtil.executeQuery();
            while (rs.next()) {
                ReservationDTO reservation = new ReservationDTO();
                reservation.setReservationId(rs.getInt("reservationId"));
                reservation.setResDaTi(rs.getDate("resDaTi").toLocalDate());
                reservation.setUserId(rs.getString("userId"));
                reservation.setStoreId(rs.getInt("storeId"));
                reservation.setuName(rs.getString("uName"));  // 사용자 이름
                reservation.setsName(rs.getString("sName"));  // 가게 이름
                
                reservationList.add(reservation);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }

        return reservationList;
    }
    
    // 가게 ID로 예약 찾기
    public List<ReservationDTO> findReservationsByStore(int storeId) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT r.*, s.sName ");
        query.append("FROM Reservation r ");
        query.append("JOIN Store s ON r.storeId = s.storeId ");
        query.append("WHERE r.storeId = ?");

        Object[] parameters = new Object[] {storeId};
        jdbcUtil.setSqlAndParameters(query.toString(), parameters);

        List<ReservationDTO> reservationList = new ArrayList<>();

        try {
            ResultSet rs = jdbcUtil.executeQuery();
            while (rs.next()) {
                ReservationDTO reservation = new ReservationDTO();
                reservation.setReservationId(rs.getInt("reservationId"));
                reservation.setResDaTi(rs.getDate("resDaTi").toLocalDate());
                reservation.setUserId(rs.getString("userId"));
                reservation.setStoreId(rs.getInt("storeId"));
                reservation.setsName(rs.getString("sName"));  // 가게 이름
                
                reservationList.add(reservation);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }

        return reservationList;
    }

    // 사용자별 예약 수 확인
    public Map<String, Integer> countReservationsByUser() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT r.userId, c.uName, COUNT(*) ");
        sql.append("FROM Reservation r ");
        sql.append("JOIN Customer c ON r.userId = c.userId ");
        sql.append("GROUP BY r.userId, c.uName");

        Map<String, Integer> countMap = new HashMap<>();
        jdbcUtil.setSqlAndParameters(sql.toString(), null);

        try {
            ResultSet rs = jdbcUtil.executeQuery();
            while (rs.next()) {
                String userId = rs.getString("userId");
                String uName = rs.getString("uName");
                int count = rs.getInt(3);
                countMap.put(userId + " (" + uName + ")", count);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }

        return countMap;
    }

    // 가게별 예약 수 확인
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

    // 해당 날짜에 누가 예약했는지 확인
    public List<ReservationDTO> findReservationsByDate(LocalDate date) {
        StringBuilder sqlSelect = new StringBuilder();
        sqlSelect.append("SELECT r.*, c.uName FROM Reservation r ");
        sqlSelect.append("JOIN Customer c ON r.userId = c.userId ");
        sqlSelect.append("WHERE DATE(r.resDaTi) = ?");

        Object[] parametersSelect = new Object[] {Date.valueOf(date)};

        jdbcUtil.setSqlAndParameters(sqlSelect.toString(), parametersSelect);

        try {
            List<ReservationDTO> reservationList = new ArrayList<>();
            ResultSet rsSelect = jdbcUtil.executeQuery();

            while (rsSelect.next()) {
                ReservationDTO reservation = new ReservationDTO();
                reservation.setReservationId(rsSelect.getInt("reservationId"));
                reservation.setUserId(rsSelect.getString("userId"));
                reservation.setuName(rsSelect.getString("uName")); // 사용자 이름 추가
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


    // 해당 날짜에 몇 명이 예약했는지 확인
    public int countReservationsByDate(LocalDate date) {
        StringBuilder sqlCount = new StringBuilder();
        sqlCount.append("SELECT COUNT(*) FROM Reservation WHERE DATE(resDaTi) = ?");

        Object[] parametersCount = new Object[] {Date.valueOf(date)};

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

    // 전체 예약 보기
    public List<ReservationDTO> viewAllReservations() {
        StringBuilder sqlSelect = new StringBuilder();
        sqlSelect.append("SELECT r.*, c.uName, s.sName");
        sqlSelect.append("FROM Reservation r ");
        sqlSelect.append("JOIN Customer c ON r.userId = c.userId ");
        sqlSelect.append("JOIN Store s ON r.storeId = s.storeId");

        jdbcUtil.setSqlAndParameters(sqlSelect.toString(), null);
        List<ReservationDTO> reservationList = new ArrayList<>();

        try {
            ResultSet rsSelect = jdbcUtil.executeQuery();

            while (rsSelect.next()) {
                ReservationDTO reservation = new ReservationDTO();
                reservation.setReservationId(rsSelect.getInt("reservationId"));
                reservation.setResDaTi(rsSelect.getDate("resDaTi").toLocalDate());
                reservation.setUserId(rsSelect.getString("userId"));
                reservation.setStoreId(rsSelect.getInt("storeId"));
                reservation.setuName(rsSelect.getString("uName")); // 사용자 이름 추가
                reservation.setsName(rsSelect.getString("sName")); // 가게 이름 추가
                reservation.setComment(rsSelect.getString("comment")); // 코멘트 추가

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
