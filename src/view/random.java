package view;

import java.util.HashSet;
import java.util.Iterator;

public class random {

static 	HashSet<Integer> A = new HashSet<Integer>();
	public static void main(String args[]){
		
		generate(30);	
		while(A.size() != 30){
			int diff = 30 - A.size();
			generate(diff);
			
		}
		Iterator<Integer> it = A.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
			
		}

	private static void generate(int i) {
		
			for(int j=0; j< i;j++){
				int k = (int) (Math.random()* 100);
				A.add(k);
			
			
			
		
		
	}
		
		
	}
}
	

