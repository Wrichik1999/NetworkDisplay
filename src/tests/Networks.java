package tests;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

class Networks {

    public static void main(String[] args) {
        try {
            Collections.list(NetworkInterface.getNetworkInterfaces())
                    .forEach(net -> {
                        try {
                            System.out.println("Display Name: " + net.getDisplayName());
                            System.out.println("Name: " + net.getName());
                            System.out.println("Index: " + net.getIndex());
                            System.out.println("Hardware/MAC Address: " + fixMAC(net.getHardwareAddress()));
                            System.out.println("Inet: " + enumOf0(net.getInetAddresses()));
                            System.out.println("Interface: " + listOf0(net.getInterfaceAddresses()));
                            System.out.println("MTU: " + net.getMTU());
                            System.out.println("Loopback: " + net.isLoopback());
                            System.out.println("P2P: " + net.isPointToPoint());
                            System.out.println("Up: " + net.isUp());
                            System.out.println("Virtual: " + net.isVirtual());
                            System.out.println("Multicast Support: " + net.supportsMulticast());
                        } catch (Exception ignored) {
                        }
                        System.out.println();
                    });
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    private static String enumOf0(Enumeration inetAddresses) {
        try {
            if (inetAddresses.hasMoreElements()) {
                //noinspection unchecked
                return Collections.list(inetAddresses).toString();
            }
        } catch (Exception ignored) {
        }
        return Collections.EMPTY_LIST.toString();
    }

    private static String listOf0(List inetAddresses) {
        try {
            if (!inetAddresses.isEmpty()) {
                return inetAddresses.toString();
            }
        } catch (Exception ignored) {
        }
        return Collections.EMPTY_LIST.toString();
    }

    private static String fixMAC(byte[] address) {
        StringBuilder string = new StringBuilder();
        try {
            for (int i = 0; i < 5; i++) {
                string.append(Integer.toHexString(Byte.toUnsignedInt(address[i])));
                string.append(":");
            }
            string.append(Integer.toHexString(Byte.toUnsignedInt(address[5])));
        } catch (NullPointerException ignored) {
        }
        return string.toString();
    }
}
