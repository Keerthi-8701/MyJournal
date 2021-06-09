import java.io.IOException; 
import java.io.PrintWriter; 
import java.sql.*; 
import java.util.logging.Level; 
import java.util.logging.Logger; 
import java.text.SimpleDateFormat;  
import javax.servlet.ServletException; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
 
public class add_new_events extends HttpServlet { 
    @Override 
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        System.out.println("welcome..."); 
        final String JDBC_DRIVER="com.mysql.jdbc.Driver"; 
        final String DB_URL="jdbc:mysql://localhost:3306/myjournal"; 
        final String USER="root"; 
        final String PASS="root"; 
        response.setContentType("text/html;charset=UTF-8"); 
        String uname= request.getParameter("tablename"); 
        String title = request.getParameter("title");
        String description = request.getParameter("des");
        String s1=request.getParameter("dline");
        s1=s1.replace('T',' ');
        s1=s1+":00";
        String s2=request.getParameter("start");
        s2=s2.replace('T',' ');
        s2=s2+":00";
        PrintWriter out = response.getWriter(); 
        try{ 
            Class.forName("com.mysql.jdbc.Driver"); 
            Connection conn=DriverManager.getConnection(DB_URL,USER,PASS); 
            Statement stmt=conn.createStatement(); 
            stmt.executeUpdate(" insert into "+uname+"(title, description,starttime, deadline) values('"+title+"','"+description+"','"+s2+"','"+s1+"');");
                out.println("<SCRIPT LANGUAGE=\"JavaScript\">\n" +
"function fnSubmit() {\n" +
" document.mypage.submit();\n" +
"}\n" +
"</SCRIPT>\n" +
"\n" +
"<body onload=\"fnSubmit()\">\n" +
"<form action=\"myjournal\" name=\"mypage\" method=\"POST\">\n" +
"<input type=\"hidden\" name=\"tablename\"  value="+uname+">\n" +
"</form>");
            
            
          }catch(SQLException ex) 
          { 
              Logger.getLogger(add_new_events.class.getName()).log(Level.SEVERE, null, ex); 
          } catch (ClassNotFoundException ex) { 
            Logger.getLogger(add_new_events.class.getName()).log(Level.SEVERE, null, ex); 
        } 
    } 
 
   @Override 
   public void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException  
   { 
          doGet(request, response); 
   } 
}
