package com.basic.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;

public class copyFileByChannel
{
    public static void copy(File source, File dest)
    {
        try(FileChannel sourceChannel = new FileInputStream(source).getChannel();
            FileChannel targetChannel = new FileOutputStream(dest).getChannel();)
        {
            for (long count = sourceChannel.size(); count > 0 ; )
            {
                long transferred = sourceChannel.transferTo(sourceChannel.position(), count, targetChannel);
                sourceChannel.position(sourceChannel.position() + transferred);
                count -= transferred;
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
