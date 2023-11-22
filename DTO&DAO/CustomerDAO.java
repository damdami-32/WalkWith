import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class CustomerDAO {

    private JDBCUtil jdbcUtil = null;

    public CustomerDAO() {
        jdbcUtil = new JDBCUtil();
    }

    //customer에 fk:StoreId인 StoreDTO List객체(filed 이름 like) 있다고 가정
    public List<StoreDTO> getLikeListByUserId(String userID){
    	StringBuilder query = new StringBuilder();
        query.append("SELECT like FROM CUSTOEMR WHERE userId = ? ");

        jdbcUtil.setSqlAndParameters(query.toString(), new Object[] {userId});

        List<StoreDTO> LikeList = new ArrayList<>();
        try {
            ResultSet rs = jdbcUtil.executeQuery();
            while (rs.next()) {
            	StoreDTO like = new StoreDTO();
                like.setsName(rs.getStirng("sName"));
                like.setSellerId(rs.getString("sellerId"));
            	
                LikeList.add(like);                
            }
            return LikeList;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
    	return null; 
    }

    //reservation talbe에 fk:userId가 존재하기 때문에 별도의 join 필요 없을 것으로 사료
    public int updateReservationByUserId(String userId, Date newDate) {
        StringBuilder query = new StringBuilder();
        query.append("UPDATE Reservation SET resDaTi = ? WHERE userId = ? ");

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

    //reservation talbe에 fk:userId가 존재하기 때문에 별도의 join 필요 없을 것으로 사료
    public List<ReservationDTO> findReservationsByUserId(String userId) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM Reservation WHERE userId = ? ");

        Object[] parameters = new Object[] {userId};
        jdbcUtil.setSqlAndParameters(query.toString(), parameters);

        List<ReservationDTO> reservationList = new ArrayList<>();

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

    //reservation talbe에 fk:userId가 존재하기 때문에 별도의 join 필요 없을 것으로 사료
    public Map<String, Integer> countReservationsByUser() {
        String sql = "SELECT userId, COUNT(*) FROM Reservation GROUP BY userId ";
        Map<String, Integer> countMap = new HashMap<>();

        jdbcUtil.setSqlAndParameters(sql, null);

        try {
            ResultSet rs = jdbcUtil.executeQuery();
            while (rs.next()) {
                String userId = rs.getString("userId");
                int count = rs.getInt(2);
                countMap.put(userId, count);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }

        return countMap;
    }

    public CustomerDTO getCustomer(String userId) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM CUSTOMER ");
        query.append("WHERE userid = ? ");

        jdbcUtil.setSqlAndParameters(query.toString(), new Object[]{userId});

        try {
            ResultSet resultSet = jdbcUtil.executeQuery();
            if (resultSet.next()) {
                String uName = resultSet.getString("uName");
                String uPhone = resultSet.getString("uPhone");
                String uMail = resultSet.getString("uMail");
                List<PetDTO> petList = getAllPets(userId);
                return new CustomerDTO(userId, uName, null, uPhone, uMail, petList);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }

        return null;
    }

    public void addCustomer(CustomerDTO customer) {
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "INSERT INTO customer_table (userId, uName, uPassword, uPhone, uMail) VALUES (?, ?, ?, ?, ?) ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, customer.getUserId()); // userId는 문자열로 바꾸어주어야 합니다.
                preparedStatement.setString(2, customer.getuName());
                preparedStatement.setString(3, customer.getuPassword());
                preparedStatement.setString(4, customer.getuPhone());
                preparedStatement.setString(5, customer.getuMail());

                preparedStatement.executeUpdate();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
    }

    public void updateCustomer(CustomerDTO customer) {
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "UPDATE customer_table SET uName=?, uPassword=?, uPhone=?, uMail=? WHERE userId=? ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, customer.getuName());
                preparedStatement.setString(2, customer.getuPassword());
                preparedStatement.setString(3, customer.getuPhone());
                preparedStatement.setString(4, customer.getuMail());
                preparedStatement.setString(5, customer.getUserId());

                preparedStatement.executeUpdate();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
    }

    public void deleteCustomer(String userId) {
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "DELETE FROM customer_table WHERE userId=? ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, userId);

                preparedStatement.executeUpdate();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
    }

    public List<PetDTO> getAllPets(String userId) {
    	StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM Pet WHERE userId = ? ");
        jdbcUtil.setSqlAndParameters(query.toString(), new Object[] {userId});

        List<PetDTO> petList = new ArrayList<>();
        try {
            ResultSet rs = jdbcUtil.executeQuery();
            while (rs.next()) {
                PetDTO pet = new PetDTO();
                pet.setPId(rs.getString("petId")))
				pet.setPImage(rs.getString("pImage")))
				pet.setPName(rs.getString("pName")))
				pet.setPCategory(rs.getString("pCategory")))
				pet.setPDetailCa(rs.getString("pDetailCa")))
				pet.setPNeureting(rs.getString("pNeureting")))
				pet.setUserId(userId)

				petList.add(pet);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }

        return petList;//List<petDTO>객체 반환
    }

    public void addPet(PetDTO pet) {
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "INSERT INTO pet_table (petId, pImage_path, pName, pAge, pCategory, pDetailCa, pNeureting) VALUES (?, ?, ?, ?, ?, ?, ?) ";
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
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
    }

    public void updatePet(PetDTO pet) {
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "UPDATE pet_table SET pImage_path=?, pName=?, pAge=?, pCategory=?, pDetailCa=?, pNeureting=? WHERE petId=? ";
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
            jdbcUtil.close();
        }
    }

    public void deletePet(int petId) {
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "DELETE FROM pet_table WHERE petId=? ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, petId);

                preparedStatement.executeUpdate();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
    }
}
