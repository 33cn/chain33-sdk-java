package cn.chain33.javasdk.performance;

import cn.chain33.javasdk.client.RpcClient;

public class RpcQPS {

    static String ip = "localhost";
    static RpcClient client = new RpcClient(ip, 8801);

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            Thread newThread = new Thread(new RpcRunner(String.valueOf(i), client));
            newThread.start();
        }
    }
}
