/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Poker;

/**
 *
 * @author Putra
 */
public class ServerCode {
    private ServerPokerTable spt;
    public static int port;
    
    public static void main(String args[]){
       ServerCode Scode = new ServerCode();
       
       Scode.OpenGUI();
       
       //mulai thread
       ServerPokerTable Spt = new ServerPokerTable(port);
       Spt.setVisible(true);
       
    }
    
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
}
