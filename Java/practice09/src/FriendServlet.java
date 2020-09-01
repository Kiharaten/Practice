import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Friends;

/**
 * Servlet implementation class hogehoge
 */
@WebServlet("/FriendServlet")
public class FriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FriendServlet() {
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
		request.setAttribute("TARGET", "友達");


		if(action.equals("add")){
			strName = "/friend_add.jsp";
			request.setAttribute("ACTION", "追加");

		}
		else if(action.equals("check")){
			strName = "/friend_check.jsp";
			request.setAttribute("ACTION", "確認");

			DaoFactory factory = new DaoFactory();
			FriendDAO dao = factory.getFriendDAO();

			List<Friends> list = dao.selectFriends();

			//getterメソッドで値を取り出しコンソール出力
			System.out.println("----表示ここから-----");
			for(Friends friends : list){
				System.out.println(friends.getId() + "\t" + friends.getName() + "\t" + friends.getMail());
			}
			System.out.println("-----ここまで-----");

			request.setAttribute("LIST", list);

		}
		else if(action.equals("edit")){
			strName = "/friend_edit.jsp";
			request.setAttribute("ACTION", "修正");

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
        String name = request.getParameter("name");
		String mail = request.getParameter("mail");
        String strName = "";
		String target = "";
		String action = "";
        String message = "";

        if(name.equals("")) {
			target = "友達登録";
			action = "失敗";
        	message = "名前を入力してください";
        }
		else if (mail.equals("")) {
			target = "友達登録";
			action = "失敗";
        	message = "メールアドレスを入力してください";
		}
		else {
            DaoFactory factory = new DaoFactory();
            FriendDAO dao = factory.getFriendDAO();
            dao.insertFriends(name, mail);
			target = "友達";
			action = "登録";
			message = "完了しました";
        }

		strName = "/done.jsp";
		request.setAttribute("TARGET", target);
		request.setAttribute("ACTION", action);
		request.setAttribute("MESSAGE", message);
        RequestDispatcher rd = request.getRequestDispatcher(strName);
        rd.forward(request, response);
	}

}