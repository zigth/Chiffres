package org.chiffres;

class Construct {
	private Composition	comp;
	private Composition comp1;
	private Composition comp2;
	private int value1;
	private int value2;
	private boolean basis = false;
	
	Construct(Composition comp1, Composition comp2, int value1, int value2) {
		
		if(value1>value2){
			this.comp1 = comp1;
			this.comp2 = comp2;
			this.value1 = value1;
			this.value2 = value2;
		}else{
			this.comp1 = comp2;
			this.comp2 = comp1;
			this.value1 = value2;
			this.value2 = value1;			
		}
		
		comp=comp1.combine(comp2);
		
		basis = false;
	}

	Construct(Composition comp) {
		
		this.comp = comp;
		basis = true;
		
		//value1=0;
		//value2=0;
		
		//comp1= new Composition(boolean[] = {false,false,false,false,false,false});
		
	}

	Composition getComp() {
		return comp;
	}

	Composition getComp1() {
		return comp1;
	}

	Composition getComp2() {
		return comp2;
	}

	int getValue1() {
		return value1;
	}

	int getValue2() {
		return value2;
	}

	boolean isBasis() {
		return basis;
	}

	
}
