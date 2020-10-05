package org.chiffres;

import java.util.ArrayList;


public class Chiffres {

	public static void main(String[] args) {

		InputAssist myInputAssist = new InputAssist();

		System.out.println("first number: ");
		int int1 = myInputAssist.getInteger();
		
		System.out.println("second number: ");
		int int2 = myInputAssist.getInteger();
		
		System.out.println("third number: ");
		int int3 = myInputAssist.getInteger();
		
		System.out.println("fourth number: ");
		int int4 = myInputAssist.getInteger();
		
		System.out.println("fifth number: ");
		int int5 = myInputAssist.getInteger();
		
		System.out.println("sixth number: ");
		int int6 = myInputAssist.getInteger();
		
		
		NombreList nombList = new NombreList(int1,int2,int3,int4,int5,int6);
		
		ArrayList<Construct> tempList;//	= new ArrayList<Construct>();
		int value1;
		int value2;
		int newValue;
		int index;
		
		while (nombList.isTodo()){
			for (int i = 0; i < nombList.getLength(); i++) {
				if(nombList.getNombre(i).isUpdated()){
					nombList.getNombre(i).setUpdated(false);
					for (int j = 0; j < nombList.getLength(); j++) {
						tempList = nombList.getNombre(i).mergeVariations(nombList.getNombre(j));			
						if(tempList.size()>0){
							value1 = nombList.getNombre(i).getValue();
							value2 = nombList.getNombre(j).getValue();
							for (int operator = 0; operator < 4; operator++) {
								switch (operator){
								case 0:	newValue = value1+value2;
										break;
								case 1:	newValue = Math.abs(value1-value2);
										break;
								case 2: newValue = value1*value2;
										break;
								case 3: newValue = 0;								
										if ((value1!=0)&&(value2!=0)) {
											if (value1 % value2 == 0) {
												newValue = value1 / value2;
											}
											if (value2 % value1 == 0) {
												newValue = value2 / value1;
											}
										}
										break;
								default: newValue = 0;
										 break;								
								}
								
								index = nombList.containsNombre(newValue);
								if(index<0){
									nombList.addNombre(new Nombre(newValue, tempList));
								}else{
									if(nombList.getNombre(index).addVariations(tempList)){
										nombList.getNombre(index).setUpdated(true);
									}
								}								
							}							
						}
					}					
				}
			}
			nombList.updateTodo();
		}
		
		
		
		boolean[] checkList = new boolean[1000];
		int checkValue;
		for (int i = 0; i < nombList.getLength(); i++) {			
			checkValue = nombList.getNombre(i).getValue();
			if ((0<=checkValue)&&(checkValue<1000)){
				checkList[nombList.getNombre(i).getValue()] = true;
			}
		}
		
		System.out.println("numbers impossible to construct: ");
		boolean noNumCheck = true;
		
		for (int i = 0; i < checkList.length; i++) {
			if(!checkList[i]){
				System.out.println(i);
				noNumCheck = false;
			}
		}
		
		if (noNumCheck){
			System.out.println("There are no numbers impossible to construct");
		}
		
		
		
		System.out.println("Display the Construction a Number? (Yes/No)");
		boolean printCondition = myInputAssist.getBoolean();
		
		
		while(printCondition){
			System.out.println("Display Construction of which Number:");
			int num = myInputAssist.getInteger();

			System.out.println(nombList.printOutNombreConstruction(num));
			System.out.println("Display the Construction of another Number? (Yes/No)");
			printCondition = myInputAssist.getBoolean();
		}
		
	}
	
}
