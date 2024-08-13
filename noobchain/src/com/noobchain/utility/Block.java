package com.noobchain.utility;

import java.util.Date;

public class Block {
	/**
	 * A chain/list of block contains 1.Own Hash 2.Previous block's hash 3.It's own
	 * data(usually transactions) Hash = Digital Fingerprint
	 */

	public String hash;
	public String previousHash;
	private String data;// a simple message for now
	private long timeStamp;// as number of milliseconds since 1/1/1970
	// hashcash proof of work for preventing tampering of block and new blockchains
	// being added
	private int nonce;

	// block constructor
	public Block(String data, String previousHash) {
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		this.hash = calculateHash();
	}

	/**
	 * for the hash to have target number of trailing zeroes nonce is generally used
	 * in a proof of work concept until hash found meeting the condition,
	 * calculateHash is called iteratively with new buffer in nonce
	 */
	public void mineBlock(int difficulty) {
		// creating a string with difficulty * "0"
		String target = new String(new char[difficulty]).replace('\0', '0');
		while (!hash.substring(0, difficulty).equals(target)) {
			nonce++;
			hash = calculateHash();
		}
		System.out.println("Block Mined!! : " + hash);
	}

	/**
	 * Calculating hash from all the parts of the block we wish to hide 1.Previous
	 * Block's hash 2.Block's Data 3.Timestamp since identity/buffer for the block
	 * 
	 * @return
	 */
	public String calculateHash() {
		String calculatedHash = StringUtil
				.applySHA256(previousHash + Long.toString(timeStamp) + data + Integer.toString(nonce));
		return calculatedHash;
	}

}
