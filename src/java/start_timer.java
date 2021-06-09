import java.io.IOException; 
import java.io.PrintWriter; 
import java.sql.*; 
import java.util.logging.Level; 
import java.util.logging.Logger; 
import javax.servlet.ServletException; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
 
public class start_timer extends HttpServlet { 
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
            ResultSet rs = stmt.executeQuery("Select * from "+uname+" where status='pending' order by deadline");
            if(rs.next()){
                 String hoursspent=rs.getString("hoursspent");
                 String[] arrSplit = hoursspent.split(":");
                 out.println("<!DOCTYPE html>\n" +
"<html>\n" +
"<body onload=\"myTimer = setInterval(myCounter, 1000);\">\n" +
"    <button id=\"ststop\" onClick=\"buttoncheck()\">Stop Timer</button>\n" +
"<span id=\"hour\">"+arrSplit[0]+"</span>:\n" +
"<span id=\"minute\">"+arrSplit[1]+"</span>:\n" +
"<span id=\"demo\">"+arrSplit[2]+"</span>\n" +
"<script>\n" +
"    function buttoncheck()\n" +
"    {\n" +
"        name=document.getElementById(\"ststop\").innerHTML;\n" +
"        if(name==\"Start Timer\")\n" +
"        {\n" +
"            document.getElementById(\"ststop\").innerHTML=\"Pause Timer\";\n" +
"            myTimer = setInterval(myCounter, 1000);\n" +
"        }\n" +
"        else\n" +
"        {\n" +
"            document.getElementById(\"ststop\").innerHTML=\"Start Timer\";\n" +
"            clearInterval(myTimer);\n" +
"        }\n" +
"    }\n" +
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
"}\n" +
"</script> \n" +
"\n" +
"</body>\n" +
"</html>");
            }
//            out.println("<SCRIPT LANGUAGE=\"JavaScript\">\n" +
//"function fnSubmit() {\n" +
//" document.mypage.submit();\n" +
//"}\n" +
//"</SCRIPT>\n" +
//"\n" +
//"<body onload=\"fnSubmit()\">\n" +
//"<form action=\"myjournal\" name=\"mypage\" method=\"POST\">\n" +
//"<input type=\"hidden\" name=\"tablename\"  value="+uname+">\n" +
//"<input type=\"submit\">\n" +
//"</form>");
          }catch(SQLException ex) 
          { 
              Logger.getLogger(start_timer.class.getName()).log(Level.SEVERE, null, ex); 
          } catch (ClassNotFoundException ex) { 
            Logger.getLogger(start_timer.class.getName()).log(Level.SEVERE, null, ex); 
        } 
    } 
 
   @Override 
   public void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException  
   { 
          doGet(request, response); 
   } 
}
