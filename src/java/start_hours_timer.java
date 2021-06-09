import java.io.IOException; 
import java.io.PrintWriter; 
import java.sql.*; 
import java.util.logging.Level; 
import java.util.logging.Logger; 
import javax.servlet.ServletException; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
 
public class start_hours_timer extends HttpServlet { 
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
            ResultSet rs = stmt.executeQuery("Select * from "+uname+" where eventid="+eid+"");
            if(rs.next()){
                 String hoursspent=rs.getString("hoursspent");
                 String title=rs.getString("title");
                 String description=rs.getString("description");
                 if(hoursspent.equals("00:00:00"))
                     stmt.executeUpdate("update "+uname+" set startedat=CURRENT_TIMESTAMP where eventid='"+eid+"'");
                 String[] arrSplit = hoursspent.split(":");
                 out.println("<!DOCTYPE html>\n" +
"<html><head>\n" +

"<script>\n" +
"var c = 00;\n" +
"var m=00;\n" +
"var h=00;\n" +
"function myCounter() {\n" +
"c=document.getElementById(\"demo\").innerHTML;\n" +
"m=document.getElementById(\"minute\").innerHTML;\n" +
"h=document.getElementById(\"hour\").innerHTML;\n" +
"++c;\n" +
"if(c%60==0)\n" +
"{\n" +
"if(m%60==59)\n" +
"{\n" +
"++h;\n" +
"h=h > 9 ? \"\" + h: \"0\" + h;\n" +
"m=00;\n" +
"c=00;\n" +
"m=m > 9 ? \"\" + m: \"0\" + m;\n" +
"document.getElementById(\"minute\").innerHTML = m;\n" +
"document.getElementById(\"hour\").innerHTML = h;\n" +
"}\n" +
"else\n" +
"{\n" +
"++m;\n" +
"m=m > 9 ? \"\" + m: \"0\" + m;\n" +
"c=00;\n" +
"document.getElementById(\"minute\").innerHTML = m;\n" +
"}\n" +
"}\n" +
"c=c > 9 ? \"\" + c: \"0\" + c;\n" +
"  document.getElementById(\"demo\").innerHTML = c;\n" +
"}"
+"function funSubmit() {" +
                   "c=document.getElementById(\"demo\").innerHTML;\n" +
"m=document.getElementById(\"minute\").innerHTML;\n" +
"h=document.getElementById(\"hour\").innerHTML;\n" +
                   "var times=h+\":\"+m+\":\"+c;"+
                   "document.mypage.timer.value=times;"+
                   " document.mypage.submit();" +
"}\n" 
+ "</script>" +
"<link rel=\"stylesheet\" href=\"MyJournal_Mainpage_css.css\">"+
"        <title>MyJournal</title>\n" +
"</head> \n" +
"  <body  class=\"bg\" onload=\"myTimer = setInterval(myCounter, 1000)\">\n" +
"    <center>\n" +
"        <span class=\"brmed\"></span>\n" +
"        <img src=\"Title.svg\" width=\"250px\" height=\"80px\">\n" +
"        \n" +
"        <div>\n" +
"         <form  class=\"box\">\n" +
"                <table>\n" +
"                    <tr><td>\n" +
"                        <img src=\"sandclock.svg\" width=\"150px\" height=\"150px\"></td></tr>\n" +
                         
"            <tr><td><font size=+1>"+title+" - "+description+"</font></td></tr>\n" +
"            <tr><td><font size=+2>\n" +
"<span id=\"hour\">"+arrSplit[0]+"</span>:\n" +
"<span id=\"minute\">"+arrSplit[1]+"</span>:\n" +
"<span id=\"demo\">"+arrSplit[2]+"</span></font></td></tr>\n" +
"            <tr><td>Don't Quit now!! Sometimes later becomes never!</td></tr>\n" +
"                    <tr><td><input type=\"button\" class=\"button1\" value=\"Stop Timer\"  onclick=\"clearInterval(myTimer),funSubmit()\"></td></tr>\n" +
"                </table>\n" +
"            </form>\n" +
"        </div>\n" +
"    </center>"+
"</body>\n" +
"</html>");
            }
           out.println("<form action=\"stop_timer\" name=\"mypage\" method=\"POST\">\n" +
"<input type=\"hidden\" name=\"tablename\"  value="+uname+">\n" +
"<input type=\"hidden\" name=\"eventid\"  value=\""+eventid+"\">\n" +
        "<input type=\"hidden\" name=\"timer\"  value=\"nil\">\n" +
"</form>"
        );
          }catch(SQLException ex) 
          { 
              Logger.getLogger(start_hours_timer.class.getName()).log(Level.SEVERE, null, ex); 
          } catch (ClassNotFoundException ex) { 
            Logger.getLogger(start_hours_timer.class.getName()).log(Level.SEVERE, null, ex); 
        } 
    } 
 
   @Override 
   public void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException  
   { 
          doGet(request, response); 
   } 
}
