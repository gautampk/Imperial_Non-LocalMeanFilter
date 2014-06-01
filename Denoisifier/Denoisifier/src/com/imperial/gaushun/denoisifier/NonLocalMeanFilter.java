package com.imperial.gaushun.denoisifier;

public class NonLocalMeanFilter {
    public int h;
    public int C;
    public int NF;
    
    public NonLocalMeanFilter(int h){
        this.h = h;
    }
    
    public int u(Image img, int x, int y){
        return (img.getRed(x,y) + img.getBlue(x,y) + img.getGreen(x,y))/3;
    }
    
    public void filter(Image img){
        for(int i=0;i<img.width;i++){
            for(int j=0;j<img.height;j++){
            
				//Work out C(X)
				C = 0;
				for(int n=0;n<img.width;n++){
					for(int m=0;m<img.height;m++){
						C += Math.exp((Math.abs(u(img,n,m)-u(img,i,j))^2) / (h^2) );
					}
				}
               
				//Work out new grey value
				NF = 0;
				for(int n=0;n<img.width;n++){
					for(int m=0;m<img.height;m++){
						NF += u(img,n,m)*(Math.exp((Math.abs(u(img,n,m)-u(img,i,j))^2) / (h^2) ));
					}
				}
				NF /= C;
				
				//Set grey values
				img.setRed(i,j,NF);
				img.setGreen(i,j,NF);
				img.setBlue(i,j,NF);
            }
        }
    }
}
