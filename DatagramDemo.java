import java.io.*;
import java.net.*;
public class DatagramDemo
{
    public static int server_port=7500;
    public static int client_port=7501;
    public static DatagramSocket dgsock;
    public static byte buffer[] =new byte[512];
    public static void DgServer() throws Exception
    {
        System.out.println("server starts... ");
        int ptr=0;
        for(;;)
        {
            int nextchar=System.in.read();
            switch(nextchar)
            {
                case -1:
                System.out.println("Exiting ...");
                return ;
                case '\n':
                dgsock.send(new DatagramPacket(buffer,ptr,InetAddress.getLocalHost(),client_port));
                ptr=0;
                break;
                default:
                buffer[ptr++]=(byte)nextchar;
            }
        }
    }
    public static void DgClient() throws Exception
    {
        System.out.println("Client Starts ... ");
        for(;;)
        {
            DatagramPacket pkt =new DatagramPacket(buffer,buffer.length);
            dgsock.receive(pkt);
            System.out.println(new String(pkt.getData(),0,0,pkt.getLength()));
        }
    }
    public static void main(String[] args) throws Exception
    {
        if(args.length!=1)
        System.out.println("Wrong number of arguments");
        else if(args[0].equals("client"))
        {
            dgsock=new DatagramSocket(client_port);
            DgClient();
        }
        else if(args[0].equals("server"))
        {
            dgsock=new DatagramSocket(server_port);
            DgServer();
        }
    }
}
