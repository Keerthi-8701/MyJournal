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
 
public class myjournal extends HttpServlet { 
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
        try{ 
            Class.forName("com.mysql.jdbc.Driver"); 
            Connection conn=DriverManager.getConnection(DB_URL,USER,PASS); 
            Statement stmt=conn.createStatement(); 
            
                
            out.println("<html>\n" +
"    <head>\n" +
"        <title>MyJournal</title>\n" +
                    "<script src=\"https://kit.fontawesome.com/a076d05399.js\" crossorigin=\"anonymous\"></script>"+
"        <link rel=\"stylesheet\" href=\"maincontents_css.css\">\n" +
"</head>\n" +
"<body class=\"bg\">\n" +
"    <table cellspacing=\"0\" width=\"100%\"><tr><td width=\"2%\">&nbsp;</td><td width=\"85%\"><img src=\"Title.svg\" width=\"200px\" height=\"65px\"></td>"
                    +"<td><div class=\"dropdown\" style=\"float:right;\">\n" +
"  <button class=\"dropbtn\"><i class=\"fas fa-user\" ></i> &nbsp;"+uname+"</button>\n" +
"  <div class=\"dropdown-content\">\n" +
"  <a href=\"Loginpage.html\">Logout</a>\n" +
        "  <a href=\"changepassword.html\">Change Password</a>\n" +
"  </div>\n" +
"</div></td>"
                    + "<td width=\"2%\">&nbsp;</td></tr></table>\n" +
"<center>\n" +
"    <div class=\"box\" >\n" +
"        <form name=\"form1\" action=\"add_new_event\" method=\"POST\">\n" +
                    "<input type=\"hidden\" name=\"tablename\" value=\""+uname+"\">");
            ResultSet rs = stmt.executeQuery("Select * from "+uname+" where status='pending' order by deadline");
            if(rs.next()){
             out.println("        <table cellspacing=\"0\" width=\"100%\">\n"+
            "<tr  align=\"left\" ><th width=\"5px\">&nbsp;</th>\n" +
        "<th width=\"10%\">Expected Start</th>\n" +
"                <th width=\"10%\">Expected End</th>\n" +
"                <th width=\"50%\">Title and Description</th>\n" +
        "<th width=\"40px\" >Hours Spent</th>\n" +
        "<th width=\"30px\">&nbsp;</th>"+
        "<th width=\"30px\">&nbsp;</th></tr>"+
"            <tr><td colspan=\"7\"><hr></td></tr></table>"
                            + "<table width=\"100%\">");
            do {
            int eid=rs.getInt("eventid");
            String title=rs.getString("title");
            String description=rs.getString("description");
            String hoursspent=rs.getString("hoursspent");
            Timestamp dline=rs.getTimestamp("deadline");
            Timestamp start=rs.getTimestamp("starttime");
             SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm"); 
             String deadline=formatter.format(dline);
             String starttime=formatter.format(start);
out.println("<tr><td width=\"3px\"><input type=\"checkbox\" id=\""+eid+"\" onclick=\"chkidpicker(this.id)\"></td>\n" +
        "<td width=\"10%\">"+starttime+"</td>\n" +
"                <td width=\"10%\">"+deadline+"</td>\n" +
"                <td width=\"50%\" >"+title+"<br>"+description+"</td>\n" +
        "<td width=\"40px\" >"+hoursspent+"</td>\n" +
                "<td width=\"30px\"> <input type=\"button\" class=\"button1\"  id=\""+eid+"\" value=\"Start Timer\" onclick=\"timeridpicker(this.id)\"></td>"+
        "<td width=\"30px\"><input type=\"button\" class=\"button1\"  id=\""+eid+"\" value=\"Modify\" onclick=\"modidpicker(this.id)\" ></td></tr>"+
        
"            <tr><td colspan=\"7\"><hr></td></tr>\n" );
            }while(rs.next());
            }
            else{
                out.println("<h4>Yayyy!! You are winning!!! No more pending events!<span class=\"space\"></span>Click \"+\" Button to add new events!!</h4>");
            }
out.println("        </table>\n" +
"        </form>\n" +
"    </div>\n" +
"    <br style=\"line-height: 10px; \">\n" +
"    <table  width=\"100%\"><tr><td width=\"2%\"></td><td><input type=\"button\" class=\"button2\" value=\"View Summary\" onclick=\"fnSubmit()\"></td><td width=\"98%\"><div class=\"btn\" onclick=\"document.form1.submit();\">+</div></td><td>&nbsp;</td></tr></table>\n" +
"    </center>\n" +
"</body>\n" +
"</html>");
out.println(
        "<SCRIPT LANGUAGE=\"JavaScript\">\n"+
        "function chkidpicker(no){" +
        "if(confirm(\"Clicking on OK indicates your event as Completed. \"))"
+ "{document.chkbox.eventid.value=no;"
        + " document.chkbox.submit();}"+
        "else{document.getElementById(no).checked=false;}"
        + "}"+
"</SCRIPT>\n" +
"<body>\n" +
"<form action=\"event_complete\" name=\"chkbox\" method=\"POST\">\n" +
"<input type=\"hidden\" name=\"tablename\"  value="+uname+">\n" +
"<input type=\"hidden\" name=\"eventid\"  value=\"nil\">\n" +
"</form>");
out.println("<SCRIPT LANGUAGE=\"JavaScript\">\n" +
"function timeridpicker(no){" +
        "document.starttimerform.eventid.value=no;"
        + " document.starttimerform.submit();"
        + "}"+
"</SCRIPT>\n" +
"<body>\n" +
"<form action=\"start_hours_timer\" name=\"starttimerform\" method=\"POST\">\n" +
"<input type=\"hidden\" name=\"tablename\"  value="+uname+">\n" +
"<input type=\"hidden\" name=\"eventid\"  value=\"nil\">\n" +
"</form>");
out.println("<SCRIPT LANGUAGE=\"JavaScript\">\n" +
        "function modidpicker(no){document.modform.eventid.value=no; document.modform.submit();}"+
"</SCRIPT>\n" +
"\n" +
"<body>\n" +
"<form action=\"modify_event_form\" name=\"modform\" method=\"POST\">\n" +
"<input type=\"hidden\" name=\"tablename\"  value="+uname+">\n" +
"<input type=\"hidden\" name=\"eventid\"  value=\"nil\">\n" +
"</form>");
 out.println("<SCRIPT LANGUAGE=\"JavaScript\">\n" +
"function fnSubmit() {\n" +
" document.mypage.submit();\n" +
"}\n" +
"</SCRIPT>\n" +
"\n" +
"<body>\n" +
"<form action=\"summary\" name=\"mypage\" method=\"POST\">\n" +
"<input type=\"hidden\" name=\"tablename\"  value="+uname+">\n" +
"</form>");
            
          }catch(SQLException ex) 
          { 
              Logger.getLogger(myjournal.class.getName()).log(Level.SEVERE, null, ex); 
          } catch (ClassNotFoundException ex) { 
            Logger.getLogger(myjournal.class.getName()).log(Level.SEVERE, null, ex); 
        } 
    } 
         
 
   @Override 
   public void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException  
   { 
          doGet(request, response); 
   } 
}

