/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daftar;

import bean.MohonCuti;
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
@WebServlet(name = "HapusCutiServlet", urlPatterns = {"/HapusCutiServlet"})
public class HapusCutiServlet extends HttpServlet {
    
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
        
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        ArrayList mohonList = new ArrayList();
        MohonCuti mohoncuti = null;
        
        if(request.getParameter("capaiID") != null) { //Search Permohonan Cuti in Hapus Cuti
            
            int capaiTahun = Integer.parseInt(request.getParameter("capaiTahun"));
            int capaiID = Integer.parseInt(request.getParameter("capaiID"));
            out.println(capaiTahun);
            out.println(capaiID);
            
            try {
                PreparedStatement preparedStatement = jdbcUtility.getPsSelectAllFromMohonCutiUmumWhereID();
                
                preparedStatement.setInt(1, capaiTahun);
                preparedStatement.setInt(2, capaiID);
                
                ResultSet rs = preparedStatement.executeQuery();
                
                while (rs.next()) {
                        mohoncuti = new MohonCuti();
                        mohoncuti.setId(rs.getInt("id"));
    //                    mohoncuti.setAlamatCuti(rs.getString("alamatCuti"));
                        mohoncuti.setBilanganCuti(rs.getInt("bilanganCuti"));
                        mohoncuti.setCatatan(rs.getString("catatan"));
                        mohoncuti.setStatus(rs.getInt("status"));
    //                    mohoncuti.setId_sokonglulus(rs.getInt("id_sokonglulus"));
    //                    mohoncuti.setSebabTidakSokong(rs.getString("sebabTidakSokong"));
    //                    mohoncuti.setSebabTidakLulus(rs.getString("sebabTidakLulus"));

                        String tarikhMula = rs.getString("tarikhMula");
                        String tarikhTamat = rs.getString("tarikhTamat");
                        String tarikhMohon = rs.getString("tarikhMohon");


                        //convert tarikh string to date
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        Date date1 = new Date();
                        Date date2 = new Date();
                        Date date3 = new Date();
                        try {
                            date1 = formatter.parse(tarikhMohon);
                            date2 = formatter.parse(tarikhMula);
                            date3 = formatter.parse(tarikhTamat);
                        } catch (Exception ex) {
                        }

                        //convert mysql date to MY date
                        formatter = new SimpleDateFormat("dd-MM-yyyy");
                        tarikhMohon = formatter.format(date1);
                        tarikhMula = formatter.format(date2);
                        tarikhTamat = formatter.format(date3);

                        mohoncuti.setTarikhMohon(tarikhMohon);
                        mohoncuti.setTarikhMula(tarikhMula);
                        mohoncuti.setTarikhTamat(tarikhTamat);

                        mohonList.add(mohoncuti);
                }
            } catch (SQLException ex) {
              }
            
        } else {
            
            try {
                    ResultSet rs = jdbcUtility.getPsSelectAllFromMohonCutiUmum().executeQuery();

                    while (rs.next()) {
                        mohoncuti = new MohonCuti();
                        mohoncuti.setId(rs.getInt("id"));
    //                    mohoncuti.setAlamatCuti(rs.getString("alamatCuti"));
                        mohoncuti.setBilanganCuti(rs.getInt("bilanganCuti"));
                        mohoncuti.setCatatan(rs.getString("catatan"));
                        mohoncuti.setStatus(rs.getInt("status"));
    //                    mohoncuti.setId_sokonglulus(rs.getInt("id_sokonglulus"));
    //                    mohoncuti.setSebabTidakSokong(rs.getString("sebabTidakSokong"));
    //                    mohoncuti.setSebabTidakLulus(rs.getString("sebabTidakLulus"));

                        String tarikhMula = rs.getString("tarikhMula");
                        String tarikhTamat = rs.getString("tarikhTamat");
                        String tarikhMohon = rs.getString("tarikhMohon");


                        //convert tarikh string to date
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        Date date1 = new Date();
                        Date date2 = new Date();
                        Date date3 = new Date();
                        try {
                            date1 = formatter.parse(tarikhMohon);
                            date2 = formatter.parse(tarikhMula);
                            date3 = formatter.parse(tarikhTamat);
                        } catch (Exception ex) {
                        }

                        //convert mysql date to MY date
                        formatter = new SimpleDateFormat("dd-MM-yyyy");
                        tarikhMohon = formatter.format(date1);
                        tarikhMula = formatter.format(date2);
                        tarikhTamat = formatter.format(date3);

                        mohoncuti.setTarikhMohon(tarikhMohon);
                        mohoncuti.setTarikhMula(tarikhMula);
                        mohoncuti.setTarikhTamat(tarikhTamat);

                        mohonList.add(mohoncuti);

                    }
                    session.setAttribute("mohonList", mohonList);
                } catch (SQLException ex) {
                  }

        }
            sendPage(request, response, "/HapusCuti.jsp");
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
