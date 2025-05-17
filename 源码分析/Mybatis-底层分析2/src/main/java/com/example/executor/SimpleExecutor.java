package com.example.executor;

import com.example.handler.StatementHandler;

public class SimpleExecutor implements Executor {

	@Override
	public <T> T query(String statement, Object[] parameter, Class<T> pojo) {
		StatementHandler statementHandler = new StatementHandler();
		return statementHandler.query(statement, parameter, pojo);
	}

}
