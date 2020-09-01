import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Disks;
import beans.Friends;
import beans.Rentals;

/**
 * Servlet implementation class hogehoge
 */
@WebServlet("/RentalServlet")
public class RentalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RentalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("v");

		String strName = "";
		DaoFactory factory = new DaoFactory();

		if(action.equals("check")){
			strName = "/rental_check.jsp";
            request.setAttribute("TARGET", "貸出");
			request.setAttribute("ACTION", "確認");

			RentalDAO dao = factory.getRentalDAO();
			List<Rentals> list = dao.selectRentals();

			//getterメソッドで値を取り出しコンソール出力
			System.out.println("----表示ここから-----");
			for(Rentals rentals : list){
				System.out.println(rentals.getNumber() + "\t" + rentals.getDate() + "\t" + rentals.getFriendName() + "\t" + rentals.getCount());
			}
			System.out.println("-----ここまで-----");
			request.setAttribute("LIST", list);
		}

		else if(action.equals("check_detail")) {
			String number = request.getParameter("n");

			strName = "/rental_check_detail.jsp";
			request.setAttribute("TARGET", "貸出");
			request.setAttribute("ACTION", "詳細");

			RentalDAO dao = factory.getRentalDAO();
			List<Rentals> list = dao.selectRentalsDetail(number);

			//getterメソッドで値を取り出しコンソール出力
			System.out.println("----表示ここから-----");
			for(Rentals rentals : list){
				System.out.println(rentals.getId() + "\t" + rentals.getNumber() + "\t" + rentals.getDate() + "\t" + rentals.getFriendName() + "\t" + rentals.getDiskName() + "\t" + rentals.getStatusName());
			}
			System.out.println("-----ここまで-----");
			request.setAttribute("LIST", list);

		}

		else if(action.equals("lend")){
			strName = "/rental_lend.jsp";
            request.setAttribute("TARGET", "貸出");
			request.setAttribute("ACTION", "登録");

			FriendDAO friendDao = factory.getFriendDAO();
			DiskDAO diskDao = factory.getDiskDAO();

			List<Friends> friendList = friendDao.selectFriends();
			request.setAttribute("FRIENDLIST", friendList);
			List<Disks> diskList = diskDao.selectDisksnotLended();
			request.setAttribute("DISKLIST", diskList);

		}

		else if(action.equals("history")){
			strName = "/rental_history.jsp";
            request.setAttribute("TARGET", "貸出");
			request.setAttribute("ACTION", "履歴");

			RentalDAO dao = factory.getRentalDAO();
			List<Rentals> list = dao.selectRentalHistory();

			//getterメソッドで値を取り出しコンソール出力
			System.out.println("----表示ここから-----");
			for(Rentals rentals : list){
				System.out.println(rentals.getId() + "\t" + rentals.getNumber() + "\t" + rentals.getDate() + "\t" + rentals.getDiskName() + "\t" + rentals.getFriendName());
			}
			System.out.println("-----ここまで-----");
			request.setAttribute("LIST", list);
		}
		RequestDispatcher rd = request.getRequestDispatcher(strName);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		request.setCharacterEncoding("UTF-8");

		String method = request.getParameter("method");
		String strName = "";
		String target = "";
		String action = "";
	    String message = "";

		if(method.equals("return")){
			String id = request.getParameter("id");

			DaoFactory factory = new DaoFactory();
			RentalDAO dao = factory.getRentalDAO();
			dao.updateRentals(id);
			target = "DVD";
			action = "返却";
			message = "完了しました";

		}
		else if(method.equals("lend")){
			List<String> getDiskList = new ArrayList<String>();
			List<String> diskList = new ArrayList<String>();
			String setter = "";
			String friendName = request.getParameter("friend");
			getDiskList.add(request.getParameter("disk1"));
			getDiskList.add(request.getParameter("disk2"));
			getDiskList.add(request.getParameter("disk3"));
			getDiskList.add(request.getParameter("disk4"));
			getDiskList.add(request.getParameter("disk5"));

			for(Iterator<String> it = getDiskList.iterator(); it.hasNext();) {
				setter = it.next();
            	if(!(setter.equals("unselected"))){
					diskList.add(setter);
				}
        	}

			if(friendName.equals("unselected")){
				target = "貸出登録";
				action = "失敗";
				message = "友達を選択してください";
			}
			else if (diskList.size() == 0) {
  				target = "貸出登録";
				action = "失敗";
				message = "DVDが一つも選択されていません";
			}
			else {
				DaoFactory factory = new DaoFactory();
				RentalDAO dao = factory.getRentalDAO();
				dao.insertRental(diskList, friendName);

				target = "貸出";
				action = "登録";
				message = "完了しました";
			}
		}

		strName = "/done.jsp";
		request.setAttribute("TARGET", target);
		request.setAttribute("ACTION", action);
		request.setAttribute("MESSAGE", message);
        RequestDispatcher rd = request.getRequestDispatcher(strName);
        rd.forward(request, response);
	}

}
