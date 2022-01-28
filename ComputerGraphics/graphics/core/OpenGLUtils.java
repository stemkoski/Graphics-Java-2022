package graphics.core;

import java.nio.file.*;

/**
 * methods to load, send, compile, link, check shader programs
 *
 * @author Stemkoski
 * @version 1
 */
public class OpenGLUtils
{
   public static String readFile(String fileName)
   {      
       String text = "";
       try
       {
           text = new String(
                    Files.readAllBytes(
                      Paths.get(fileName)
                    )
                  );
       }
       catch (Exception ex)
       {
           ex.printStackTrace();
       }
       
       return text;
   }


}
