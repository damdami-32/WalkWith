package lab4;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.store;
import model.store;
import util.JDBCUtil;


public class store_reviewDAO {
    private JDBCUtil jdbcUtil = null;
    
    public store_reviewDAO() {       // 기본 생성자
        jdbcUtil = new JDBCUtil();
    }
    
 
    //추천 매장 출력
    public List<StoreDTO> selectionStore(string userId){
    	//if 예약했던 가게 존재할 시 2개까지 넣기 + 나머지 8개 recommand 매장으로 채우기
    	// 추천매장 총 10개 반환 -> 필요시 숫자 조정할 것!
    	//customer랑 reservation join해서 storeId 받아오기
    	//reservation에 fk:(storeId, userId) 존재 -> reservation이랑 store만 join
    	//List<store> 객체 반환
    	StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM RESERVATION CROSS JOIN STORE WHERE userId = ? ");
        jdbcUtil.setSqlAndParameters(query.toString(), new Object[] {userId});

        List<StoreDTO> recommandList = new ArrayList<>();
        try {
            ResultSet rs = jdbcUtil.executeQuery();
            while (rs.next() && recommandList.size() < 3) {
            	StoreDTO recommand = new StoreDTO();
                recommand.setsName(rs.getStirng("sName"));
                recommand.setSPhone(rs.getString("sPhone"));
            	recommand.setSTime(rs.getDate("sTime"));
            	recommand.setSStarScore(rs.getFloat("sStarScore"));
            	recommand.setSDetailDescription(rs.getString("sDeailDescription"));
            	recommand.setSellerId(rs.getDtring("sellerId"));
            	recommand.setOpenDate(rs.getDate("openDate"));
            	 
                recommandList.add(recommand);      
            }
            
            return recommandList;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
    	return null; 
    }
    
    
    // category를 join해서 받아와야할까요? setCategory에서 설정 후 가져와서 그 결과를 넣는다.
    public int addStore(Store store) throws SQLException {
        String sql = "INSERT INTO store VALUES (?, ?, ?, ?, ?, ?, ?, ?)";      
        Object[] param = new Object[] {store.getsName(), store.getsPhone(), 
                store.getsTime(), store.getsStarScore(), store.getsDetailDescription(), 
                store.getSellerId(), store.getopenDate(), store.getCategory()};             
        jdbcUtil.setSqlAndParameters(sql, param);   // JDBCUtil 에 insert문과 매개 변수 설정
                        
        try {               
            int result = jdbcUtil.executeUpdate();  // insert 문 실행
            return result;
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } finally {     
            jdbcUtil.commit();
            jdbcUtil.close();   // resource 반환
        }       
        return 0;           
    }
    
    public int updateStore(Store store) throws SQLException {
        String sql = "UPDATE store "
                    + "SET sName=?, sPhone=?, sTime=?, sStarScore=? sDetailDescription=? sellerId=? openDate=? category=?"
                    + "WHERE storeid=?";
        Object[] param = new Object[] {store.getsName(), store.getsPhone(), 
                    store.getsTime(), store.getsStarScore(), store.getsDetailDescription(), 
                    store.getSellerId(), store.getopenDate(), store.getCategory()};              
        jdbcUtil.setSqlAndParameters(sql, param);   // JDBCUtil에 update문과 매개 변수 설정
            
        try {               
            int result = jdbcUtil.executeUpdate();  // update 문 실행
            return result;
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        }
        finally {
            jdbcUtil.commit();
            jdbcUtil.close();   // resource 반환
        }       
        return 0;
    }
    
    public void setCategory() {
        String sql = "SELECT name FROM Category ";
        
        jdbcUtil.setSql(sql);
        
        try {               
            int result = jdbcUtil.executeUpdate();  // update 문 실행
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        }
        finally {
            jdbcUtil.commit();
            jdbcUtil.close();   // resource 반환
        }       
    }
    
    public int deleteStore(String storeId) throws SQLException {
        String sql = "DELETE FROM store WHERE storeId=?";     
        jdbcUtil.setSqlAndParameters(sql, new Object[] {storeId});   // JDBCUtil에 delete문과 매개 변수 설정

        try {               
            int result = jdbcUtil.executeUpdate();  // delete 문 실행
            return result;
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        }
        finally {
            jdbcUtil.commit();
            jdbcUtil.close();   // resource 반환
        }       
        
        return 0;
    }
    
    public int writeReview() {
        String sql = "INSERT INTO Review VALUES (?)";      
        Object[] param = new Object[] {//리뷰 내용};             
        jdbcUtil.setSqlAndParameters(sql, param);   // JDBCUtil 에 insert문과 매개 변수 설정
        
     
        try {               
            int result = jdbcUtil.executeUpdate();  // delete 문 실행
            return result;
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        }
        finally {
            jdbcUtil.commit();
            jdbcUtil.close();   // resource 반환
        }       
        
        return 0;       
    }
    
    public int writeRating() {
            String sql = "INSERT INTO Review VALUES (?)";      
            Object[] param = new Object[] {//별점 선택};             
            jdbcUtil.setSqlAndParameters(sql, param);   // JDBCUtil 에 insert문과 매개 변수 설정
            
         
            try {               
                int result = jdbcUtil.executeUpdate();  // delete 문 실행
                return result;
            } catch (Exception ex) {
                jdbcUtil.rollback();
                ex.printStackTrace();
            }
            finally {
                jdbcUtil.commit();
                jdbcUtil.close();   // resource 반환
            }       
            
            return 0;
    }
            
            
    public int readReview() {
        String sql = "SELECT review, rating FROM Review ";       // 작성자 정보도 같이? 그럼 join 해야하나 -> customer rating 2개 조인
        
        jdbcUtil.setSql(sql);
        
        try {               
            int result = jdbcUtil.executeUpdate();  // update 문 실행
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        }
        finally {
            jdbcUtil.commit();
            jdbcUtil.close();   // resource 반환
        }
    }
    
    public int updateReview(Review review) throws SQLException {
        String sql = "UPDATE Review "
                    + "SET review=? "
                    + "WHERE storeid=?";
        Object[] param = new Object[] {review.getReview(), review.getReviewId()};              
        jdbcUtil.setSqlAndParameters(sql, param);   // JDBCUtil에 update문과 매개 변수 설정
            
        try {               
            int result = jdbcUtil.executeUpdate();  // update 문 실행
            return result;
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        }
        finally {
            jdbcUtil.commit();
            jdbcUtil.close();   // resource 반환
        }       
        return 0;
    } 
    
    public int updateRating(Review review) throws SQLException {
        String sql = "UPDATE Review "
                    + "SET rating=? "
                    + "WHERE reviewId=?";
        Object[] param = new Object[] {review.getRating(), review.getReviewId()};              
        jdbcUtil.setSqlAndParameters(sql, param);   // JDBCUtil에 update문과 매개 변수 설정
            
        try {               
            int result = jdbcUtil.executeUpdate();  // update 문 실행
            return result;
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        }
        finally {
            jdbcUtil.commit();
            jdbcUtil.close();   // resource 반환
        }       
        return 0;
    }
}
