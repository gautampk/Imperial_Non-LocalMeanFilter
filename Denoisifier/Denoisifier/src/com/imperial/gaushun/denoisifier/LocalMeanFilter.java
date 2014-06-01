package com.imperial.gaushun.denoisifier;

public class LocalMeanFilter {
	public int grpSize;
	public int span;
	public int[] meanRGB = {0,0,0};
	
	public LocalMeanFilter(int grpSize){
		this.grpSize = grpSize;
		
		if(this.grpSize < 3){
			this.grpSize = 3;
			System.out.println("Group size must be 3 or greater. Your entry has been rounded up to 3.");
		}
		
		if(this.grpSize % 2 == 0){
			this.grpSize++;
			System.out.println("Odd group sizes only. Your entry has been rounded up to " + grpSize + ".");
		}
		
		span = (grpSize-1)/2;
	}
	
	public void filter(Image img){
		for(int i=span;i<img.width;i+=grpSize){
			for(int j=span;j<img.height;j+=grpSize){
				meanRGB[0] = 0; meanRGB[1] = 0; meanRGB[2] = 0;
				if(i>img.width || j>img.height){
					break;
				}
				
				for(int n=-1*span;n<=span;n++){
					for(int m=-1*span;m<=span;m++){
						meanRGB[0] += img.getRed(i+m,j+n);
						meanRGB[1] += img.getGreen(i+m,j+n);
						meanRGB[2] += img.getBlue(i+m,j+n);
					}
				}
				
				meanRGB[0] /= grpSize*grpSize;
				meanRGB[1] /= grpSize*grpSize;
				meanRGB[2] /= grpSize*grpSize;
				
				for(int n=-1*span;n<=span;n++){
					for(int m=-1*span;m<=span;m++){
						img.setRed(i+m,j+n,meanRGB[0]);
						img.setGreen(i+m,j+n,meanRGB[1]);
						img.setBlue(i+m,j+n,meanRGB[2]);
					}
				}
			}
		}
	}
}
