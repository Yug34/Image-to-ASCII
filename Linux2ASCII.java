package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
//import javax.swing.JFrame;

public class BetterImg2ASCII
{
    public static void main(String[] args)
    {
        BufferedImage image;
        int width;
        int height;

        String  charstr = "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/|()1{}[]?-_+~<>i!lI;:,\"^`'. ";
        int len = charstr.length();

        try
        {
            //windows    path
            File input = new File("C:\\Users\\Yug\\Desktop\\nature.jpg");
            //linux path
            //File input = new File("/home/verti/Desktop/nature.jpg");

            try {
                File myObj = new File("/home/verti/Desktop/ASCII.txt");
                if (myObj.createNewFile()) {
                    System.out.println("File created: " + myObj.getName());
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

            FileWriter writer = new FileWriter("/home/verti/Desktop/ASCII.txt");

            image = ImageIO.read(input);
            width = image.getWidth();
            height = image.getHeight();

            int charheight = 12;
            int charwidth = 8;

            for (int i = 0; i < height; i+=charheight)
            {
                for (int j = 0; j < width; j+=charwidth)
                {
                    Color[][] c = new Color[charheight][charwidth];
                    int[][] redArr = new int[charheight][charwidth];
                    int[][] greenArr = new int[charheight][charwidth];
                    int[][] blueArr = new int[charheight][charwidth];

                    int average = 0;

                    for (int k = 0; k < charheight; k++)
                    {
                        for (int l = 0; l < 8; l++)
                        {
                            c[k][l] = new Color(image.getRGB(j + l, i + k));

                            redArr[k][l] = (int) (c[k][l].getRed() * 0.2989);
                            greenArr[k][l] = (int) (c[k][l].getGreen() * 0.5870);
                            blueArr[k][l] = (int) (c[k][l].getBlue() * 0.1140);

                            average += redArr[k][l] + greenArr[k][l] + blueArr[k][l];
                        }
                    }

                    average /= (charheight*charwidth);

                    int index = (int) Math.ceil(average * (len - 1) / 255);
                    char str = charstr.charAt(index);

                    writer.append(str);
                    System.out.print(str);
                }
                writer.append("\n");
                System.out.println();
            }
            writer.close();
            System.out.println("Finished");
        }
        catch (Exception e) {}
    }
}