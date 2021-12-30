
public class Player {

	String pName;
	int pHp;
	Animal[] hDex;
	
	public String getPName() {
		return pName;
	}
	
	public int getPHealth() {
		return pHp;
	}
	
	public Animal[] getHDex() {
		return hDex;
	}
	
	public int getDexNum() {
		int counter = 0;
		Animal[] a = hDex;
		for (int i = 0; i < a.length; i ++) {
			 if (a[i] != null) {
				  counter ++;
			 } 
		}
	   return counter;
	}
	
	
	
}
