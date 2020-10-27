package prueba;
import poo.primero;
public class prueba_main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

			primero p1;
			primero p2;
			primero p3;
			
			p1=new primero(1,4);
			p2=new primero(2,3);
			
			System.out.print(p1.distacia(p2));
			System.out.print(p1.cadenas());
			
		}
	

}
