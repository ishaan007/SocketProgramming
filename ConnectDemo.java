import java.io.*;
import java.net.*;
public class ConnectDemo
{
    public static void main(String[] args)
    {
        try
        {
            Socket s=new Socket("127.0.0.1",8080);
            DataInputStream inp=new DataInputStream(s.getInputStream());
            boolean more_data=true;
            System.out.println("Established Connection");
            while(more_data)
            {
                String line=inp.readLine();
                if(line==null)
                {more_data=false;
                }
                else
                {
                    System.out.println(line);
                }
            }
        }
        catch(IOException e)
        {
            System.out.println("IO error "+e);
        }
    }
}
