/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.x.spesometro.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
/**
 *
 * @author Luca
 */
public class Rete {

    public static ArrayList<String> stampaIp() throws Exception {
//        InetAddress localHost = InetAddress.getLocalHost();
//        printInetAddress("localHost", localHost);
//        String hostName = localHost.getHostName();
//        String canonicalHostName = localHost.getCanonicalHostName();
//        printByName("  by" + hostName, hostName);
//        printByName("  by" + canonicalHostName, canonicalHostName);
//
//        System.out.println();
//
//        System.out.println("Full list of Network Interfaces:");
        ArrayList<String> listaIp = new ArrayList<>();
        Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
        if (en == null) {
            System.out.println("got null from NetworkInterface.getNetworkInterfaces()");
        } else {
            for (int networkInterfaceNumber = 0; en.hasMoreElements(); networkInterfaceNumber++) {
                NetworkInterface intf = en.nextElement();

                System.out.println();
                String ifaceId = "networkInterface[" + networkInterfaceNumber + "]";
                System.out.println("  " + ifaceId + ".name: " + intf.getName());
                System.out.println("  " + ifaceId + ".displayName: " + intf.getDisplayName());

                Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();
                for (int addressNumber = 0; enumIpAddr.hasMoreElements(); addressNumber++) {
                    InetAddress ipAddr = enumIpAddr.nextElement();
                    System.out.println();
                    printInetAddress("    " + ifaceId + ".address[" + addressNumber + "]", ipAddr);

                    String hostAddress = ipAddr.getHostAddress();
                    if (hostAddress.length() <= 18 && !hostAddress.contains(":")) {
                        if (hostAddress.equals("127.0.0.1")) {
                            hostAddress = "registrazioni ditte su questo pc";
                        }
                        listaIp.add(hostAddress);
                    }
                }
            }
            listaIp.add("registrazioni ditte su pc VINCENZA");
        }
        return listaIp;
    }

    private static void printByName(String prefix, String canonicalHostName) throws UnknownHostException {
        System.out.println();
        InetAddress[] allMyIps = InetAddress.getAllByName(canonicalHostName);
        for (int i = 0; i < allMyIps.length; i++) {
            String subPrefix = prefix + "[" + i + "]";
            System.out.println(subPrefix);
            System.out.println();
            InetAddress myAddress = allMyIps[i];
            printInetAddress("  " + subPrefix, myAddress);
        }
    }

    private static void printInetAddress(String prefix, InetAddress myAddress) {
        System.out.println(prefix + ".toString: " + myAddress);
        System.out.println(prefix + ".hostName: " + myAddress.getHostName());
        System.out.println(prefix + ".canonicalHostName: " + myAddress.getCanonicalHostName());
        System.out.println(prefix + ".getHostAddress: " + myAddress.getHostAddress());
    }

}
