package com.imperial.gaushun.denoisifier;

public class NonLocalMeanFilter {
    public double h;
    public int s;
    public int R;
    public int[] NF = {0,0,0};
    boolean bw;
    
    public NonLocalMeanFilter(double variance, int groupSize, int searchSize, boolean bw){
        h = variance;
        s = (groupSize-1)/2;
        R = (searchSize-1)/2;
        this.bw = bw;
    }
    
    public int[] B(Image img, int x, int y){
    	int[] B = {0,0,0};
    	
    	for(int n=x-s;n<=x+s;n++){
			for(int m=y-s;m<=y+s;m++){
				if(n < 0 || n > img.width-1 || m < 0 || m > img.height-1){
					continue;
				}
				B[0] += img.getRed(x,y);
				if(!bw){
					B[1] += img.getGreen(x,y);
					B[2] += img.getBlue(x,y);
				}
			}
		}
    	
    	B[0] /= (2*s+1)^2;
    	if(!bw){
	    	B[1] /= (2*s+1)^2;
	    	B[2] /= (2*s+1)^2;
    	}
    	
    	return B;
    }
    
    public double[] C(Image img, int x, int y){
    	double[] C = {0,0,0};
    	
    	for(int n=x-R;n<=x+R;n++){
			for(int m=y-R;m<=y+R;m++){
				if(n < 0 || n > img.width-1 || m < 0 || m > img.height-1){
					continue;
				}
				C[0] += Math.exp( -1*( (Math.abs(B(img,n,m)[0]-B(img,x,y)[0])^2) / (h*h) ) );
				if(!bw){
					C[1] += Math.exp( -1*( (Math.abs(B(img,n,m)[1]-B(img,x,y)[1])^2) / (h*h) ) );
					C[2] += Math.exp( -1*( (Math.abs(B(img,n,m)[2]-B(img,x,y)[2])^2) / (h*h) ) );
				}
			}
		}
    	
    	return C;
    }
    
    public int[] u(Image img, int x, int y){
    	int[] u = {0,0,0};
    	if(bw){
    		u[0] = (img.getRed(x,y)+img.getGreen(x,y)+img.getBlue(x,y))/3;
    		u[1] = 0;
    		u[2] = 0;
    	}else{
    		u[0] = img.getRed(x,y);
    		u[1] = img.getGreen(x,y);
    		u[2] = img.getBlue(x,y);
    	}
    	return u;
    }
    
    public void filter(Image in, Image out){
        for(int i=0;i<in.width;i++){
            for(int j=0;j<in.height;j++){
            	System.out.print( "                    " + "\r");
            	System.out.print( ((double)(((double)(((double)(i*in.height))+j))/((double)(in.width*in.height))))*100 + "%\r");
            
				//Work out new grey value
				NF[0] = 0; NF[1] = 0; NF[2] = 0;
				for(int n=i-R;n<=i+R;n++){
					for(int m=j-R;m<=j+R;m++){
						if(n < 0 || n > in.width-1 || m < 0 || m > in.height-1){
							continue;
						}
						NF[0] += u(in,n,m)[0]*(Math.exp( -1*( (Math.abs(B(in,n,m)[0]-B(in,i,j)[0])^2) / (h*h) ) ));
						if(!bw){
							NF[1] += u(in,n,m)[1]*(Math.exp( -1*( (Math.abs(B(in,n,m)[1]-B(in,i,j)[1])^2) / (h*h) ) ));
							NF[2] += u(in,n,m)[2]*(Math.exp( -1*( (Math.abs(B(in,n,m)[2]-B(in,i,j)[2])^2) / (h*h) ) ));
						}
					}
				}
				NF[0] /= C(in,i,j)[0];
				if(!bw){
					NF[1] /= C(in,i,j)[1];
					NF[2] /= C(in,i,j)[2];
				}
				
				if(NF[0] > 255){
					NF[0] = 255;
				}else if(NF[0] < 0){
					NF[0] = 0;
				}
				if(!bw){
					if(NF[1] > 255){
						NF[1] = 255;
					}else if(NF[1] < 0){
						NF[1] = 0;
					}
					if(NF[2] > 255){
						NF[2] = 255;
					}else if(NF[2] < 0){
						NF[2] = 0;
					}
				}
				
				//Set grey values
				if(bw){
					out.setRed(i,j,NF[0]);
					out.setGreen(i,j,NF[0]);
					out.setBlue(i,j,NF[0]);
				}else{
					out.setRed(i,j,NF[0]);
					out.setGreen(i,j,NF[1]);
					out.setBlue(i,j,NF[2]);
				}
            }
        }
    }
}
