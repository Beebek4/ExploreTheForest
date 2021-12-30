import java.util.Random;
import java.util.Scanner;

public class exploringTheForest {
	
	public static void main(String[] args) {
		Player ash = start();
		desc();
		
		while(ash.getPHealth()>0&&ash.getDexNum()!=20) {
			if((randomInt(3)+1)>1) {
				heal(ash);
			}
			else {
				adv(ash);
			}
		}
	
		if(ash.getPHealth()>0) {
			win(ash);
		}
		else {
			lose(ash);
		}
		
	}
	
	public static Animal[] sortA(Animal[] aniA) {
        int n = aniA.length;  
        Animal temp;  
        for(int i=0; i < n; i++){  
        	for(int j=1; j < (n-i); j++){  
        		if(aniA[j-1].injury > aniA[j].injury){  
	                temp = aniA[j-1];  
	                aniA[j-1] = aniA[j];  
	                aniA[j] = temp;  
        		}         
        	}  
        }
        return aniA;
	}
	
	public static void win(Player ash) {
		System.out.println("You have just finished successfully healing 20 animals!\r\n"
				+ "You've done your part for the community and can head back home :)\r\n"
				+ "********Animals Healed********\r\n(Animal Type, Injury Score Healed)\r\n");
		Animal[] sortedA = sortA(ash.getHDex());
		int len = ash.getDexNum();
		for(int i=0;i<len;i++) {
			System.out.println(sortedA[i].getType()+", "+sortedA[i].getInjury());
		}
	}
	public static void lose(Player ash) {
		System.out.println("Oh no, you've lost all of your health points and have fainted in the rainforest :(\r\n"
				+ "GAME OVER!\r\n********Animals Healed********\r\n(Animal Type, Injury Score Healed)\r\n");
		Animal[] sortedA = sortA(ash.getHDex());
		int len = ash.getDexNum();
		for(int i=0;i<len;i++) {
			System.out.println(sortedA[i].getType()+", "+sortedA[i].getInjury());
		}
	}
	
	public static Player start() {
		Scanner kb = new Scanner(System.in);
		Player ash = new Player();
		ash.pHp = 10;
		ash.hDex = new Animal[20];
		System.out.println("Welcome explorer! My name is Professor Oak. \r\nI see you've just gotten dropped off "
				+ "from the helicopter to the Island of Papua New Guinea! \r\nI have many things to teach you but "
				+ "first what is your name? ");
		ash.pName = kb.nextLine();
		System.out.println("Well it's nice to meet you Medic "+ash.getPName()+"!");
		
		return ash;
	}
	
	public static void desc() {
		Scanner kb = new Scanner(System.in);
		System.out.println("Now let's get to the situation at hand.\r\nAs you know the New Guinea Tropical Rainforest "
				+ "is inhabited by over a thousand different species. \r\nHowever, due to the recent pandemic, a new "
				+ "non-government organisation by the name of Team Rocket has been formed.\r\nThey vow to find "
				+ "a cure to the Corona virus through unwarranted methods.\r\nThey have decided to capture animals "
				+ "from the New Guinea Tropical Rainforest and experiment on them until a cure is found.\r\nThis "
				+ "has resulted in hundreds of wounded animals being left in the forest. \r\nWe need YOU to save them!"
				+ "\r\nAre you ready?");
		kb.nextLine();
		System.out.println("First things first, Healing!\r\nTo heal a wounded animal you will roll two dice."
				+ "\r\nIf your roll is greater or equal to the animal's injury score \r\nyou will successfully heal"
				+ "the animal,\r\notherwise its injury score will increase by 1 and attack you in anger\r\n"
				+ "if the animal's injury score reaches 13 it will pass away.\r\nBut be careful, you only start "
				+ "with 10 health points,\r\nif that reaches 0 you will faint.\r\nTry and look for special plants "
				+ "that increase your health points.\r\nGot it?");
		kb.nextLine();
		System.out.println("Great! Let's go!");
	}
	
	public static void findPlant(Player ash) {
		Scanner kb = new Scanner(System.in);
		Boolean check1 = false;
		while(check1==false) {
			System.out.println("You find a strange plant. Do you wish to eat it? (Yes/No)");
			String ans1 = kb.nextLine();
			if(ans1.equalsIgnoreCase("yes")) {
				check1=true;
				int healing = (randomInt(3)+1);
				System.out.println("The plant was a "+randP()+", it has healed you for "+healing+" health points!");
				ash.pHp = ash.getPHealth()+healing;
				System.out.println("Your health is now at "+ash.getPHealth()+"!");
			}
			else if(ans1.equalsIgnoreCase("no")) {
				System.out.println("You have decided to wander deeper into the forest.");
				check1=true;
			}
		}
	}
	public static void findTrap(Player ash) {
		Scanner kb = new Scanner(System.in);
		System.out.println("You've fallen into a trap set by Team Rocket, roll a 6 or higher to take no damage!");
		kb.nextLine();
		int total = dieRoll();
		if(total>=6) {
			System.out.println("You have SUCCESSFULLY escaped the trap and took no damage!");
		}
		else {
			int damage = (randomInt(3)+1);
			System.out.println("You have escape BUT have taken damage and lose "+damage+" health points!");
			ash.pHp = ash.getPHealth()-damage;
			System.out.println("Your health is now at "+ash.getPHealth()+" health points!");
		}
	}
	
	public static void adv(Player ash) {
		Scanner kb = new Scanner(System.in);
		Boolean check = false;
		System.out.println("As you walk through the rainforest "+randO()+" blocks your way.");
		
		while(check==false) {
			System.out.println("Do you wish to turn left or right? (L/R)");
			String ans = kb.nextLine();
			if(ans.equalsIgnoreCase("L")) {
				check=true;
				findPlant(ash);
			}
			else if(ans.equalsIgnoreCase("R")) {
				check=true;
				findTrap(ash);
			}
		}
	}
	
	public static void heal(Player ash) {
		Scanner kb = new Scanner(System.in);
		Boolean check1 = false;
		
		String animal = randA();
		int injury = loadedDie();
		
		System.out.println("You stumble across a wounded "+animal+", it has an "
				+ "injury score of "+injury);
		
		while(check1 == false) {
			System.out.println("Do you wish to heal the animal? (Yes/No)");
			String ans1 = kb.nextLine();
			
			if(ans1.equalsIgnoreCase("yes")) {
				check1 = true;
				Boolean check2 = false;
				while(check2==false) {
					int total = dieRoll();
					if(total>=injury) {
						System.out.println("The animal has been healed SUCCESSFULLY! It will now be okay :)");
						Animal a = new Animal();
						a.type = animal;
						a.injury = injury;	
						ash.hDex[ash.getDexNum()]=a;
						check2=true;
						System.out.println("You wander deeper into the forest.");
					}
					else{
						System.out.println("Healing was UNSUCCESSFUL and the animal's injury score has increased "
								+ "by 1.");
						injury = injury+1;
						if(injury>=13) {
							check2=true;
							System.out.println("The animal has sadly passed away :(");
							System.out.println("You wander deeper into the forest.");
						}
						else {			
							System.out.println("The animal's injury score is now "+ injury);
							int damage = (randomInt(3)+1);
							System.out.println("It attacks you in anger and you lose "+damage+" health point/s.");
							ash.pHp = ash.getPHealth()-damage;
							System.out.println("Your health is now at "+ash.getPHealth()+"!");
							
							System.out.println("Do you wish to try again? (Yes/No)");
							String ans2 = kb.nextLine();
							if(ans2.equalsIgnoreCase("no")||ash.getPHealth()<1) {
								check2=true;
							}
						}
					}
				}
			}
			else if(ans1.equalsIgnoreCase("no")) {
				System.out.println("You have decided to wander deeper into the forest.");
				check1 = true;
			}
		}
	}
	
	public static int dieRoll() {
		System.out.println("Rolling...");
		final int faces = 6;//Number of faces on a die is always the same
		int die1 = randomInt(faces)+1;
		int die2 = randomInt(faces)+1;//Two different dice are rolled
		int total = die1+die2;//Sum is calculated
		System.out.println("You have rolled a total of "+total);
		
		return total;
	}
	
	
	public static int loadedDie() {
		int roll = randomInt(100)+1;
		
		if(roll>97) {
			return 10;
		}
		else if(roll>80) {
			return 2;
		}
		else if(roll>60) {
			return 3;
		}
		else if(roll>40) {
			return 4;
		}
		else if(roll>=0) {
			return 5;
		}
		return 6;
		
	}
	
	public static String randA() {
		String[] animals = {"Giant Bandicoot","Black-spotted Cuscus","Tree Kangaroo","Northern Glider",
				"New Guinea Big-eared Bat","Cassowary","Kookaburra","Magpie","Lorikeet","Marine Toad",
				"Mongrel","Monitor Lizard","Moorhen","River Turtle","Otter"};
		
		return animals[randomInt(animals.length-1)];
	}
	
	public static String randP() {
		String[] plants = {"Rungia","Waterdropwort","Blackberried Nightshade","Nasturtium schlechteri",
				"Kangkong","Watercress","Wandering Jew","Dicliptera","Waterleaf","Valanguar","Fig leaves",
				"Highlands kapiak","Basella","Comfrey"};
		
		return plants[randomInt(plants.length-1)];
	}
	
	public static String randO() {
		String[] obstacles = {"a monstrous grey boulder","a large ancient tree", "a wide flowing river"};
		
		String obstacle = obstacles[randomInt(obstacles.length-1)];
		
		return obstacle;
	}
	
	public static int randomInt(int bound) {
		Random r = new Random();
		return r.nextInt(bound);
	}
		
}
