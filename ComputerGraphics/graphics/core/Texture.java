package graphics.core;

// import OpenGL functions to work with GPU
import static org.lwjgl.opengl.GL40.*;

// import image loading library
import static org.lwjgl.stb.STBImage.*;

// use these as temporary storage
import org.lwjgl.BufferUtils;

import java.nio.*;

/**
 *  Load and transmit image data to the GPU.
 */
public class Texture
{
    public int width;
    public int height;
    public int temp;
    
    public ByteBuffer pixelData; // R,G,B,A values separated
    
    /**
     * Load an image file and transmit data to GPU.
     * Using the "stb" library.
     * Store data in an array of bytes.
     *
     * @param fileName A parameter
     */
    public Texture(String fileName)
    {
    
        // in order to obtain width and height of image,
        // pass these objects in as parameters (pass by reference);
        // they will be used to store additional return data,
        // which we will copy into width/height
        IntBuffer widthBuf  = BufferUtils.createIntBuffer(1);
        IntBuffer heightBuf = BufferUtils.createIntBuffer(1);
        IntBuffer tempBuf   = BufferUtils.createIntBuffer(1);
        
        // setting to flip texture along Y axis
        stbi_set_flip_vertically_on_load(true);
        // 4 = 4 bytes = R,G,B,A
        pixelData = stbi_load( fileName, widthBuf, heightBuf, tempBuf, 4 );
        
        width  = widthBuf.get();
        height = heightBuf.get();
        
        uploadData();
    }
    
    // reference to a texture buffer object on the GPU
    public int textureRef;
    
    // upload pixel data to the GPU
    public void uploadData()
    {
        textureRef = glGenTextures();
        
        // bind the texture object: all OpenGL functions will target this buffer
        glBindTexture(GL_TEXTURE_2D, textureRef);
        
        // does the uploading of data to the GPU:
        //  glTexImage2D( bindTarget, mipmapLevel, internalFormat, width, height, 
        //                      border, format, typeNumberOfBits, pixelData)
        glTexImage2D( GL_TEXTURE_2D, 0, GL_RGBA, width, height,
                                     0, GL_RGBA, GL_UNSIGNED_BYTE, pixelData);
                                     
        // set other properties:
        
        // improve efficiency
        glGenerateMipmap(GL_TEXTURE_2D);
        
        // when zooming in and out on image - how to combine pixel data
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        
        
    }
    
    
    
    
    
    
}
