import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Genres;

/**
 * Servlet implementation class hogehoge
 */
@WebServlet("/GenreServlet")
public class GenreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenreServlet() {
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
		request.setAttribute("TARGET", "ジャンル");


		if(action.equals("add")){
			strName = "/genre_add.jsp";
			request.setAttribute("ACTION", "追加");

		}
		else if(action.equals("check")){
			strName = "/genre_check.jsp";
			request.setAttribute("ACTION", "確認");

			DaoFactory factory = new DaoFactory();
			GenreDAO dao = factory.getGenreDAO();

			List<Genres> list = dao.selectGenres();

			//getter値を取り出しコンソール出力
			System.out.println("----表示ここから-----");
			for(Genres genres : list){
				System.out.println(genres.getId() + "\t" + genres.getName());
			}
			System.out.println("-----ここまで-----");

			request.setAttribute("LIST", list);

		}
		else if(action.equals("edit")){
			strName = "/genre_edit.jsp";
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
        String strName = "";
		String target = "";
		String action = "";
        String message = "";

        if(name.equals("")) {
			target = "ジャンル登録";
			action = "失敗";
        	message = "名前を入力してください";
        } else {
            DaoFactory factory = new DaoFactory();
            GenreDAO dao = factory.getGenreDAO();
            dao.insertGenres(name);
			target = "ジャンル";
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
