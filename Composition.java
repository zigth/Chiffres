package org.chiffres;

class Composition {

	private final int numberOfElements = 6;
	private boolean[] elements = new boolean[numberOfElements];

	private boolean[] getElements() {
		return elements;
	}


	Composition(boolean[] elements) {
		this.elements = elements;
	}
	
	boolean isCompatible(Composition input) {
		boolean[] conditionals = input.getElements();
		boolean result = true;
		for (int i = 0; i < conditionals.length; i++) {
			result = (result&&(!(conditionals[i]&&this.elements[i])));
		}
		return result;
	}
	
	Composition combine(Composition input) {
		boolean[] conditionals = input.getElements();
		boolean[] result = new boolean[numberOfElements];
		for (int i = 0; i < conditionals.length; i++) {
			result[i]=((conditionals[i]||this.elements[i])&&(!(conditionals[i]&&this.elements[i])));
		}
		return new Composition(result);
	}


	boolean compEquals(Composition input) {
		boolean[] conditionals = input.getElements();
		boolean result = true;
		for (int i = 0; i < conditionals.length; i++) {
			result = (result&&((conditionals[i]&&this.elements[i])||(!conditionals[i]&&!this.elements[i])));
		}
		return result;
	}
	
	boolean isSuperiorTo(Composition input) {
		boolean[] conditionals = input.getElements();
		boolean result = true;
		for (int i = 0; i < conditionals.length; i++) {
			if (this.elements[i]){
				result = (result&&conditionals[i]);
			}
		}
		return result;
	}

	/*
	void printOutComposition() {
		for (int j = 0; j < numberOfElements; j++) {
			System.out.print(this.getElements()[j]);
			System.out.print("  ");
		}
		System.out.println(" ");
	}
	*/
	
}
