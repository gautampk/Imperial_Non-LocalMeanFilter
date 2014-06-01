package com.imperial.gaushun.denoisifier;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;

//Currently loads an image (line 13) and then copies it as a PNG (line 14).
//Now generates noise and applies it to the image (demonstrate pixel-by-pixel editing).
//Need to supply command line option now:
//**> cd /path/to/download/folder
//**> cd bin
//**> java com.imperial.gaushun.denoisifier.Image "/path/to/image"

//TODO: Denoised image is very dark.

public class Image {
	//Fields
	public int[][][][] pxlGrps;	//pxlGrps[x][y][group no. (0-8 & 9 for mean)][a/r/g/b/argb]
	public int height;
	public int width;
	
	//Constructor
	public Image(String filePath) {
		try {
			BufferedImage img = ImageIO.read(new File(filePath));
			width = img.getWidth();
			height = img.getHeight();
			pxlGrps = new int[width][height][10][5];
			
			//Enter values for pxlGrps
			for(int i=0;i<width;i+=3){
				for(int j=0;j<height;j+=3){
					if(i>=width-1 || j>=height-1){
		    			break;
		    		}
					
					//First enter the values for individual pixels
					pxlGrps[i][j][0][0] = new Color(img.getRGB(i-1,j+1)).getAlpha();
					pxlGrps[i][j][1][0] = new Color(img.getRGB(i,j+1)).getAlpha();
					pxlGrps[i][j][2][0] = new Color(img.getRGB(i+1,j+1)).getAlpha();
					pxlGrps[i][j][3][0] = new Color(img.getRGB(i-1,j)).getAlpha();
					pxlGrps[i][j][4][0] = new Color(img.getRGB(i,j)).getAlpha();
					pxlGrps[i][j][5][0] = new Color(img.getRGB(i+1,j)).getAlpha();
					pxlGrps[i][j][6][0] = new Color(img.getRGB(i-1,j-1)).getAlpha();
					pxlGrps[i][j][7][0] = new Color(img.getRGB(i,j-1)).getAlpha();
					pxlGrps[i][j][8][0] = new Color(img.getRGB(i+1,j-1)).getAlpha();
					
					pxlGrps[i][j][0][1] = new Color(img.getRGB(i-1,j+1)).getRed();
					pxlGrps[i][j][1][1] = new Color(img.getRGB(i,j+1)).getRed();
					pxlGrps[i][j][2][1] = new Color(img.getRGB(i+1,j+1)).getRed();
					pxlGrps[i][j][3][1] = new Color(img.getRGB(i-1,j)).getRed();
					pxlGrps[i][j][4][1] = new Color(img.getRGB(i,j)).getRed();
					pxlGrps[i][j][5][1] = new Color(img.getRGB(i+1,j)).getRed();
					pxlGrps[i][j][6][1] = new Color(img.getRGB(i-1,j-1)).getRed();
					pxlGrps[i][j][7][1] = new Color(img.getRGB(i,j-1)).getRed();
					pxlGrps[i][j][8][1] = new Color(img.getRGB(i+1,j-1)).getRed();
					
					pxlGrps[i][j][0][2] = new Color(img.getRGB(i-1,j+1)).getGreen();
					pxlGrps[i][j][1][2] = new Color(img.getRGB(i,j+1)).getGreen();
					pxlGrps[i][j][2][2] = new Color(img.getRGB(i+1,j+1)).getGreen();
					pxlGrps[i][j][3][2] = new Color(img.getRGB(i-1,j)).getGreen();
					pxlGrps[i][j][4][2] = new Color(img.getRGB(i,j)).getGreen();
					pxlGrps[i][j][5][2] = new Color(img.getRGB(i+1,j)).getGreen();
					pxlGrps[i][j][6][2] = new Color(img.getRGB(i-1,j-1)).getGreen();
					pxlGrps[i][j][7][2] = new Color(img.getRGB(i,j-1)).getGreen();
					pxlGrps[i][j][8][2] = new Color(img.getRGB(i+1,j-1)).getGreen();
					
					pxlGrps[i][j][0][3] = new Color(img.getRGB(i-1,j+1)).getBlue();
					pxlGrps[i][j][1][3] = new Color(img.getRGB(i,j+1)).getBlue();
					pxlGrps[i][j][2][3] = new Color(img.getRGB(i+1,j+1)).getBlue();
					pxlGrps[i][j][3][3] = new Color(img.getRGB(i-1,j)).getBlue();
					pxlGrps[i][j][4][3] = new Color(img.getRGB(i,j)).getBlue();
					pxlGrps[i][j][5][3] = new Color(img.getRGB(i+1,j)).getBlue();
					pxlGrps[i][j][6][3] = new Color(img.getRGB(i-1,j-1)).getBlue();
					pxlGrps[i][j][7][3] = new Color(img.getRGB(i,j-1)).getBlue();
					pxlGrps[i][j][8][3] = new Color(img.getRGB(i+1,j-1)).getBlue();
					
					pxlGrps[i][j][0][4] = img.getRGB(i-1,j+1);
					pxlGrps[i][j][1][4] = img.getRGB(i,j+1);
					pxlGrps[i][j][2][4] = img.getRGB(i+1,j+1);
					pxlGrps[i][j][3][4] = img.getRGB(i-1,j);
					pxlGrps[i][j][4][4] = img.getRGB(i,j);
					pxlGrps[i][j][5][4] = img.getRGB(i+1,j);
					pxlGrps[i][j][6][4] = img.getRGB(i-1,j-1);
					pxlGrps[i][j][7][4] = img.getRGB(i,j-1);
					pxlGrps[i][j][8][4] = img.getRGB(i+1,j-1);
					
					//Now the mean
					for(int k=0;k<=8;k++){
						pxlGrps[i][j][9][0] += pxlGrps[i][j][k][0];
						pxlGrps[i][j][9][1] += pxlGrps[i][j][k][1];
						pxlGrps[i][j][9][2] += pxlGrps[i][j][k][2];
						pxlGrps[i][j][9][3] += pxlGrps[i][j][k][3];
		    		}
					pxlGrps[i][j][9][0] /= 9;
					pxlGrps[i][j][9][1] /= 9;
					pxlGrps[i][j][9][2] /= 9;
					pxlGrps[i][j][9][3] /= 9;
					pxlGrps[i][j][9][4] = new Color(pxlGrps[i][j][9][1],pxlGrps[i][j][9][2],pxlGrps[i][j][9][3],pxlGrps[i][j][9][0]).getRGB();
				}
			}
		} catch (IOException e) {
			System.out.println("Input/Output Error. Check filepaths.");
		}
	}
};