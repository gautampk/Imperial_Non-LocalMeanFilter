package com.imperial.gaushun.denoisifier;

import java.util.Random;

public class ImgGen {
	public ImgGen(){
		
	}
	
	public Image sine(int width, int height){
		Image img = new Image(width,height);
		int c;
		
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				c = (int) ( (255*Math.pow(Math.sin((2*i*Math.PI)/(width-1)),2)) + (255*Math.pow(Math.sin((2*j*Math.PI)/(width-1)),2)) );
				img.setRed(i,j,c);
				img.setGreen(i,j,c);
				img.setBlue(i,j,c);
			}
		}
		
		return img;
	}
	
	public Image gaussian(int width, int height, double sd){
		Image img = new Image(width,height);
		Random rg = new Random();
		int c;
		
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				c = (int) ( (rg.nextGaussian()*sd) + 128 );
				if(c < 0){
					c = 0;
				}else if(c > 255){
					c = 255;
				}
				img.setRed(i,j,c);
				img.setGreen(i,j,c);
				img.setBlue(i,j,c);
			}
		}
		
		return img;
	}
}