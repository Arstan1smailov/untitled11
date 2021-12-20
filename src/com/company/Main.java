package com.company;

import java.util.Random;

public class Main {
    public boolean a;
    public static int bossHealth = 1500;
    public static int bossDamage = 50;
    public static String bossDefence = "";

    public static int[] heroesHealth = {270, 260, 250, 500, 600,350,200, 350};
    public static int[] heroesDamage = {15, 20, 25, 25, 5, 10,20, 0};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Lucky", "Golem","Berserk","Thor", "Medic"};
    public static int round_number = 1;

    public static void Thor () {
        Random random = new Random();
        boolean a = random.nextBoolean();
        for (int i = 0; i < heroesHealth.length; i++) {
            if (a){
                bossDamage = 0;
                System.out.println("Thors stun");
                break;
            }
            else{
                bossDamage = 50;
            }
        }
        }


public static void Berserk (){
    Random random = new Random();
   boolean a = random.nextBoolean();
 int Repulse = (int) Math.floor(Math.random()*((bossDamage - bossDamage/5)-10+1)+10);
    for (int i = 0; i < heroesHealth.length; i++) {
        if (a && heroesHealth[5] > 0){
            bossHealth = bossHealth - (heroesDamage[5] + Repulse);
            System.out.println("Berserks RAge");
            heroesHealth[5] = heroesHealth[5] + (bossDamage - Repulse);
            break;
        }
    }
}


    public static void Golem() {
        Random random = new Random();
        boolean a = random.nextBoolean();
        boolean b = random.nextBoolean();
        int shield = bossDamage / 5;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (i == 4) {
                continue;
            }
            if (heroesHealth[i] > 0 && heroesHealth[5] > 0 && a) {
                heroesHealth[i] = heroesHealth[i] + shield;
                System.out.println("Shield");
                if(heroesHealth[3] > 0 && a && b) {
                    if (b) {
                        heroesHealth[3] += bossDamage;
                        System.out.println("LAVAAAA1");
                        break;
                    }
                }

            }
            else if (heroesHealth[3] > 0 && b) {
                heroesHealth[3] += bossDamage;
                System.out.println("LAVAAA");
                break;
            }
        }
    }

    public static void heal() {
        Random random = new Random();
        random.nextBoolean();
        for (int i = 0; i < heroesHealth.length; i++) {
            if (i == 3) {
                continue;
            }
            if (heroesHealth[i] > 0 && heroesHealth[i] <= 100 && heroesHealth[5] > 0) {
                if (random.nextBoolean() == true) {
                    heroesHealth[i] += 80;
                    System.out.println("Medic heals " + heroesAttackType[i]);
                }
            }
        }
    }


    public static void main(String[] args) {
        printStatistics(); // До начала игры вывод статистики
        while (!isGameFinished()) {
            round();
            changeBossDefence();
            heal();
            bossHits();
            Golem();
            Berserk();
            Thor();
            heroesHit();
            printStatistics();
        }

    }


    public static void changeBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length - 1);
        bossDefence = heroesAttackType[randomIndex];
    }

    public static void round() {
        round_number++;
        bossHits();
        heroesHit();
        printStatistics();
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && heroesHealth[i] > 0) {
                bossHealth = bossHealth - heroesDamage[i];
            }
        }
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (bossHealth > 0 && bossHealth > 0) {
                if (heroesHealth[i] - heroesDamage[i] > 0) {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                } else {
                    heroesHealth[i] = 0;
                }

            }
        }
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
//        if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
//            System.out.println("Boss won!!!");
//            return true;
//        }
//        return false;
//    }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
            if (allHeroesDead == true) {
                System.out.println("Boss won!!!");
            }
        }
        return allHeroesDead;
    }

    public static void printStatistics() {
        System.out.println(round_number + " ROUND _______________");
        System.out.println("Boss health: " + bossHealth + " (" + bossDamage + ")");
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: "
                    + heroesHealth[i] + " (" + heroesDamage[i] + ")");
        }
        System.out.println("_______________");
    }
}