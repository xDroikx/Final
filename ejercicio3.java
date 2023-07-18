package Final;

public class ejercicio3 {

	 public static void main(String[] args) {

	        cuentabancaria[] cuenta = new cuentabancaria[2];
	        cuenta[0] = new cuentabancaria("Jorge", 1000);
	        cuenta[1] = new cuentabancaria("Mauricio", 1400);

	        for (cuentabancaria objeto : cuenta) {
	            System.out.println("El usuario: " + objeto.getNumerodecuenta() + ". " + "Posee un saldo de: " + objeto.getSaldo()+ " Pesos");
	        }
	    }
	}

	class cuentabancaria {
	    private
	    String numerodecuenta;
	    int saldo;
	    public
	    cuentabancaria(String nc, int s){
	    	numerodecuenta = nc;
	        saldo = s;

	    }
	    String getNumerodecuenta (){
	        return numerodecuenta;
	    }
	    int getSaldo() {
	        return saldo;
	    }
	}