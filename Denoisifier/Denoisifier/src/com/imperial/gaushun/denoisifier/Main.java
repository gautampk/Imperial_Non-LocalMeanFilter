package com.imperial.gaushun.denoisifier;

public class Main {
	public static void main(String[] args){
		Image img = new Image(args[0]);
		Image out = new Image(img.width,img.height);
	
		Noisifier noiseGen = new Noisifier();
		LocalMeanFilter lmfGen = new LocalMeanFilter(3);
		NonLocalMeanFilter nlmfGen = new NonLocalMeanFilter(15,5,21,false);
		GreyMaker greyGen = new GreyMaker();
		ImgGen imgGen = new ImgGen();
		
		noiseGen.addNoise(img, 25, false);
		img.saveImg("png", args[0]+".noise.png");
		
		nlmfGen.filter(img, out);
		out.saveImg("png", args[0]+".nlmf.png");
		
		lmfGen.filter(img, out);
		out.saveImg("png", args[0]+".lmf.png");
	}
}