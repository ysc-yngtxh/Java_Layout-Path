package com.example.service;

/**
 * @author 游家纨绔
 * @dateTime 2023-11-11 22:08
 * @apiNote TODO
 */
public class BService {
    private AService aService;

    public void setAService(AService aService) {
        this.aService = aService;
    }

    public BService() {
    }

    public BService(AService aService) {
        this.aService = aService;
    }
}
