/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daftar;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jdbc.JDBCUtility;

/**
 *
 * @author Amiruddin
 */
@WebServlet(name = "PadamCutiUmum", urlPatterns = {"/PadamCuti"})
public class PadamCuti extends HttpServlet {
    
    private JDBCUtility jdbcUtility;
    private Connection con;
    
    public void init() throws ServletException
    {
        String driver = "com.mysql.jdbc.Driver";

        String dbName = "admincuti";
        String url = "jdbc:mysql://localhost/" + dbName + "?";
        String userName = "root";
        String password = "";

        jdbcUtility = new JDBCUtility(driver,
                                      url,
                                      userName,
                                      password);

        jdbcUtility.jdbcConnect();
        con = jdbcUtility.jdbcGetConnection();
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
        response.setContentType("text/html;charset=UTF-8");
        
        if (request.getParameter("cutiUmumID") != null) {
            
            int cutiUmumID = Integer.parseInt(request.getParameter("cutiUmumID"));

            try {                    
                PreparedStatement preparedStatement = jdbcUtility.getPsDeleteFromDaftarCutiWhereID();
                preparedStatement.setInt(1, cutiUmumID);
                preparedStatement.executeUpdate();

                //PrintWriter out = response.getWriter();
                //out.println("Delete successfull");
                sendPage(request, response, "/DaftarCutiUmum");
            }
            catch (SQLException ex)
            {
                while (ex != null)
                {
                    System.out.println ("SQLState: " +
                                     ex.getSQLState ());
                    System.out.println ("Message:  " +
                                     ex.getMessage ());
                    System.out.println ("Vendor:   " +
                                     ex.getErrorCode ());
                    ex = ex.getNextException ();
                          System.out.println ("");
                }

                System.out.println("Connection to the database error");
            }
            catch (java.lang.Exception ex)
            {
                ex.printStackTrace ();
            } 
        } else {
            
            int id = Integer.parseInt(request.getParameter("id"));

            try {                    
                PreparedStatement preparedStatement = jdbcUtility.getPsDeleteFromMohonCutiUmumWhereID();
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();

                //PrintWriter out = response.getWriter();
                //out.println("Delete successfull");
                sendPage(request, response, "/HapusCutiServlet");
            }
            catch (SQLException ex)
            {
                while (ex != null)
                {
                    System.out.println ("SQLState: " +
                                     ex.getSQLState ());
                    System.out.println ("Message:  " +
                                     ex.getMessage ());
                    System.out.println ("Vendor:   " +
                                     ex.getErrorCode ());
                    ex = ex.getNextException ();
                          System.out.println ("");
                }

                System.out.println("Connection to the database error");
            }
            catch (java.lang.Exception ex)
            {
                ex.printStackTrace ();
            }
        }
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
