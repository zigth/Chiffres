package org.chiffres;

import java.util.ArrayList;


 class Nombre {
	private int value;
	private ArrayList<Construct> variations = new ArrayList<>();
	private boolean updated = true;
	
	
	int getValue() {
		return value;
	}
	
	ArrayList<Construct> getVariations() {
		return variations;
	}
	
	Construct getVariation(int index) {
		return variations.get(index);	
	}
	
	boolean isUpdated() {
		return updated;
	}
	
	void setUpdated(boolean updated) {
		this.updated = updated;
	}
	
	
	Nombre(int value, ArrayList<Construct> input) {
		this.value = value;
		this.addVariations(input);
	}
	
	boolean addConstruct (Construct inputConst){
		boolean result = true;
		Composition input = inputConst.getComp();
		for (int i = 0; i < this.variations.size(); i++) {			
			Composition comp=this.variations.get(i).getComp();
			if(comp.isSuperiorTo(input)){
				result=false;		
			}
			if(result&&input.isSuperiorTo(comp)){
				this.variations.remove(i);
			}
		}
		if(result){
			this.variations.add(inputConst);
		}	
		return result;
	}
	
	boolean containsConstruct(Composition input, ArrayList<Construct> list){
		boolean result = false;
		for (Construct cons : list) {
			if(cons.getComp().isSuperiorTo(input)){
				result = true;
			}
		}		
		return result;
	}
	
	boolean addVariations(ArrayList<Construct> inputlist) {
		boolean result=false;
		for (Construct cons : inputlist) {
			if(this.addConstruct(cons)){
				result = true;
			}
		}
		return result;
	}
	
	ArrayList<Construct> mergeVariations(Nombre input) {
		ArrayList<Construct> thatVariations = input.getVariations();
		ArrayList<Construct> result = new ArrayList<>();
		
		for (Construct disConstruct : this.variations) {
			for (Construct thatConstruct : thatVariations) {
				if (disConstruct.getComp().isCompatible(thatConstruct.getComp())){
					Construct newConstruct = new Construct(disConstruct.getComp(),thatConstruct.getComp(),this.getValue(),input.getValue());
					if(!containsConstruct(newConstruct.getComp(),result)){
						result.add(newConstruct);
					}
				}
			}
		}
		return result;
	}

}
