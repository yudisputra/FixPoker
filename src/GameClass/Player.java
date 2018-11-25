/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameClass;

/**
 *
 * @author Putra
 */
public class Player {
    private String nama;
    private int Chip;

    public Player(String nama) {
        this.nama = nama;
        this.Chip=1000;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getChip() {
        return Chip;
    }

    public void setChip(int Chip) {
        this.Chip = Chip;
    }
    
    
    
}
