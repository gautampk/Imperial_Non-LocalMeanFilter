package com.imperial.gaushun.denoisifier;

public class NonLocalMeanFilter {
    public int h;
    public int C;
    public int NF;
    public int s;
    
    public NonLocalMeanFilter(int grpSize, int Skip){
        h = grpSize;
        s = Skip;
    }
    
    public int u(Image img, int x, int y){
    	int localGrey = 0;
        int span = (h-1)/2;
        
        for(int n=-1*span;n<span;n++){
			for(int m=-1*span;m<span;m++){
				localGrey += (img.getRed(x+m,y+n) + img.getBlue(x+m,y+n) + img.getGreen(x+m,y+n))/3;
			}
		}
        localGrey /= h*h;
        
        return localGrey;
    }
    
    public void filter(Image in, Image out){
        for(int i=0;i<in.width;i++){
            for(int j=0;j<in.height;j++){
            
				//Work out C(X)
				C = 0;
				for(int n=0;n<in.width-s;n+=s){
					for(int m=0;m<in.height-s;m+=s){
						C += Math.exp( -1*( (Math.abs(u(in,n,m)-u(in,i,j))^2) / (h^2) ) );
					}
				}
               
				//Work out new grey value
				NF = 0;
				for(int n=0;n<in.width-s;n+=s){
					for(int m=0;m<in.height-s;m+=s){
						NF += u(in,n,m)*(Math.exp( -1*( (Math.abs(u(in,n,m)-u(in,i,j))^2) / (h^2) ) ));
					}
				}
				NF /= C;
				
				if(NF > 255){
					NF = 255;
				}else if(NF < 0){
					NF = 0;
				}
				
				//Set grey values
				out.setRed(i,j,NF);
				out.setGreen(i,j,NF);
				out.setBlue(i,j,NF);
            }
        }
    }
}
