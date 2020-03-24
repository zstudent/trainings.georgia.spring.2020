package lesson200324;

import utils.Utils;

public class UseSingletonExample {
	public static void main(String[] args) {
		
		System.out.println("start");
		
		IvoryTower tower;
		
		System.out.println("before 1st pause");
		Utils.pause(5000);
		
		IvoryTower.count++;
		
		System.out.println("before 2nd pause");
		
		Utils.pause(5000);
		
		tower = IvoryTower.getInstance();
		tower = IvoryTower.getInstance();
		
	}

}
