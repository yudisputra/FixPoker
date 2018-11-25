/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientClass;

import ServerClass.*;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import GameClass.Player;

/**
 *
 * @author Putra
 */
public class ClientCode {
    private ServerPokerTable spt;
    public static int port;
    public static String IPaddress;
    public static String nama;
    public Player ClientPlayer;
    public Socket ClientSocket;
    
    public static void main(String args[]) throws IOException{
       ClientCode Ccode = new ClientCode();
       
       Ccode.OpenGUI();
       Ccode.OpenGUIPoker();
    }
    
    //method membuka GUI yang pertama
    public void OpenGUI()
    {
       ClientGUI Cgui = new ClientGUI();
       Cgui.setVisible(true);
       
       //perulangan menunggu button diclick
       boolean statusbutton;
       do
       {
           statusbutton=Cgui.getTombol();
           //bug perlu di system out prinln kalo do while
           System.out.println(statusbutton);
       }while(statusbutton==false);
       
       System.out.println("" +Cgui.getTombol());
       port = Integer.parseInt(Cgui.getTextport());
       IPaddress = Cgui.getTextIP();
       nama = Cgui.getNama();
       
       Cgui.dispose();
    }
    
    
    //membuat koneksi dan membuka GUI PokerTable
    public void OpenGUIPoker() throws IOException
    {
         try{
            this.ClientSocket= new Socket(IPaddress,port);
            DataInputStream dis = new DataInputStream(ClientSocket.getInputStream());
            
            ClientPokerTable Cpt = new ClientPokerTable(port, IPaddress);
            Cpt.setVisible(true);
            //menerima input no 1 yaitu no player client
            String put = dis.readUTF();
            Cpt.tambahText(put);
            
            //inisiasi class player
            this.ClientPlayer=new Player(nama);
            Cpt.tambahText("Nama anda adalah " + ClientPlayer.getNama());
            
            //mengirim data player ke server
            DataOutputStream dos = new DataOutputStream(ClientSocket.getOutputStream());
            dos.writeUTF(ClientPlayer.getNama());
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
