/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package My;

import com.sun.jdi.connect.spi.Connection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class DeleteServlet extends HttpServlet {

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
             String id = request.getParameter("id");
             Connection conn = null;
            PreparedStatement ps = null;
             ResultSet rs=null;
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                
                conn = (Connection) DriverManager.getConnection("jdbc:sqlserver://pc341;databaseName=demodb", "sa", "sa");
                
                ps = conn.PreparedStatement("Select * from users");
                rs = ps.executeQuery();

                int kq = ps.executeUpdate();

                if (kq > 0) {
                    out.println("<h2>Xoá user thành công</h2>");
                } else {
                    out.println("<h2>Xoá user thất bại</h2>");
                }

                conn.close();
            } catch (Exception e) {
                System.out.println("Loi:" + e.toString());
                out.println("<h2>Xoá user thất bại</h2>");
            }

            request.getRequestDispatcher("ViewServlet").include(request, response);
        }
    }

}
