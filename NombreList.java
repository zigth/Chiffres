package org.chiffres;

import java.util.ArrayList;

class NombreList {

	private ArrayList<Nombre> list = new ArrayList<>();
	private boolean todo = true;

	NombreList(int int1, int int2, int int3, int int4, int int5, int int6) {
		boolean[] comp1 = {true,false,false,false,false,false};
		Composition c1 = new Composition(comp1);
		Construct cons1 = new Construct(c1);
		ArrayList<Construct> l1 = new ArrayList<>();
		l1.add(cons1);
		Nombre n1 = new Nombre(int1,l1);
		
		boolean[] comp2 = {false,true,false,false,false,false};
		Composition c2 = new Composition(comp2);	
		Construct cons2 = new Construct(c2);
		ArrayList<Construct> l2 = new ArrayList<>();
		l2.add(cons2);
		Nombre n2 = new Nombre(int2,l2);
		
		boolean[] comp3 = {false,false,true,false,false,false};
		Composition c3 = new Composition(comp3);	
		Construct cons3 = new Construct(c3);
		ArrayList<Construct> l3 = new ArrayList<>();
		l3.add(cons3);
		Nombre n3 = new Nombre(int3,l3);
		
		boolean[] comp4 = {false,false,false,true,false,false};
		Composition c4 = new Composition(comp4);	
		Construct cons4 = new Construct(c4);
		ArrayList<Construct> l4 = new ArrayList<>();
		l4.add(cons4);
		Nombre n4 = new Nombre(int4,l4);
		
		boolean[] comp5 = {false,false,false,false,true,false};
		Composition c5 = new Composition(comp5);	
		Construct cons5 = new Construct(c5);
		ArrayList<Construct> l5 = new ArrayList<>();
		l5.add(cons5);
		Nombre n5 = new Nombre(int5,l5);
		
		boolean[] comp6 = {false,false,false,false,false,true};
		Composition c6 = new Composition(comp6);	
		Construct cons6 = new Construct(c6);
		ArrayList<Construct> l6 = new ArrayList<>();
		l6.add(cons6);
		Nombre n6 = new Nombre(int6,l6);
		
		this.addNombre(n1);
		this.addNombre(n2);
		this.addNombre(n3);
		this.addNombre(n4);
		this.addNombre(n5);
		this.addNombre(n6);
	}

	boolean isTodo() {
		return todo;
	}

	void updateTodo() {
		this.todo = false;
		for (Nombre nomb : this.list) {
			if(nomb.isUpdated()){
				this.todo=true;
			}
		}
	}
	
	void addNombre(Nombre input) {
		int index = this.containsNombre(input.getValue());
		if (index<0){
			list.add(input);
		}else{
			this.getNombre(index).addVariations(input.getVariations());
		}
	}
	
	int containsNombre(int inputvalue) {
		int result = -1;
		for (int i = 0; i < this.getLength(); i++) {
				if(this.getNombre(i).getValue()==inputvalue){
				result = i;
			}
		}
		return result;
	}
	
	Nombre getNombre(int index) {
		return this.list.get(index);
	}
	
	int getLength() {
		return this.list.size();
	}

	String printOutNombreConstruction(int input) {
		int index = containsNombre(input);
		if(index<0){
			return "This number is impossible to construct";
		}else{
			String Construction = printOutConstruction(this.getNombre(index),this.getNombre(index).getVariation(0).getComp());
			return input+" = "+Construction.substring(1,Construction.length()-1);
		}
	}
	
	String printOutConstruction(Nombre nomb, Composition comp) {
		
		Construct constr = nomb.getVariation(0);
		for (Construct cons : nomb.getVariations()) {
			if (cons.getComp().compEquals(comp)){
				constr=cons;
			}
		}
		
		int value = nomb.getValue();
		
		if (constr.isBasis()){
			return String.valueOf(value);
		}else{
			//System.out.print("(");
			
			char operator = '/';
			if (value == (constr.getValue1() + constr.getValue2())) {
				operator = '+';
			}
			if (value == (constr.getValue1() - constr.getValue2())) {
				operator = '-';
			}
			if (value == (constr.getValue1() * constr.getValue2())) {
				operator = '*';
			}
			
			int index1 = this.containsNombre(constr.getValue1());
			int index2 = this.containsNombre(constr.getValue2());

			return '('+printOutConstruction(this.getNombre(index1),constr.getComp1())+operator+printOutConstruction(this.getNombre(index2),constr.getComp2())+')';
		}
	}
}
