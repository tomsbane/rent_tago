package action;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import svc.ShortRentListService;
import vo.ActionForward;
import vo.Rentcar;

public class ShortRentListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ShortRentListService shortRentListService  = new ShortRentListService(); 
		//주의 : 매개값으로  request 전송이유?session영역에 공유되어 있는 "장바구니 목록 객체(cartList)"를 얻어오기 위해 
		ArrayList<Rentcar> carList = shortRentListService.getCarList(request);
		
		request.setAttribute("carList", carList);
		
		ActionForward forward =new ActionForward("carList.jsp", false);//"디스패치 방식"으로 포워딩
		return forward;
	}

}
