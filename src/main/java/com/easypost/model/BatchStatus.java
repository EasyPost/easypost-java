package com.easypost.model;

public class BatchStatus {
	int created;
	int postagePurchased;
	int postagePurchaseFailed;
	
	public int getCreated() {
		return created;
	}
	public void setCreated(int created) {
		this.created = created;
	}

	public int getPostagePurchased() {
		return postagePurchased;
	}
	public void setPostagePurchased(int postagePurchased) {
		this.postagePurchased = postagePurchased;
	}

	public int getPostagePurchaseFailed() {
		return postagePurchaseFailed;
	}
	public void setPostagePurchaseFailed(int postagePurchaseFailed) {
		this.postagePurchaseFailed = postagePurchaseFailed;
	}
}