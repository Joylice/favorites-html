package com.example.favorites.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by cuiyy on 2017/11/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class NetworkMonitor {


    @Test
    public void networkTest() {
        startNetworkMonitor();

        for (int i = 0; i < 60; i++) {
            System.out.println("network[" + i + 1 + "]: " + com.example.favorites.web.comm.network.NetworkMonitor.isNetworkAvailable());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    @Test
    public void startNetworkMonitor() {
      com.example.favorites.web.comm.network.NetworkMonitor networkMonitor = new com.example.favorites.web.comm.network.NetworkMonitor();
        networkMonitor.isAddressAvailable("192.168.12.157");
        Thread thread = new Thread(networkMonitor);
        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
