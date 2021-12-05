 import java.util.Random;
 import ansi_terminal.*;
 import java.util.Scanner;
 import java.io.PrintWriter;
 public class Boss extends Character {
         private String name;
         private int damage;
         private int protection;
         private static Random rng;
         private boolean battleActive;

         public Boss(String name, int row, int col, int hp, int damage, int protection) {
                 super(row, col, '&', Color.RED, hp);
                 this.name = name;
                 this.damage = damage;
                 this.protection = protection;
                 this.battleActive = false;
                 rng = new Random();
         }

         @Override
                 public int getDamage() {
                         return damage;
                 }
         @Override
         public void save(PrintWriter pw)
         {
                 super.save(pw);
                 pw.println(name);
                 pw.println(damage);
                 pw.println(protection);
         }

         public Boss (Scanner in)
         {
                 super (in);
		 Terminal.warpCursor(2,100);
		 System.out.print("1");
		 Terminal.pause(2);
                 this.name=in.nextLine();
                 this.damage=in.nextInt();
                 this.protection = in.nextInt();
                 this.battleActive = false;
                 rng= new Random();
         }
         @Override
                public int getProtection() {
                         return protection;
                 }
@Override
                 public String getName() {
                         return name;
                 }

         public void setBattleActive() {
                 battleActive = true;
         }

         // randomly move this enemy in the room
         public void walk(Room room) {
                 // if a battle is active with this enemy, they DONT walk right after
                 if (battleActive) {
                         battleActive = false;
                         return;
                 }

                 // loop forever until we move correctly
                 while (true) {
                         int choice = rng.nextInt(4);
                         switch (choice) {
                                 case 0:
                                         if (move(0, 1, room)) return;
                                         break;
                                 case 1:
                                         if (move(0, -1, room)) return;
                                         break;
                                 case 2:
                                         if (move(1, 0, room)) return;
                                         break;
                                 case 3:
                                        if (move(-1, 0, room)) return;
                                         break;
                         }
                 }
         }
 }

