import java.io.IOException; 
import java.io.PrintWriter; 
import java.sql.*; 
import java.util.logging.Level; 
import java.util.logging.Logger; 
import javax.servlet.ServletException; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
 
public class new_login extends HttpServlet { 
    @Override 
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        System.out.println("welcome..."); 
        final String JDBC_DRIVER="com.mysql.jdbc.Driver"; 
        final String DB_URL="jdbc:mysql://localhost:3306/myjournal"; 
        final String USER="root"; 
        final String PASS="root"; 
        response.setContentType("text/html;charset=UTF-8"); 
        String uname= request.getParameter("user"); 
        String pass = request.getParameter("pass"); 
        PrintWriter out = response.getWriter(); 
        try{ 
            Class.forName("com.mysql.jdbc.Driver"); 
            Connection conn=DriverManager.getConnection(DB_URL,USER,PASS); 
            Statement stmt=conn.createStatement(); 
            ResultSet result = stmt.executeQuery("Select * from login where loginid='"+uname+"'");
            if (result.next()) { 
            response.sendRedirect("user_taken.html");
            } else {
                stmt.executeUpdate("insert into login values('"+uname+"','"+pass+"')");
                stmt.executeUpdate(" create table "+uname+"(eventid INT AUTO_INCREMENT PRIMARY KEY, title VARCHAR(50), description VARCHAR(100),starttime TIMESTAMP DEFAULT 0, deadline TIMESTAMP DEFAULT 0,startedat TIMESTAMP DEFAULT 0, completedat TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,hoursspent time DEFAULT 0, status varchar(10) default 'pending');");
               response.sendRedirect("signup_success.html");  
            }
          }catch(SQLException ex) 
          { 
              Logger.getLogger(new_login.class.getName()).log(Level.SEVERE, null, ex); 
          } catch (ClassNotFoundException ex) { 
            Logger.getLogger(new_login.class.getName()).log(Level.SEVERE, null, ex); 
        } 
    } 
 
   @Override 
   public void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException  
   { 
          doGet(request, response); 
   } 
}
