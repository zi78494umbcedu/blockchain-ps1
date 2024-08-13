package com.noobchain.main;

import java.util.ArrayList;

import com.google.gson.GsonBuilder;
import com.noobchain.utility.Block;

public class noobChain {

	public static int difficulty = 5;

	public static ArrayList<Block> blockchain = new ArrayList<>();

	public noobChain() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		blockchain.add(new Block("First block", "0"));
		System.out.println("Trying to mine block 1..");
		blockchain.get(0).mineBlock(difficulty);

		blockchain.add(new Block("Second block", blockchain.get(blockchain.size() - 1).hash));
		System.out.println("Trying to mine block 2..");
		blockchain.get(1).mineBlock(difficulty);

		blockchain.add(new Block("Third block", blockchain.get(blockchain.size() - 1).hash));
		System.out.println("Trying to mine block 3..");
		blockchain.get(2).mineBlock(difficulty);

		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
		System.out.println("\nThe blockchain: ");
		System.out.println(blockchainJson);

//		// adding block to our blockchain arraylist
//		blockchain.add(new Block("First Block", "0"));
//		blockchain.add(new Block("Second Block", blockchain.get(blockchain.size() - 1).hash));
//		blockchain.add(new Block("Third Block", blockchain.get(blockchain.size() - 1).hash));
//
//		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
//		System.out.println(blockchainJson);
//
//		// System.out.println(isChainValid());
//		// testing basic blocks
////		Block genesisBlock =new Block("First Block", "0");
////		System.out.println("hash for block 1: "+genesisBlock.hash);	
////		
////		Block secondBlock =new Block("Second Block", genesisBlock.hash);
////		System.out.println("hash for block 2: "+secondBlock.hash);	
////
////		Block thirdBlock =new Block("Third Block", secondBlock.hash);
////		System.out.println("hash for block 3: "+thirdBlock.hash);	

	}

	public static Boolean isChainValid() {
		Block previousBlock;
		Block currentBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		// loop through blockchain to check hashes
		for (int i = 1; i < blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i - 1);
			// compare registered hash and calculated hash
			if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
				System.out.println("Current hashes not equal");
				return false;
			}
			// compare previous hash and registered previous hash
			if (!previousBlock.hash.equals(previousBlock.calculateHash())) {
				System.out.println("Previous hashes not equal");

				return false;
			}
			// check if hash is solved
			/**
			 * Conditions to be checked ideally
			 * 1.Blockchain should be invalid
			 * 2.Shouldn't be able to create a longer blockchain
			 * 3.Honest blockchains should have time advantage on the longest blockchain
			 */
			if (!currentBlock.hash.substring(0, difficulty).equals(hashTarget)) {
				System.out.println("This block hasn't been mined.");
				return false;

			}
		}
		return true;
	}

}
