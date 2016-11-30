/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;

/**
 *
 * @author Amiruddin
 */
public class DaftarCuti implements Serializable {
    
    private String tarikhCuti;
    private String kampus;
    private String keterangan;
    private int cutiUmumID;

    /**
     * @return the tarikhCuti
     */
    public String getTarikhCuti() {
        return tarikhCuti;
    }

    /**
     * @param tarikhCuti the tarikhCuti to set
     */
    public void setTarikhCuti(String tarikhCuti) {
        this.tarikhCuti = tarikhCuti;
    }

    /**
     * @return the kampus
     */
    public String getKampus() {
        return kampus;
    }

    /**
     * @param kampus the kampus to set
     */
    public void setKampus(String kampus) {
        this.kampus = kampus;
    }

    /**
     * @return the keterangan
     */
    public String getKeterangan() {
        return keterangan;
    }

    /**
     * @param keterangan the keterangan to set
     */
    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    /**
     * @return the CutiUmumID
     */
    public int getCutiUmumID() {
        return cutiUmumID;
    }

    /**
     * @param cutiUmumID the CutiUmumID to set
     */
    public void setCutiUmumID(int cutiUmumID) {
        this.cutiUmumID = cutiUmumID;
    }
}
