/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daftar;

import bean.DaftarCuti;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jdbc.JDBCUtility;

/**
 *
 * @author Amiruddin
 */
@WebServlet(name = "CutiServlet", urlPatterns = {"/DaftarCutiUmum"})
public class CutiServlet extends HttpServlet {
    
    private JDBCUtility jdbcUtility;
    private Connection conn;
    
    public void init() throws ServletException {
        
        String driver = "com.mysql.jdbc.Driver";
        String dbName = "admincuti";
        String url = "jdbc:mysql://localhost/" + dbName + "?";
        String userName = "root";
        String password = "";
        
        jdbcUtility = new JDBCUtility(driver, url, userName, password);
        jdbcUtility.jdbcConnect();
        conn = jdbcUtility.jdbcGetConnection();
        jdbcUtility.prepareSQLStatement();
        
    }    
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
        
        HttpSession session = request.getSession(true);
        ArrayList cutiList = new ArrayList();
        DaftarCuti daftarcuti = null;
        PrintWriter out = response.getWriter();
        if(request.getParameter("kampus") != null ){ //since the input are required already
                                                      // so only one parameter is validate on if
            int kampus = Integer.parseInt(request.getParameter("kampus"));
            String keterangan = request.getParameter("keterangan");
            String tarikhCuti = request.getParameter("tarikhCuti");
            
             try {
                PreparedStatement preparedStatement = jdbcUtility.getPsInsertIntoDaftarCuti();

                preparedStatement.setString(1, tarikhCuti);
                preparedStatement.setInt(2, kampus);
                preparedStatement.setString(3, keterangan);

                preparedStatement.executeUpdate();
                //preparedStatement.clearParameters();

                //PrintWriter out = response.getWriter();

            out.println("<script>");
            out.println("    alert('Cuti insert success');");
            out.println("</script>");
            } catch (SQLException ex) {
                while (ex != null) {
                    System.out.println("SQLState: "
                            + ex.getSQLState());
                    System.out.println("Message:  "
                            + ex.getMessage());
                    System.out.println("Vendor:   "
                            + ex.getErrorCode());
                    ex = ex.getNextException();
                    System.out.println("");
                }

                System.out.println("Connection to the database error");
                
            } catch (java.lang.Exception ex) {
                ex.printStackTrace();
            }
        }
        // Search cuti Umum in Daftar Cuti
        if(request.getParameter("tahunCapai") != null ){
            String tahunCapai = request.getParameter("tahunCapai");
            int kampusCapai = Integer.parseInt(request.getParameter("kampusCapai"));
            
            try {
                PreparedStatement preparedStatement = jdbcUtility.getPsSelectAllFromDaftarCutiWhereKampus();

                preparedStatement.setInt(1, kampusCapai);
                preparedStatement.setString(2, tahunCapai);
                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    daftarcuti = new DaftarCuti();
                    daftarcuti.setKampus(rs.getString("kampus"));
                    daftarcuti.setKeterangan(rs.getString("keterangan"));
                    daftarcuti.setCutiUmumID(rs.getInt("cutiUmumID"));
                    String tarikhCuti = rs.getString("tarikhCuti");

                    //convert tarikhcuti string to date
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = new Date();
                    try {
                        date = formatter.parse(tarikhCuti);
                    } catch (Exception ex) {
                    }

                    //convert mysql date to MY date
                    formatter = new SimpleDateFormat("dd MMMM, yyyy");
                    tarikhCuti = formatter.format(date);
                    daftarcuti.setTarikhCuti(tarikhCuti);

                    cutiList.add(daftarcuti);
                    
                }
            } 
            catch (SQLException ex) {
            }
        }else{
            try {
                ResultSet rs = jdbcUtility.getPsSelectAllFromDaftarCuti().executeQuery();

                while (rs.next()) {
                    daftarcuti = new DaftarCuti();
                    daftarcuti.setKampus(rs.getString("kampus"));
                    daftarcuti.setKeterangan(rs.getString("keterangan"));
                    daftarcuti.setCutiUmumID(rs.getInt("cutiUmumID"));
                    String tarikhCuti = rs.getString("tarikhCuti");

                    //convert tarikhcuti string to date
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = new Date();
                    try {
                        date = formatter.parse(tarikhCuti);
                    } catch (Exception ex) {
                    }

                    //convert mysql date to MY date
                    formatter = new SimpleDateFormat("dd MMMM, yyyy");
                    tarikhCuti = formatter.format(date);
                    daftarcuti.setTarikhCuti(tarikhCuti);

                    cutiList.add(daftarcuti);
                }
            } catch (SQLException ex) {
            }
        }
      
        session.setAttribute("cutiList", cutiList);
        sendPage(request, response, "/DaftarCuti.jsp");
    }
    void sendPage(HttpServletRequest req, HttpServletResponse res, String fileName) throws ServletException, IOException
    {
        // Get the dispatcher; it gets the main page to the user
	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(fileName);

	if (dispatcher == null)
	{
            System.out.println("There was no dispatcher");
	    // No dispatcher means the html file could not be found.
	    res.sendError(res.SC_NO_CONTENT);
	}
	else
	    dispatcher.forward(req, res);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
