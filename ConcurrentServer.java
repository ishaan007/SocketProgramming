import java.io.*;
import java.net.*;

 class ThreadHandler extends Thread
{
    Socket newsock;
    int n;
    ThreadHandler(Socket s,int v)
    {
        newsock =s;
        n=v;
    }
    public void run()
    {
        try
        {
            DataInputStream inp=new DataInputStream(newsock.getInputStream());
            PrintStream outp=new PrintStream(newsock.getOutputStream());
            outp.println("Hello : : enter QUIT to exit");
            boolean more_data=true;
        while(more_data)
        {
            String line=inp.readLine();
            if(line==null)more_data=false;
            else
            {
                outp.println("from server "+line+"\n");
                if(line.trim().equals("QUIT"))
                more_data=false;
            }
        }
        newsock.close();
    }
    catch(IOException e)
    {
        System.out.println("IO error"+e);
    }
    }
}
public class ConcurrentServer
{
    public static void main(String[] args)
    {
        int nreq=1;
        try
        {
            ServerSocket sock=new ServerSocket(8080);
            for(;;)
            {
                Socket newsock =sock.accept();
                System.out.println("Creating thread ...");
                Thread t=new ThreadHandler(newsock,nreq);
                t.start();
                nreq++;
                
            }
        }
        catch(Exception e)
        {
            System.out.println("IO error "+e);
        }
    }
}
