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

    public void create() throws Exception {

        //read image
//        FileInputStream inputStream = new FileInputStream("assets/lotr.jpg");
        InputStream inputStream =
                new URL("https://blogger.googleusercontent.com/img/a/AVvXsEhIwlW0X5_Kja8cYSDxTwnhLWs4ZJuH25xfkp82VOi75drFEEMeC18tevPAgqi1UV2krnEs0EiHR7bGlYX6XQtWQQFkZmJASlzxw55QXK8NvmVopyKN0n5YtBVKa7aLP0ZM6yAKXHe2kE2HhMqUVtUGreXAxxjywX7MNQGg9r7yh_B_Z_R5azGDrj0-YA=w512-h640")
                        .openStream();
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
        ImageIO.write(newImage, "png", new File("output/sticker.png"));
    }

    public static void main(String[] args) throws Exception {
        var stickerMaker = new StickerMaker();
        stickerMaker.create();
    }
}


