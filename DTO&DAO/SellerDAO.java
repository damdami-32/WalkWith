package lab4;

import java.util.*;
import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import util.JDBCUtil;

public class SellerDAO {
	private JDBCUtil jdbcUtil = null;
	
	public SellerDAO() {
		jdbcUtil = new JDBCUtil();
	}
	
	//전제 seller의 정보 출력
	public List<SellerDTO> getAllSellerInfo() {
		StringBuilder query = new StringBuilder();
    	query.append("SELECT sellerId, seName, sePassword, sePhone, seMail ");
    	query.append("FROM Seller ");
    	
    	try {
    		ResultSet rs = jdbcUtil.executeQuery();
        	List<SellerDTO> selList = new ArrayList<SellerDTO>();    
        	
        	while (rs.next()) {
        		SellerDTO seller = new SellerDTO(
        				rs.getString("sellerId"),
        				rs.getString("seName"),
        				rs.getString("sePassword"),
        				rs.getString("sePhone"),
        				rs.getString("seMail")
        				);
        		selList.add(seller);
        		
        	}
        	return selList;
        	
    	} catch (SQLException e) {
    		e.getStackTrace();
    	} finally {
    		jdbcUtil.close();
    	}
    	return null;
	}
	
	//SellerId를 매개변수로 해당하는 Seller의 정보 받아오기
	public SellerDTO findSeller(String sellerId) {
		StringBuilder query = new StringBuilder();
    	query.append("SELECT sellerId, seName, sePassword, sePhone, seMail ");
    	query.append("FROM Seller WHERE sellerId = ? ");
    	
    	jdbcUtil.setSqlAndParameters(query.toString(), new Object[]{sellerId});
    	
    	try {
    		ResultSet rs = jdbcUtil.executeQuery();  
    		SellerDTO seller = new SellerDTO(
    				rs.getString("sellerId"),
    				rs.getString("seName"),
    				rs.getString("sePassword"),
    				rs.getString("sePhone"),
    				rs.getString("seMail")
    				);
        	return seller;
        	
    	} catch (SQLException e) {
    		e.getStackTrace();
    	} finally {
    		jdbcUtil.close();
    	}
    	return null;
	}
	
	//sellerId 값으로 자신의 가게 찾기
	public List<StoreDTO> findMyStore(String sellerId) {
		StringBuilder query = new StringBuilder();
    	query.append("SELECT sellerId, storeId, sName, sPhone, sTime, openDate, sStarScore, sDescription ");
    	query.append("FROM Seller JOIN Store USING (sellerId) ");
    	
    	try {
    		ResultSet rs = jdbcUtil.executeQuery();
        	List<StoreDTO> strList = new ArrayList<StoreDTO>();    
        	
        	while (rs.next()) {
        		StoreDTO store = new StoreDTO(
        				rs.getString("sellerId"),
        				rs.getInt("storeId"),
        				rs.getString("sName"),
        				rs.getString("sPhone"),
        				rs.getTime("sTime"),
        				rs.getString("openDate"),
        				rs.getDouble("sStarScore"),
        				rs.getString("sDescription"),
        				rs.getInt("likeCount")
        				);
        		strList.add(store);
        		
        	}
        	return strList;
        	
    	} catch (SQLException e) {
    		e.getStackTrace();
    	} finally {
    		jdbcUtil.close();
    	}
    	return null;
	}
	
	//사용자 정보 업데이트 함수 Update(sellerName, Password 변경, Phone, Mail / 삭제도 필요한 것 같은데... 시간 상 삭제는 구현 못했습니다,,,ㅜㅜ
	public SellerDTO sellerInfoUpdate(SellerDTO sel) {
        String selId = null;
        try {
            String query1 = "SELECT sellerId FROM Seller WHERE sellerId = ?";
            jdbcUtil.setSqlAndParameters(query1.toString(), new Object[] {sel.getSellerId()});
            
            ResultSet rs = jdbcUtil.executeQuery();
            if (rs.next())
                selId = rs.getString("sellerId");
            else
                return null;;

            String update1 = "UPDATE Seller SET seName = ?, sePassword = ?, sePhone = ?, seMail = ? WHERE selId = ? ";
            jdbcUtil.setSqlAndParameters(update1.toString(), new Object[] {sel.getSeName(), sel.getSePassword(), sel.getSePhone(), sel.getSeMail(), selId});
            jdbcUtil.executeUpdate();

        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }
        return null;
	}
	
	//Id값으로 해당 Seller 정보 삭제
	public SellerDTO deleteSeller(String sellerId) {
	    StringBuilder query = new StringBuilder();
	    query.append("DELETE FROM Seller WHERE sellerID = ? ");
	    
	    jdbcUtil.setSqlAndParameters(query.toString(), new Object[] {sellerId} );
	    try {
            ResultSet rs = jdbcUtil.executeQuery();
            
            if (rs.next()) {        // 검색 결과 존재
                SellerDTO seller = new SellerDTO(
                        rs.getString(sellerId),
                        rs.getString("seName"),
                        rs.getString("sePassword"),
                        rs.getString("sePhone"),
                        rs.getString("seMail")
                        );
                return seller;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();       // ResultSet, PreparedStatement, Connection 등 해제
        }
        return null;
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
}
