

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Counter
 */
@WebServlet(description = "计数器Servlet", urlPatterns = { "/Counter","/counter" })
public class Counter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	String url = "jdbc:postgresql://localhost:5432/Counter";//数据库地址
	String user = "admin";//数据库用户名
	String password = "admin";//数据库密码
    public Counter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Connection database = DriverManager.getConnection(url, user, password);
			System.out.println(database);
			System.out.println("数据库测试连接成功！");
			database.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		try {
			Connection con = DriverManager.getConnection(url, user, password);
			System.out.println("GET请求的数据库连接成功");
			String name =request.getParameter("name");
			if(name==null||name.equals(""))
			{
				outputTEXT(out, "参数name为空，请重试！");
				return;
			}
			System.out.println(name+"  发出了GET请求");
			
			String search ="SELECT number FROM counter where \"name\" = '"+name+"'";
			String newnameString ="INSERT into counter(\"name\",number) VALUES(?,?)";
			String add="UPDATE counter SET number=number+1 WHERE \"name\"= '"+name+"'";
			System.out.println("即将执行SQL语句："+search);
			PreparedStatement ps= con.prepareStatement(search);
			ResultSet rs= ps.executeQuery();		
			if(!rs.next()){
				ps=con.prepareStatement(newnameString);
				ps.setString(1,name);
				ps.setInt(2,1);
				ps.execute();
				outputTEXT(out, 1);
			}
			else {
				PreparedStatement ps2=con.prepareStatement(add);
				ps2.execute();
				rs=ps.executeQuery();
				rs.next();
				outputTEXT(out, rs.getInt("number"));
				ps2.close();
			}

			rs.close();
			ps.close();

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	public void outputTEXT(PrintWriter out,String text) {
		out.println("<!DOCTYPE html>");
		out.println("<HTML>");
		out.println("  <HEAD><meta charset=\"utf-8\"></HEAD>");
		out.println("  <BODY>");
		out.println(text);
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
	}
	public void outputTEXT(PrintWriter out,int number) {
		out.println("<!DOCTYPE html>");
		out.println("<HTML>");
		out.println("  <HEAD><meta charset=\"utf-8\"></HEAD>");
		out.println("  <BODY>");
		out.println(number);
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
	}
}
