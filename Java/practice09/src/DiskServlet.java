import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Actors;
import beans.Disks;
import beans.Genres;

/**
 * Servlet implementation class hogehoge
 */
@WebServlet("/DiskServlet")
public class DiskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DiskServlet() {
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

//		System.out.println("catched");
		String strName = "";
		request.setAttribute("TARGET", "DVD");
		DaoFactory factory = new DaoFactory();


		if(action.equals("add")){
			strName = "/dvd_add.jsp";
			request.setAttribute("ACTION", "追加");

			GenreDAO genreDao = factory.getGenreDAO();
			ActorDAO actorDao = factory.getActorDAO();

			List<Genres> genreList = genreDao.selectGenres();
			request.setAttribute("GENRELIST", genreList);
			List<Actors> actorList = actorDao.selectActors();
			request.setAttribute("ACTORLIST", actorList);

		}
		else if(action.equals("check")){
			strName = "/dvd_check.jsp";
			request.setAttribute("ACTION", "確認");

			DiskDAO dao = factory.getDiskDAO();
			List<Disks> list = dao.selectDisks();

			//getterメソッドで値を取り出しコンソール出力
			System.out.println("----表示ここから-----");
			for(Disks disks : list){
				System.out.println(disks.getId() + "\t" + disks.getName() + "\t" + disks.getGenre() + "\t" + disks.getActor());
			}
			System.out.println("-----ここまで-----");

			request.setAttribute("LIST", list);

		}
		else if(action.equals("edit")){
			strName = "/dvd_edit.jsp";
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
		String genre = request.getParameter("genre");
		String actor = request.getParameter("actor");

		System.out.println(name + " : " + genre + " : " + actor);

        String strName = "";
		String target = "";
		String action = "";
        String message = "";

        if(name.equals("")) {
			target = "DVD登録";
			action = "失敗";
        	message = "タイトルを入力してください";
        }
		else if (genre.equals("unselected")) {
			target = "DVD登録";
			action = "失敗";
        	message = "ジャンルを入力してください";
		}
		else if (actor.equals("unselected")) {
			target = "DVD登録";
			action = "失敗";
        	message = "役者を入力してください";
		}
		else {
            DaoFactory factory = new DaoFactory();
            DiskDAO dao = factory.getDiskDAO();
            dao.insertDisks(name, genre, actor);
			target = "DVD";
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
