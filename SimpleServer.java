import java.io.*;
import java.net.*;
public class SimpleServer
{
   public static void main(String[] args)
   {
       try
    {
        ServerSocket sock =new ServerSocket(8080);
        Socket newsock =sock.accept();
        System.out.println("conection from server complete ");
        DataInputStream inp=new DataInputStream(newsock.getInputStream());
        PrintStream outp=new PrintStream(newsock.getOutputStream());
        outp.println("Hello :: entter Quit to exit");
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
