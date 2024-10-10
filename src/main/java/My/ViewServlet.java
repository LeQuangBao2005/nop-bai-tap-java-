/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package My;

import com.sun.jdi.connect.spi.Connection;
import java.io.IOException;
import java.sql.ResultSet;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class ViewServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            String kq = "";
            try {

                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                conn = (Connection) DriverManager.getConnection("jdbc:sqlserver://pc341;databaseName=demodb", "sa", "sa");

                ps = conn.PreparedStatement("select * from users");

                rs = ps.executeQuery();

                kq += "<table border=1>";

                kq += "<tr>";
                kq
                        += "<td>Id</td><td>Name</td><td>Password</td><td>Email</td><td>Country</td><td>Edit</td><td>Delete</td >\n";

                kq += "</tr>";
                while (rs.next()) {
                    kq += "<tr>";
                    kq += "<td>" + rs.getInt("id") + "</td><td>"
                            + rs.getString("name") + "</td><td>" + rs.getString(3) + "</td><td>"
                            + rs.getString(4) + "</td><td>" + rs.getString(5) + "</td><td>"
                            + "<a href=EditServlet?id=" + rs.getInt(1) + ">Edit</a>"
                            + "</td><td><a href=DeleteServlet?id=" + rs.getInt(1) + ">Delete</a></td>";
                    kq += "</tr>";
                }
                kq += "</table>";

                conn.close();
            } catch (Exception e) {
                System.out.println("Loi:" + e.toString());
            }

            out.println("<html>");
            out.println("<body>");
            out.println("<a href='index.html'>Add New User</a>");
            out.println("<h1>Users List</h1>");
            out.println(kq);
            out.println("</body>");
            out.println("</html>");
        }
    }
}
