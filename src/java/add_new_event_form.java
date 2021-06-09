import java.io.IOException; 
import java.io.PrintWriter; 
import java.sql.*; 
import java.util.logging.Level; 
import java.util.logging.Logger; 
import javax.servlet.ServletException; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
 
public class add_new_event_form extends HttpServlet { 
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
        PrintWriter out = response.getWriter(); 
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
"        <form name=\"f1\" action=\"add_new_events\" method=\"POST\">\n" +
                "<input type=\"hidden\" name=\"tablename\" value=\""+uname+"\">"+
"            <table >\n" +
"                <tr>\n" +
"                    <td>\n" +
"                        Title: </td><td><input type=\"text\" name=\"title\" oninput=\"titleval()\"></td>\n" +
"                </tr>\n" +
"                <tr><td colspan=\"2\"><div id=\"titlediv\" style=\"color:red;\"> &nbsp;</div></td></tr>\n" +
"                <tr> \n" +
"                    <td>\n" +
"                        Description:</td><td> <input type=\"text\" name=\"des\" oninput=\"desval()\"></td>\n" +
"                </tr>\n" +
"                <tr><td colspan=\"2\"><div id=\"desdiv\" style=\"color:red;\">&nbsp; </div></td></tr>\n" +
"                        <tr> \n" +
"                    <td>\n" +
"                        Expected Start:</td><td> <input type=\"datetime-local\" name=\"start\" oninput=\"startval()\"></td>\n" +
"                </tr>\n" +
"                <tr><td colspan=\"2\"><div id=\"startdiv\" style=\"color:red;\">&nbsp; </div></td></tr>\n" +
"                <tr> \n" +
"                    <td>\n" +
"                        Expected End:</td><td> <input type=\"datetime-local\" name=\"dline\" oninput=\"dlineval()\"></td>\n" +
"                </tr>\n" +
"                <tr><td colspan=\"2\"><div id=\"dlinediv\" style=\"color:red;\">&nbsp; </div></td></tr>\n" +
"                <tr><td colspan=\"2\" >\n" +
"                        <input type=\"submit\" id=\"sub\" class=\"button1\" value=\"Add new module\" disabled=\"disabled\"></td>\n" +
"                </tr>\n" +
"            </table>\n" +
"            </form>\n" +
"             </div>\n" +
"    </center>\n" +
"    </body>\n" +
"</html>\n");
    } 
 
   @Override 
   public void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException  
   { 
          doGet(request, response); 
   } 
}
