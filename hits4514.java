// KALYANI TARU CS610 4514 PRP

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class hits4514 {
	static double[][] matrix;
	static double[][] transpose;
	static double[] hub;
	static double[] auth;
	static String file;
	static int nodes,edges,itt;
	static double intialvalue,erate;
	static double nfa = 0, nfh = 0;

	public static void main(String[] args) {
		itt = Integer.parseInt(args[0]);
		intialvalue = Integer.parseInt(args[1]);
		file = args[2];
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    int flag = 0;
		    while ((line = br.readLine()) != null) {
		       String[] str = line.split(" ");
		       if(flag == 0){
		    	   nodes = Integer.parseInt(str[0]);
		    	   edges = Integer.parseInt(str[1]);
		    	   matrix = new double[nodes][nodes];
		    	   transpose =  new double[nodes][nodes];
		    	  flag =1;
		       }else{
		    	   matrix[Integer.parseInt(str[0])][Integer.parseInt(str[1])] = 1;
		       }
		    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i< nodes; i++){
			for(int j = 0; j<nodes; j++){
				transpose[j][i] =  matrix[i][j];
			}
		}
		for(int i = 0; i< nodes; i++){
			for(int j = 0; j<nodes; j++){
			}
		}
		if(nodes > 10){
	 		   itt = 0;
	 		   intialvalue = -1;
	 	   }
		HitsAlgo();
	}
	
	public static void HitsAlgo(){
		double[] prevA = new double[nodes] ;
		double[] prevH = new double[nodes] ;
		int k = 0;
		hub = new double[nodes];
		auth = new double[nodes];
		double sum[] = new double[nodes];
		double stopA = 0;
		double stopH = 0;
		boolean diff = true;
		
		if(intialvalue == -1){
			intialvalue = ((double)1/nodes);
		}
		if(intialvalue == -2){
			intialvalue = ((double)1/Math.sqrt(nodes));
		}
		
		if(!(nodes>10))
			System.out.print("Base 	: "+k+" :");
		
		for(int j = 0; j < nodes; j++ ){
			hub[j] = intialvalue;
			auth[j] = intialvalue;
			prevA[j] = auth[j];
			prevH[j] = hub[j];
			if(!(nodes>10))
				System.out.printf("A/H["+j+"]="+"%.7f/%.7f ",auth[j],hub[j]);
		}
		
		if(itt < 1){
			if(itt == 0){
				erate = Math.pow(10, -5);
				itt = -1;
			}else{
				
				erate = Math.pow(10, itt);
				itt = -1;
			}
		}else{
			diff = false;
		}
		if(!(nodes>10)){
			k++;
			System.out.print("\n");
		}

		while(k <= itt || diff ){
			stopA = 0;
			stopH = 0;
			nfa = 0;
			nfh = 0;
			if(!(nodes>10))
				System.out.print("Iter 	: "+k+" :");
			for(int i = 0; i < nodes; i++ ){
				auth[i] = 0;
					for(int j = 0; j < nodes; j++){
						auth[i] = auth[i] + transpose[i][j] * hub[j]; 
				}
			}
			
			for(int i = 0; i < nodes; i++ ){
				hub[i] = 0;
				for(int j = 0; j < nodes; j++){
					hub[i] = hub[i] + matrix[i][j] * auth[j]; 
				}
			}
			
			for(int i = 0; i < nodes; i++){
				 nfa = nfa + (auth[i] * auth[i]);
				 nfh = nfh + (hub[i] * hub[i]);
			}
			
			nfa = Math.sqrt(nfa);
			nfh = Math.sqrt(nfh);
			
			for(int i = 0; i < nodes; i++){
				auth[i] = auth[i] / nfa ;
				hub[i] = hub[i] / nfh ;
				
				if((prevA[i] - auth[i]) < erate) 
				{
					stopA++;
				}
				if((prevH[i] - hub[i]) < erate)
				{
					stopH++;
				}
				prevA[i] = auth[i];
				prevH[i] = hub[i];

				if(!(nodes>10))
					System.out.printf("A/H["+i+"]="+"%.7f/%.7f ",auth[i],hub[i]);
			}
			if(!(nodes>10))
				System.out.print("\n");
			
			if((stopA == nodes) && (stopH == nodes))
				diff = false;
			k++;
		}
		
		if(nodes > 10){
			System.out.println("Iter : "+ k);
			for(int j = 0; j < nodes; j++){
				System.out.printf("A/H["+j+"]="+"%.7f/%.7f ",auth[j],hub[j]);
				System.out.print("\n");
				
			}
		}
		
	}
}
