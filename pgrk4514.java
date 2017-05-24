
// KALYANI TARU CS610 4514 PRP

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class pgrk4514 {
	static double[][] matrix;
	static double PR[];
	static int C[];
	static int I[];
	static String file;
	static int nodes,edges,itt;
	static double intialvalue,erate;
	static double alpha = 0.85;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
		    	   C = new int[nodes];
		    	   I = new int[nodes];
		    	   matrix = new double[nodes][nodes];
		    	  flag =1;
		       }else{
		    	   C[Integer.parseInt(str[0])]++;
		    	   I[Integer.parseInt(str[1])]++;
		    	   matrix[Integer.parseInt(str[0])][Integer.parseInt(str[1])] = 1;
		       }
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(nodes > 10){
 		   itt = 0;
 		   intialvalue = -1;
 	   }
		pgrkAlgo();
	}
	
	public static void pgrkAlgo(){
		double[] prev = new double[nodes] ;
		int k = 0;
		PR = new double[nodes];
		double sum[] = new double[nodes];
		int stop = 0;
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
			//PR[j] = ((double)1/nodes);
			PR[j] = intialvalue;
			prev[j] = PR[j];
			if(!(nodes>10))
			System.out.printf("PR["+j+"]= "+"%.7f ",PR[j]);
		}
		
		if(itt < 1){
			//System.out.println("itt "+itt);
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
				stop = 0;
				if(!(nodes>10))
					System.out.print("Iter 	: "+k+" :");
			for(int i = 0; i < nodes; i++){
				sum[i] = 0;
				for (int j = 0; j < nodes; j++){
					if(matrix[j][i] == 1){
						//System.out.println(j+" pointing to "+i+" "+PR[j]+" "+C[j]);
						sum[i] = sum[i] + (PR[j]/C[j]);
					}
				}
			}
			for(int i = 0; i< nodes; i++){
				PR[i] = ((1 - alpha)/nodes) + alpha * (sum[i]) ;
				
				if((prev[i] - PR[i]) < erate)
				{
					stop++;
				}
				prev[i] = PR[i];

				if(!(nodes>10))
				System.out.printf("PR["+i+"]= "+"%.7f ",PR[i]);
			}
			if(!(nodes>10))
			System.out.print("\n");

			if(stop == nodes)
				diff = false;
			k++;
		}
		
		if(nodes > 10){
			System.out.println("Iter : "+ k);
			for(int j = 0; j < nodes; j++){
				System.out.printf("PR["+j+"]= "+"%.7f ",PR[j]);
				System.out.print("\n");
				
			}
		}
		
	}
}

