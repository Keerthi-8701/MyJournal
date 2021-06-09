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
 
public class modify_event_form extends HttpServlet { 
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
            if(rs.next())
            {
            String title=rs.getString("title");
            String description=rs.getString("description");
            Timestamp dline=rs.getTimestamp("deadline");
            Timestamp start=rs.getTimestamp("starttime");
             SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
             String deadline=formatter.format(dline);
             deadline=deadline.replace(' ','T');
             String starttime=formatter.format(start);
             starttime=starttime.replace(' ','T');
             out.println("<html>\n" +
"    <head>\n" +
"        <title>MyJournal</title>\n" +
"        <meta charset=\"UTF-8\">\n" +
"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"         <link rel=\"stylesheet\" href=\"mainpage_css.css\">\n" +
"         \n" +
"         <script>\n" +
"    function titleval()\n" +
"    {\n" +
"        title=document.f1.title.value;\n" +
"        if(title==\"\")\n" +
"        {\n" +
"           document.getElementById(\"titlediv\").innerHTML=\"Enter a Title for the event!\";\n" +
"              document.getElementById(\"sub\").disabled=true;\n" +
"        }\n" +
"        else\n" +
"        {\n" +
"            document.getElementById(\"titlediv\").innerHTML=\"&nbsp;\";\n" +
"        des=document.f1.des.value;\n" +
"        dline=document.f1.dline.value;\n" +
                "start=document.f1.start.value;\n"+
"           if(des!=\"\" && dline!=\"\" && start!=\"\")\n" +
"           {\n" +
"              document.getElementById(\"sub\").disabled=false;\n" +
"          }\n" +
"        }\n" +
"    }\n" +
"    function desval()\n" +
"    {\n" +
"        des=document.f1.des.value;\n" +
"        if(des==\"\")\n" +
"        {\n" +
"           document.getElementById(\"desdiv\").innerHTML=\"Enter Description of event!\";\n" +
"              document.getElementById(\"sub\").disabled=true;\n" +
"        }\n" +
"        else\n" +
"        {\n" +
"            document.getElementById(\"desdiv\").innerHTML=\"&nbsp;\";\n" +
"        title=document.f1.title.value;\n" +
"        dline=document.f1.dline.value;\n" +
                "start=document.f1.start.value;"+
"           if(title!=\"\" && dline!=\"\" && start!=\"\")\n" +
"           {\n" +
"              document.getElementById(\"sub\").disabled=false;\n" +
"          }\n" +
"        }\n" +
"    }\n" +
"    function dlineval()\n" +
"    {\n" +
"        dline=document.f1.dline.value;\n" +
"        if(dline==\"\")\n" +
"        {\n" +
"           document.getElementById(\"dlinediv\").innerHTML=\"Enter an expected end time!\";\n" +
"              document.getElementById(\"sub\").disabled=true;\n" +
"        }\n" +
"        else\n" +
"        {\n" +
"            document.getElementById(\"dlinediv\").innerHTML=\"&nbsp;\";\n" +
"        des=document.f1.des.value;\n" +
"        title=document.f1.title.value;\n" +
                "start=document.f1.start.value;"+
"           if(des!=\"\" && title!=\"\" && start!=\"\")\n" +
"           {\n" +
"              document.getElementById(\"sub\").disabled=false;\n" +
"          }\n" +
"        }\n" +
"    }\n" +
          " function startval()\n" +
"    {\n" +
"        start=document.f1.start.value;\n" +
"        if(start==\"\")\n" +
"        {\n" +
"           document.getElementById(\"startdiv\").innerHTML=\"Enter an expected start time!\";\n" +
"              document.getElementById(\"sub\").disabled=true;\n" +
"        }\n" +
"        else\n" +
"        {\n" +
"            document.getElementById(\"startdiv\").innerHTML=\"&nbsp;\";\n" +
"        des=document.f1.des.value;\n" +
"        title=document.f1.title.value;\n" +
                "dline=document.f1.dline.value;"+
"           if(des!=\"\" && title!=\"\" && dline!=\"\")\n" +
"           {\n" +
"              document.getElementById(\"sub\").disabled=false;\n" +
"          }\n" +
"        }\n" +
"    }\n" +     
"         </script>\n" +
"    </head>\n" +
"    <body class=\"bg\">\n" +
"        <center>\n" +
"        <span class=\"brmed\"></span>\n" +
"        <img src=\"Title.svg\" width=\"250px\" height=\"80px\">\n" +
"        \n" +
"         <div class=\"box\" >\n" +
"        <form name=\"f1\" action=\"modify_event\" method=\"POST\">\n" +
                "<input type=\"hidden\" name=\"tablename\" value=\""+uname+"\">"+
                        "<input type=\"hidden\" name=\"eventid\"  value=\""+eventid+"\">\n" +
"            <table >\n" +
"                <tr>\n" +
"                    <td>\n" +
"                        Title: </td><td><input type=\"text\" name=\"title\" value=\""+title+"\"oninput=\"titleval()\"></td>\n" +
"                </tr>\n" +
"                <tr><td colspan=\"2\"><div id=\"titlediv\" style=\"color:red;\"> &nbsp;</div></td></tr>\n" +
"                <tr> \n" +
"                    <td>\n" +
"                        Description:</td><td> <input type=\"text\" name=\"des\" value=\""+description+"\"oninput=\"desval()\"></td>\n" +
"                </tr>\n" +
"                <tr><td colspan=\"2\"><div id=\"desdiv\" style=\"color:red;\">&nbsp; </div></td></tr>\n" +
"                        <tr> \n" +
"                    <td>\n" +
"                        Expected Start:</td><td> <input type=\"datetime-local\" name=\"start\" value=\""+starttime+"\" oninput=\"startval()\"></td>\n" +
"                </tr>\n" +
"                <tr><td colspan=\"2\"><div id=\"startdiv\" style=\"color:red;\">&nbsp; </div></td></tr>\n" +
"                <tr> \n" +
"                    <td>\n" +
"                        Expected End:</td><td> <input type=\"datetime-local\" name=\"dline\" value=\""+deadline+"\"oninput=\"dlineval()\"></td>\n" +
"                </tr>\n" +
"                <tr><td colspan=\"2\"><div id=\"dlinediv\" style=\"color:red;\">&nbsp; </div></td></tr>\n" +
"                <tr><td colspan=\"2\" >\n" +
"                        <input type=\"submit\" id=\"sub\" class=\"button1\" value=\"Modify module\"></td>\n" +
"                </tr>\n" +
"            </table>\n" +
"            </form>\n" +
"             </div>\n" +
"    </center>\n" +
"    </body>\n" +
"</html>\n");
            }
            }catch(SQLException ex) 
          { 
              Logger.getLogger(modify_event_form.class.getName()).log(Level.SEVERE, null, ex); 
          } catch (ClassNotFoundException ex) { 
            Logger.getLogger(modify_event_form.class.getName()).log(Level.SEVERE, null, ex); 
        } 
    } 
         
 
   @Override 
   public void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException  
   { 
          doGet(request, response); 
   } 
}

