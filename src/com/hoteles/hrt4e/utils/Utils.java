/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoteles.hrt4e.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tadeo-developer
 */
public class Utils {
    
    public static String getMacAddress(){
    
        StringBuilder sb= new StringBuilder();
         InetAddress ip;
        try {
            ip = InetAddress.getLocalHost();
        
		System.out.println("Current IP address : " + ip.getHostAddress());

		NetworkInterface network = NetworkInterface.getByInetAddress(ip);

		byte[] mac = network.getHardwareAddress();

		System.out.print("Current MAC address : ");

		
		for (int i = 0; i < mac.length; i++) {
                    sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
		}
		System.out.println(sb.toString());
        }catch (UnknownHostException ex) {
            System.out.println("UnknownHostException: "+ex);
        } catch (SocketException ex) {
            System.out.println("SocketException: "+ex);
        }
        
        return sb.toString();
        
    }
    
}
