package main;

import main.game.Game;

import java.util.Scanner;

public class Execute {
	public static void main(String[] args) {
		/*Scanner sc = new Scanner(System.in);
		int option = -1;

		while(option != 0){
			System.out.println("\nEscolha o método de jogo para o robô:");
			System.out.println("1. Aleatório");
			System.out.println("2. Menor Distância");
			System.out.println("3. Número de Objetos");
			System.out.println("4. Voting");
			System.out.println("5. Árvore Binária");
			System.out.println("0. Sair");
			System.out.print("Digite a opção desejada: ");
			System.out.println();

			option = sc.nextInt();

			if (option == 1) {
				Game gSort = new Game("sort");
				gSort.run();
			}else if (option == 2) {
				Game gShortest = new Game("short");
				gShortest.run();
			}else if (option == 3) {
				Game gFObstacle = new Game("fewer");
				gFObstacle.run();
			}else if (option == 4) {
				Game gVoting = new Game("voting");
				gVoting.run();
			}else if (option == 5) {
				Game gBinaryTree = new Game("binary");
				gBinaryTree.run();
			}else if (option == 0) {
				System.out.println("Wumpus se despede...");
				break;
			}else {
				System.out.println("Opção inválida. Tente novamente.");
			}

		}*/

		int totalSuccesses = 0;

		for (int i = 0; i < 1; i++) {
			Game game = new Game("sort");
			if (game.run()) {
				totalSuccesses++;
			}
		}

		System.out.println("O método encontrou o tesouro " + totalSuccesses + " vezes em 100 execuções.");
	}
}
