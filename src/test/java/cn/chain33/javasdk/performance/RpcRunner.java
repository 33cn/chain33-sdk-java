package cn.chain33.javasdk.performance;

import java.io.IOException;

import cn.chain33.javasdk.client.RpcClient;

public class RpcRunner implements Runnable{
	    
    String numberTh;
    RpcClient clientTh;
    
    public RpcRunner(String number, RpcClient client) {
    	numberTh =number;
    	clientTh = client;
    }

	@Override
	public void run() {
       String hash = "0x441e91ff13f28fe104d66db4308ab12652868eaf8a0011dec2059a4be98bdfb3";
        long start = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {

			try {
				clientTh.queryTx(hash);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            if (i == 999) {
        		long end = System.currentTimeMillis();
        		long total = end - start;
            	System.out.println("Thread" + numberTh + " | cost time is: " + total);
            }
		}
	}

}
