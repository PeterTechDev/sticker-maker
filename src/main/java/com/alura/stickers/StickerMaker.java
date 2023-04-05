package com.alura.stickers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

public class StickerMaker {

    public void create(InputStream inputStream, String fileName) throws Exception {

        BufferedImage originalImage = ImageIO.read(inputStream);

        //create new image (in memory) with transparency and new size
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int newHeight = height + 200;

        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        // copy original image to the new image in memory
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(originalImage, 0, 0, null);

        // config font
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 44);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(font);

        // write text under the new image
        graphics.drawString("Testing", 100, newHeight - 100);


        // write newImage in a file
        ImageIO.write(newImage, "png", new File("output/" + fileName));
    }
}


