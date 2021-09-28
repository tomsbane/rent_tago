//DB로 SQL구문을 전송하는 클래스
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.Rentcar;

import static db.JdbcUtil.*;//static:모든 메서드들 미리 메모리에 올림

public class CarDAO {
	// 멤버변수(전역변수 : 전체 메서드에서 사용 가능)
	Connection con = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;

	/***
	 * 싱글톤 패턴 : DogDAO객체 단 1개만 생성************************** 이유? 외부 클래스에서 "처음 생성된
	 * DogDAO객체를 공유해서 사용하도록 하기 위해"
	 */
	private CarDAO() {
	}

	private static CarDAO carDAO;

	// static이유? 객체를 생성하기 전에 이미 메모리에 올라간 getInstance()메서드를 통해서만 BoardDAO객체를 1개만 만들도록
	// 하기 위해
	public static CarDAO getInstance() {
		if (carDAO == null) {// 객체가 없으면
			carDAO = new CarDAO();// 객체 생성
		}

		return carDAO;// 기존 객체의 주소 리턴
	}

	/************************************************************/

	public void setConnection(Connection con) {// Connection객체를 받아 DB 연결
		this.con = con;
	}

	public ArrayList<Rentcar> getCarList() {
		ArrayList<Rentcar> carList = null;

		try {
			psmt = con.prepareStatement("select * from rentcar");
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				carList = new ArrayList<Rentcar>();
				do {
					Rentcar rentcar = new Rentcar(
						rs.getInt("car_no"),
						rs.getString("car_name"),
						rs.getString("car_group"),
						rs.getInt("car_year"),
						rs.getString("car_reserve"),
						rs.getInt("car_price"),
						rs.getString("car_brand"),
						rs.getString("car_img1"),
						rs.getString("car_img2"),
						rs.getInt("car_readCount"));
					
					carList.add(rentcar);
				}while(rs.next());
			}
		} catch (Exception e) {
			System.out.println("getCarList 에러 :" + e);
		}finally {
			close(rs);
			close(psmt);
		}

		return carList;
	}

	/*
	 * //1. 모든 개 상품 정보를 조회하여 ArrayList<Dog>객체 반환 public ArrayList<Dog>
	 * selectDogList() { ArrayList<Dog> dogList = null;
	 * 
	 * try { pstmt = con.prepareStatement("select * from dog"); rs =
	 * pstmt.executeQuery();
	 * 
	 * if(rs.next()) { dogList = new ArrayList<Dog>();
	 * 
	 * do { Dog dog=new Dog(rs.getInt("id"), rs.getString("kind"),
	 * rs.getString("country"), rs.getInt("price"), rs.getInt("height"),
	 * rs.getInt("weight"), rs.getString("content"), rs.getString("image"),
	 * rs.getInt("readcount"));
	 * 
	 * dogList.add(dog); }while(rs.next()); }//if
	 * 
	 * 
	 * } catch (Exception e) { System.out.println("selectDogList 에러 :" +
	 * e);//e:예외종류+예외메세지 }finally { close(rs); close(pstmt); }
	 * 
	 * return dogList; }
	 * 
	 * 
	 * //id로 조회수 1증가 public int updateReadCount(int id) { //String sql =
	 * "update dog SET readcount=readcount+1 where id=?"; String sql =
	 * "update dog SET readcount=readcount+1 where id="+id; int updateCount = 0;
	 * 
	 * try { pstmt = con.prepareStatement(sql); //pstmt.setInt(1, id); updateCount =
	 * pstmt.executeUpdate();//업데이트가 성공하면 1리턴받음
	 * 
	 * } catch (Exception e) { System.out.println("updateReadCount 에러 :" +
	 * e);//e:예외종류+예외메세지 }finally { close(rs); close(pstmt); }
	 * 
	 * return updateCount; }
	 * 
	 * //id로 개 정보를 조회하여 Dog객체를 반환 public Dog selectDog(int id){ Dog dog = null;
	 * 
	 * try { pstmt = con.prepareStatement("select * from dog where id="+id); rs =
	 * pstmt.executeQuery();
	 * 
	 * if(rs.next()) { dog=new Dog(rs.getInt("id"), rs.getString("kind"),
	 * rs.getString("country"), rs.getInt("price"), rs.getInt("height"),
	 * rs.getInt("weight"), rs.getString("content"), rs.getString("image"),
	 * rs.getInt("readcount"));
	 * 
	 * }//if
	 * 
	 * 
	 * } catch (Exception e) { System.out.println("selectDog 에러 :" +
	 * e);//e:예외종류+예외메세지 }finally { close(rs); close(pstmt); }
	 * 
	 * return dog; }
	 * 
	 * //새로운 개 상품 정보를 DB에 추가 public int insertDog(Dog dog){ String sql =
	 * "INSERT INTO dog VALUES(dog_seq.nextval,?,?,?,?,?,?,?,?)"; int insertCount =
	 * 0;
	 * 
	 * try { pstmt = con.prepareStatement(sql);
	 * 
	 * pstmt.setString(1, dog.getKind()); pstmt.setString(2, dog.getCountry());
	 * pstmt.setInt(3, dog.getPrice()); pstmt.setInt(4, dog.getHeight());
	 * pstmt.setInt(5, dog.getWeight()); pstmt.setString(6, dog.getContent());
	 * pstmt.setString(7, dog.getImage()); pstmt.setInt(8, dog.getReadcount());
	 * 
	 * insertCount = pstmt.executeUpdate();//업데이트가 성공하면 1리턴받음
	 * 
	 * } catch (Exception e) { System.out.println("insertDog 에러 :" +
	 * e);//e:예외종류+예외메세지 }finally { close(rs); close(pstmt); }
	 * 
	 * return insertCount;
	 * 
	 * }
	 */

}
