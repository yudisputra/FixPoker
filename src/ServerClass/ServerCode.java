/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerClass;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Putra
 */
public class ServerCode {
    private ServerPokerTable spt;
    public static int port;
    
    public static void main(String args[]) throws IOException{
       ServerCode Scode = new ServerCode();
       
       Scode.OpenGUI();
       Scode.OpenGUIPoker();
    }
    
    //method membuka GUI yang pertama
    public void OpenGUI()
    {
       ServerGUI Sgui = new ServerGUI();
       Sgui.setVisible(true);
       
       //perulangan menunggu button diclick
       boolean statusbutton;
       do
       {
           statusbutton=Sgui.getTombol();
           //bug perlu di system out prinln kalo do while
           System.out.println(statusbutton);
       }while(statusbutton==false);
       
       System.out.println("" +Sgui.getTombol());
       port = Integer.parseInt(Sgui.getText());
       
       Sgui.dispose();
    }
    
    
    //membuat koneksi dan membuka GUI PokerTable
    public void OpenGUIPoker() throws IOException
    {
        //inisiasi form pokertable
       ServerPokerTable Spt = new ServerPokerTable(port);
       //membuat server pokertable
       ServerSocket ss = new ServerSocket(1236);
       
       Spt.setVisible(true);
        boolean keadaan = true;
        int urut=1;
        while (keadaan){
            new server(ss.accept(),urut).start();
            Spt.tambahText("Client ke - " + urut + " memasuki game poker");
            urut++;
        }
    }
}

//class untuk server
class server extends Thread{
        static Socket sc = null;
        int angka = 0;
        server(Socket a, int angka){
            this.angka=angka;
            this.sc=a;
        }
        public void run()
        {
            try {
                DataOutputStream dos = new DataOutputStream(sc.getOutputStream());
                dos.writeUTF("Selamat Datang Player ke-" + angka);
            } catch (IOException ex) {
                Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
            }
          
        }
    }
