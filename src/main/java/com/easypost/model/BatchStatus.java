package com.easypost.model;

public class BatchStatus {
	int created;
	int creationFailed;
	int postagePurchased;
	int postagePurchaseFailed;
	
	public int getCreated() {
		return created;
	}
	public void setCreated(int created) {
		this.created = created;
	}

	public int getCreationFailed() {
		return creationFailed;
	}
	public void setCreationFailed(int creationFailed) {
		this.creationFailed = creationFailed;
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
