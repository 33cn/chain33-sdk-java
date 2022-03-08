package cn.chain33.javasdk.utils;

import io.grpc.EquivalentAddressGroup;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConfigUtil {

    /**
     * uri ip:port,ip:port
     */
    public static List<EquivalentAddressGroup> getNodes(String file) {
        List<EquivalentAddressGroup> addresses = new ArrayList<>();
        Properties properties = new Properties();
        String filePath = System.getProperty("user.dir") + "/" + file;
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(filePath));
            properties.load(in);
            String[] ipList = properties.getProperty("targetURI").split(",");
            for (int i = 0; i < ipList.length; i++) {
                String[] ipDetail = ipList[i].split(":");
                List<SocketAddress> socketAddresses = new ArrayList<>();
                socketAddresses.add(new InetSocketAddress(ipDetail[0], Integer.parseInt(ipDetail[1])));
                addresses.add(new EquivalentAddressGroup(socketAddresses));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return addresses;
    }
}
