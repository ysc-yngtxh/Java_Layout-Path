package com.example.service;

/**
 * @author 游家纨绔
 * @dateTime 2024-05-29 22:40:00
 * @apiNote TODO
 */
public interface TransactionService {

	void saveTransaction();

	void updateNonTransactional();

	void saveAopContextTransaction();

	void saveClassTransactional();

}
