package com.gojek.executor;

import com.gojek.controller.FrontController;
import com.gojek.util.Utils;

public class Executor {
	public static void main(String... args){
		String mode="";
		String commandfile="";
		FrontController batchinstance = new FrontController();
		if (args.length < 1){
			System.out.println("Usage :: Executor <Mode> <Instructions File>");
			System.out.println("	Mode :: Interactive/Batch. No Other modes are supported");
		} else {
			if (!Utils.isStringNullOrEmpty(args[0])){
				mode=args[0];

				switch(mode){
				case "Interactive":
					batchinstance.router(mode);
					break;
				case "Batch":
					if (!Utils.isStringNullOrEmpty(args[1])){
						commandfile=args[1];
						batchinstance.router(mode,commandfile);
					} else {
						System.out.println("Error :: Command file path is missing.");
					}

					break;
				default:
					System.out.println("Error :: Not a valid mode. Exiting ...");
					System.exit(-1);
					break;
				}

			}
		}


	}
}
