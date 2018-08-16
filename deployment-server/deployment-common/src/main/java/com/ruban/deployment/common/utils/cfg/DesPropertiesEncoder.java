package com.ruban.deployment.common.utils.cfg;

import com.ruban.deployment.common.utils.encrypt.DES3;

/**
 * DES实现
 * @author tendy
 *
 */
public class DesPropertiesEncoder implements PropertiesEncoder {
    
    /**
     * the key
     */
    private static final String KEY = "(.7dcez#328%f-zkl;-xct3-{}32savc345,@%$)";

    public String encode(String str) {
        String s = "";
		try {
			s = DES3.encrypt(str, KEY);
		} catch (Exception e) {
			throw new RuntimeException("加密错误", e);
		}
        return s;
    }

    public String decode(String str) {
        String s = "";
		try {
			s = DES3.decrypt(str, KEY);
		} catch (Exception e) {
			throw new RuntimeException("解密错误: " + str, e);
		}
        return s;
    }
    
    public static void main(String[] args) {
//    	int[] year = new int[]{};
//    	for(int i=0;i<365;i++){
//    		year[i] = i;
//    	}
//		
//		System.out.println(year[200]);
    	
    	
    	
    	
    	String a = "Ab";
    	System.out.println(a.toLowerCase());
    	System.out.println(a.toUpperCase());
//    	
//    	
//    	
//    	
//    	
//    	DesPropertiesEncoder ed = new DesPropertiesEncoder();
//        System.out.println(ed.encode("l6476RYjHDc4nvSnRrBAwbqU0B+Dyz2z"));
//        System.out.println(ed.encode("hSDv43PZI3qKf7NYom18eWhtzsEmgPtt"));
//        System.out.println(ed.encode("http://218.17.248.249:6810/services/o2oinbound"));
//        System.out.println(ed.encode("y8aDa@13nfs"));
//        System.out.println(ed.encode("127.0.0.1:6379"));
//        
//        System.out.println(ed.decode("Cjh1KDAeMGUV18poq2koPg=="));
    }
}
