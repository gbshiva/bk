package com.wyndham.ari.datagen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Date;

public class cariData {
	public static void main(String args[]) {

		String brandCode[] = new String[] { "Wyndham", "Wyndham Grand",
				"Wyndham Garden", "TRYP", "WINGATE", "HAWTHORN", "MICROTEL",
				"DREAM", "planet hollywood", "Super8", "Howard Johnson",
				"Travellodge", "KnightsInn", "RCI", "RegistryCollection",
				"Landal", "NOVASOL", "Canvas", "WorldMark", "Margaritvalle" };
		String chainCode[] = new String[] { "WY", "WY", "WY", "WY", "WG", "WG",
				"WY", "OZ", "TL", "BH", "HJ" };
		String rateCode[] = new String[] { "AD", "REF", "RTY", "MKJ", "LOI" };
		String hotelCode[] = new String[] { "HTL1", "PLZ2", "098", "7ujh",
				"875h", "gtrf" };
		
		
		try{
		File file = new File("/Users/sgokaram/Documents/Client-POCs/Wyndham/poc/bk/src/main/resources/cari.data");
		 
		// if file doesnt exists, then create it
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		
		

		for (int i = 0; i < brandCode.length; i++) {
			for (int j = 0; j < chainCode.length; j++) {
				for (int k = 0; k < rateCode.length; k++) {
					for (int l = 0; l < hotelCode.length; l++) {
						bw.write(brandCode[i] + "," + chainCode[j]
								+ "," + rateCode[k] + "," + hotelCode[l] + ","
								+ hotelCode[l] + "," + hotelCode[l] + ","
								+ new Date().getTime() +'\n');

					}
				}
			}

		}

		}catch(Exception ex){
		System.out.println(ex);
	}
		
}
}
