package com.imperial.gaushun.denoisifier;

public class LocalMeanFilter {
	public int grpSize;
	public int span;
	public int[] meanRGB = {0,0,0};
	
	public LocalMeanFilter(int grpSize){
		this.grpSize = grpSize;
		
		if(this.grpSize < 1){
			this.grpSize = 1;
			System.out.println("Group size must be 1 or greater. Your entry has been rounded up to 1.");
		}
		
		if(this.grpSize % 2 == 0){
			this.grpSize++;
			System.out.println("Odd group sizes only. Your entry has been rounded up to " + grpSize + ".");
		}
		
		span = (grpSize-1)/2;
	}
	
	public void filter(Image in, Image out){
		for(int i=span;i<in.width;i++){
			for(int j=span;j<in.height;j++){
				meanRGB[0] = 0; meanRGB[1] = 0; meanRGB[2] = 0;
				
				for(int n=-1*span;n<=span;n++){
					for(int m=-1*span;m<=span;m++){
						if(i+n < 0 || i+n > in.width-1 || j+m < 0 || j+m > in.height-1){
							continue;
						}
						meanRGB[0] += in.getRed(i+n,j+m);
						meanRGB[1] += in.getGreen(i+n,j+m);
						meanRGB[2] += in.getBlue(i+n,j+m);
					}
				}
				
				meanRGB[0] /= grpSize*grpSize;
				meanRGB[1] /= grpSize*grpSize;
				meanRGB[2] /= grpSize*grpSize;
				
				out.setRed(i,j,meanRGB[0]);
				out.setGreen(i,j,meanRGB[1]);
				out.setBlue(i,j,meanRGB[2]);
			}
		}
	}
}
