package com.iss.until;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/*import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import com.sun.javafx.image.ByteToBytePixelConverter;*/

public class SignUtil {
	/*
	*请求检验工具类
	*
	*
	*@author：zhenxing
	*date：2018-2-8
	*/
	private static String token="weixinleaders";
	/*
	*校验签名
	*
	*
	*@param signature 微信加密签名
	*@param timestamp 时间戳
	*@param nonce 随机数
	*@return
	*/
	public static boolean checkSignature(String signature,String timetamp,String nonce){
		//对三个参数记性字典排序
		String[] paramArr = new String[]{token,timetamp,nonce};
		Arrays.sort(paramArr);
		//将排序后的结果拼接成一个字符串
		String content = paramArr[0].concat(paramArr[1]).concat(paramArr[2]);
		String ciphertext = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			//对拼接后的字符串进行sha1加密
			byte[] digest = md.digest(content.toString().getBytes());
			System.out.println("test1");
			 
			ciphertext = byteToStr(digest);
			System.out.println("ciphertext"+ciphertext);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(ciphertext+"0.0"+signature);

		return ciphertext!=null ? ciphertext.equals(signature.toUpperCase()):false;

	}
	private static String byteToStr(byte[] byteArray){
		String strdigest = "";
		for (int i = 0; i < byteArray.length; i++) {
			strdigest+=byteToHexStr(byteArray[i]);
		}
		return strdigest;
		
	}
	private static String byteToHexStr(byte mByte){
		char[] Digit = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];
		String s = new String(tempArr);
		
		return s;
		
		
	}
	
}
