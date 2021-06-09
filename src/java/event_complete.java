import java.io.IOException; 
import java.io.PrintWriter; 
import java.sql.*; 
import java.util.logging.Level; 
import java.util.logging.Logger; 
import javax.servlet.ServletException; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
 
public class event_complete extends HttpServlet { 
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
        String eventid = request.getParameter("eventid");
        int eid=Integer.parseInt(eventid);
        PrintWriter out = response.getWriter(); 
        try{ 
            Class.forName("com.mysql.jdbc.Driver"); 
            Connection conn=DriverManager.getConnection(DB_URL,USER,PASS); 
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery("select * from "+uname+" where eventid='"+eid+"'");
            if(rs.next())
            {
                String hoursspent=rs.getString("hoursspent");
                if(hoursspent.equals("00:00:00"))
                {
                    out.println("<html>\n" +
"    <head>\n" +
"        <title>MyJournal</title>\n" +
"        <meta charset=\"UTF-8\">\n" +
"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"        <link rel=\"stylesheet\" href=\"MyJournal_Mainpage_css.css\">\n" +
"    </head>\n" +
"    <body  class=\"bg\">\n" +
"    <center>\n" +
"        <span class=\"brmed\"></span>\n" +
"        <img src=\"Title.svg\" width=\"250px\" height=\"80px\">\n" +
"        \n" +
"        <div>\n" +
"         <form  class=\"box\">\n" +
"                <table>\n" +
"                    <tr><td>\n" +
"                        <img src=\"shoot.svg\" width=\"100px\" height=\"100px\"></td></tr>\n" +
"            <tr><td><font size=+2>Oh shoot!!!</font></td></tr>\n" +
"            <tr><td>Seems like you haven't worked on the Module!!</td></tr>\n" +
"                    <tr><td><input type=\"button\" class=\"button1\" value=\"Log Progress Details\"  onclick=\"detSubmit()\"></td></tr>\n" +
"                    <tr><td><a href=\"#\" onclick=\"document.mpg.submit();\" >Start Working on the Module</a></td></tr>\n" +
"                </table>\n" +
"            </form>\n" +
"        </div>\n" +
"    </center>\n" +
"    </body>\n" +
"</html>\n" +
"");
    out.println(
"<body >\n" +
"<form action=\"myjournal\" id=\"f\" name=\"mpg\" method=\"POST\">\n" +
"<input type=\"hidden\" name=\"tablename\"  value="+uname+">\n" +
"</form>");                
                    
                }
            else{
            stmt.executeUpdate("update "+uname+" set status='completed' where eventid='"+eid+"'"); 
            out.println("<SCRIPT LANGUAGE=\"JavaScript\">\n" +
"function fnSubmit() {\n" +
" document.mypage.submit();\n" +
"}\n" +
"</SCRIPT>\n" +
"\n" +
"<body onload=fnSubmit()>\n" +
"<form action=\"myjournal\" id=\"f\" name=\"mypage\" method=\"POST\">\n" +
"<input type=\"hidden\" name=\"tablename\"  value="+uname+">\n" +
"</form>");
            }
             out.println("<SCRIPT LANGUAGE=\"JavaScript\">\n" +
"function detSubmit() {\n" +
" document.apage.submit();\n" +
"}\n" +
"</SCRIPT>\n" +
"\n" +
"<body>\n" +
"<form action=\"add_previous_work_form\" name=\"apage\" method=\"POST\">\n" +
"<input type=\"hidden\" name=\"tablename\"  value="+uname+">\n" +
"<input type=\"hidden\" name=\"eventid\"  value=\""+eventid+"\">\n" +
"</form>");
            }
          }catch(SQLException ex) 
          { 
              Logger.getLogger(event_complete.class.getName()).log(Level.SEVERE, null, ex); 
          } catch (ClassNotFoundException ex) { 
            Logger.getLogger(event_complete.class.getName()).log(Level.SEVERE, null, ex); 
        } 
    } 
 
   @Override 
   public void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException  
   { 
          doGet(request, response); 
   } 
}
