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
				"WY", "OZ", "TL", "BH", "HJ", "875h", "gtrf", "TR98", "12#K",
				"875h", "gtrf", "TR98", "12#K", "QWE", "RTY", "56F", "MC&",
				"QWE", "RTY", "56F", "MC&", "65G", "XEy" };
		String rateCode[] = new String[] { "AD", "REF", "RTY", "MKJ", "LOI",
				"WY", "OZ", "TL", "BH", "HJ", "875h", "gtrf", "TR98", "12#K",
				"875h", "gtrf", "TR98", "12#K", "QWE", "RTY", "56F", "MC&",
				"65G", "XEy", "98k", "876", "XDS", "HG%", "LKJ", "JHG" };

		String hotelCode[] = new String[] { "HTL1", "PLZ2", "098", "7ujh",
				"875h", "gtrf", "TR98", "12#K", "QWE", "RTY", "56F", "MC&",
				"65G", "XEy", "98k", "876", "XDS", "HG%", "LKJ", "JHG" };

		try {
			if (args.length < 1){
				System.out.println("cariData <output file>");
				System.exit(1);
			}
			File file = new File(args[0]);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
			BufferedWriter bw = new BufferedWriter(fw);

			for (int i = 0; i < brandCode.length; i++) {
				for (int j = 0; j < chainCode.length; j++) {
					for (int k = 0; k < rateCode.length; k++) {
						for (int l = 0; l < hotelCode.length; l++) {
							bw.write(brandCode[i] + "," + chainCode[j] + ","
									+ rateCode[k] + "," + hotelCode[l] + ","
									+ hotelCode[l] + "," + hotelCode[l] + ","
									+ new Date().getTime() + '\n');

						}
					}
				}

			}
			
			bw.flush();
			bw.close();
			

		} catch (Exception ex) {
			System.out.println(ex);
		}

	}
}
