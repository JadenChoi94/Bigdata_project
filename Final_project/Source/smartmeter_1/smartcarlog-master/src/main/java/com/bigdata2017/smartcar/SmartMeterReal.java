package com.bigdata2017.smartcar;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Iterator;

public class SmartMeterReal
{
	private  String smartmeterno;
	private  int customno; 
	private  double kw;
	private  String macaddr;
	private  Random r =  new Random();
	public SmartMeterReal(){}
	
	public SmartMeterReal(String smartmeterno, int customno, double kw, String macaddr) {
		super();
		this.smartmeterno = smartmeterno;
		this.customno = customno;
		this.kw = kw;
		this.macaddr = macaddr;
	}
	
	public String getSmartmeterno(int num)
	{		
		String[] carNumPrefix = {"A", "B" , "C" , "D" , "E" , "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"}; 
		String prefixNum = carNumPrefix[randomRange(0, 25)] ;

		DecimalFormat format = new DecimalFormat("0000");
		String carNum = format.format(num);

		return smartmeterno = prefixNum + carNum;
		
	}
	public void setSmartmeterno(String smartmeterno) {
		this.smartmeterno = smartmeterno;
	}
	
	public int randomRange(int n1, int n2) {  
		return  (int)(Math.random() * (n2 - n1 + 1)) + n1;
	} 
	
	public int getCustomno() {
		return customno;
	}
	
	public void setCustomno(int customno) {
		this.customno = customno;
	}
	
	
	public double getKw(String date ) { //가중치 추가
		Random r      = new Random();		
		String month  = date.substring(4,5);
		int monthnum  = Integer.parseInt(month);
		double month_value = 0;
		
		//(평균*배율)
		if(monthnum >= 1 && monthnum <= 3){
			month_value = 1.06;
		}
		else if(monthnum >= 4 && monthnum <= 7){
			month_value = 0.96;
		}
		else if(monthnum >= 8 && monthnum <= 9){
			month_value = 1.03;
		}
		else {
			month_value = 0.97;
		}
		
//		(표준편차 * r.nextGaussian()) + 평균	
		
		double family_value = 0;
		double famNo = (double)((Math.abs(random()*100+1)));
		
		//1명일때
        if(famNo >=1 && famNo <= 14.59) {
        	family_value = 0.583569405;
        }
        //2명일때
        else if (famNo > 14.59 && famNo <= 43.05){
        	family_value = 0.818696884;
        }
        //3명일때
        else if (famNo > 43.05 && famNo <= 67.17){
        	family_value = 0.909348442;
        }
        //4명 일때
        else if (famNo > 67.17 && famNo <= 92.44){
        	family_value = 0.957507082;
        }
        //5명 일때
        else if (famNo > 92.44 && famNo <= 98.84){
        	family_value = 1.005665722;
        }
        //6명 일때
        else if (famNo > 98.84 && famNo <= 99.74){
        	family_value = 1.110481586;
        }
        //7명 일때
        else if (famNo > 99.74 && famNo <= 99.96){
        	family_value = 1.099150142;
        }
        //8명 일때
        else if (famNo > 99.96 && famNo < 99.99) {
        	family_value = 1.314447592;
        }
        //9명 일때
        else {
        	family_value = 1.209631728;
        }	
		
        //시간당임
		return Math.abs( r.nextDouble()%0.696090121 + 0.271299909 ) * ( month_value + family_value );			
		/** 
		 * 가족수와 달 별로 가중치를 더 부가하고 싶다.
		 * */	
		
//		double family_value  = 0;
//		if(familynum == 1){
//			if(monthnum == 1){
//				family_value = ;
//			}else if(monthnum == 2){
//				family_value = ;

	}	
	
	private int random() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setKw(double kw) {
		this.kw = kw;
	}
	public String getMacaddr() {
		return this.genMacAdd();
	}
	public void setMacaddr(String macaddr) {
		this.macaddr = macaddr;
	}
	
	public String genMacAdd(){
	    Random rand = new Random();
	    byte[] macAddr = new byte[6];
	    rand.nextBytes(macAddr);

	    macAddr[0] = (byte)(macAddr[0] & (byte)254);  

	    StringBuilder sb = new StringBuilder(18);
	    for(byte b : macAddr){
	        if(sb.length() > 0)
	            sb.append(":");
	        sb.append(String.format("%02x", b));
	    }
	   
	    return sb.toString();
	}
	

	

}
