import java.io.IOException; 
import java.io.PrintWriter; 
import static java.lang.Math.abs;
import java.sql.*; 
import java.util.logging.Level; 
import java.util.logging.Logger; 
import java.text.SimpleDateFormat;  
import javax.servlet.ServletException; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
 
public class summary extends HttpServlet { 
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
"        <form name=\"form1\" action=\"add_new_event\" method=\"POST\">\n" 
                            + "<table width=\"100%\">");
            ResultSet rs = stmt.executeQuery("Select * from "+uname+" where status='completed' order by deadline");
            if(rs.next()){
            do {
            int eid=rs.getInt("eventid");
            String title=rs.getString("title");
            String description=rs.getString("description");
            String hoursspent=rs.getString("hoursspent");
            Timestamp dline=rs.getTimestamp("deadline");
            Timestamp start=rs.getTimestamp("starttime");
            Timestamp starat=rs.getTimestamp("startedat");
            Timestamp compat=rs.getTimestamp("completedat");
             SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm"); 
             String deadline=formatter.format(dline);
             String starttime=formatter.format(start);
             String startedat=formatter.format(starat);
             String completedat=formatter.format(compat);
             String sstatus = null,cstatus=null, performance=null;
             long compdifference = dline.getTime() - compat.getTime();
	       float compdaysBetween = (compdifference / (1000*60*60*24));
               if(compdaysBetween<0)
               {
                   int compgap = (int) compdaysBetween;
                   compgap=abs(compgap);
                   cstatus=compgap+" day(s) After";
               }
               else
               {
                   if(compdaysBetween==0)
                   {
                      cstatus="Right on"; 
                   }
                   else
                   {
                       int compgap = (int) compdaysBetween;
                        compgap=abs(compgap);
                       cstatus=compgap+" day(s) Before";
                   }
               }   
             long startdifference = start.getTime() - starat.getTime();
	       float startdaysBetween = (startdifference / (1000*60*60*24));
                if(startdaysBetween<0)
               {
                   int startgap=(int) startdaysBetween;
                   startgap=abs(startgap);
                   sstatus=startgap+" day(s) After";
                   
               }
               else
               {
                   if(startdaysBetween==0)
                   {
                      sstatus="Right on"; 
                   }
                   else
                   {
                       int startgap=(int) startdaysBetween;
                       startgap=abs(startgap);
                       sstatus=startgap+" day(s) Before";
                   }
               }
                int percentage=0;
               if(compdaysBetween>0)
               {
                   percentage+=34;
               }
               if(startdaysBetween>-5)
               {
                   percentage+=33;
               }
               String[] arrSplit = hoursspent.split(":");
               int hours=Integer.parseInt(arrSplit[0]);
               if(hours>=5)
               {
                   percentage+=33;
               }
               if(percentage==33||percentage==34)
               {
                   performance="Try Even Harder! You'll be on track one day!!";
               }
               if(percentage==66||percentage==67)
               {
                   performance="That's Impressive!!";
               }
               if(percentage==100)
               {
                   performance="Kudos! You did it!!";
               }
               if(percentage==0)
               {
                   performance="Don't lose hope! You can beat the Deadlines with your Consistent Effort!!";
               }
             out.println("<tr><th>"+title+" - "+description+"</th></tr>"
                     + "<tr><td>Expected Time Window: "+starttime+" - "+deadline+"</td></tr>"
                             + "<tr><td>Actual Time Window: "+startedat+" - "+completedat+"</td></tr>"
                                     + "<tr><td>Hours Spent on Work: "+hoursspent+"</td></tr>"
                                             + "<tr><th>Status - Started "+sstatus+" Expected Start Date and Completed "+cstatus+" Expected End Date</th></tr>"
                                                     + "<tr><td><div class=\"outer-line\"><div class=\"inner-line\" style=\"width:"+percentage+"%\"></div></div></td></tr>"
                                                     + "<tr><th>"+performance+"</th></tr>"
                                                             + "<tr><td><hr></td></tr>");
            }while(rs.next());
            }
            else{
                out.println("<h4>No completed Modules <span class=\"space\"></span>Start Working now!! Sometimes later becomes never! </h4>");
            }
out.println("        </table>\n" +
"        </form>\n" +
"    </div>\n" +
"    <br style=\"line-height: 10px; \">\n" +
"    <table width=\"100%\"><tr><td style=\"text-align:right;\" width=\"98%\"><input type=\"button\" class=\"button2\" value=\"Back to Modules\" onclick=\"fnSubmit()\"></td><td></td></tr></table>\n" +
"    </center>\n" +
"</body>\n" +
"</html>");
                out.println("<SCRIPT LANGUAGE=\"JavaScript\">\n" +
"function fnSubmit() {\n" +
" document.mypage.submit();\n" +
"}\n" +
"</SCRIPT>\n" +
"\n" +
"<body>\n" +
"<form action=\"myjournal\" name=\"mypage\" method=\"POST\">\n" +
"<input type=\"hidden\" name=\"tablename\"  value="+uname+">\n" +
"</form>");

          }catch(SQLException ex) 
          { 
              Logger.getLogger(summary.class.getName()).log(Level.SEVERE, null, ex); 
          } catch (ClassNotFoundException ex) { 
            Logger.getLogger(summary.class.getName()).log(Level.SEVERE, null, ex); 
        } 
    } 
         
 
   @Override 
   public void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException  
   { 
          doGet(request, response); 
   } 
}

