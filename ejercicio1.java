package Final;

public class ejercicio1 {

			public static void main(String[] args) {
			int num =1;
			int par=0;
			
			while(num<=100){
				 
				 if(num% 2 ==0){
					par++;
					System.out.println(num);
			 }
			num++ ;  
			
		}
			System.out.println();
			System.out.println("cantidad de numeros par: "+par);	
	}
	}