package com.iss.dao;

import java.util.List;

import com.iss.bean.Listshow;
import com.iss.bean.User;
import com.iss.bean.address;
import com.iss.bean.did;
import com.iss.bean.didrecord;
import com.iss.bean.recover;

public interface ReuserDao {
	//1.回收员订单显示
	public List<address> ListAddress2();
	//2.订单匹配,自动派单给距离最近的回收员
	public recover selectreuser(String lng,String lat);
	//3.根据订单所选择的地址找出经纬度
	public address selectlatlng(int aid);
	//4.给匹配到的回收员插入此订单id
	public void insertdid(int c_did,int d_uid);
	//5.根据订单编号查出来订单id，作为匹配回收员使用
	public int selectdid(String listnum);
	//6.回收员openid查询
	public recover selectrec(String openid);
	//7.回收员信息提交
	public void insertreuserinfo(recover rec);
	//8.管理员完善回收员地址信息
	public void reuseraddressbyadmin(recover rec);
	//9.更改回收员状态，在职or申请不通过
	public void rechangestate(int state,int reuid);
	//10.回收员支出记录
	public List<did> reuserouts(int reuid);
	//11.回收员支出总金额
	public String reuseroutall(int reuid);
	//12.回收员完成订单
	public void didsuccess(int did,float money,int state);
	//14.显示该一个回收员已完成未完成订单
	public List<didrecord> showonelist(int reuid);
	//15.所有订单显示---管理员
	public List<Listshow>  showalllist(); 
	//16.所有state = 0回收员显示---管理员
	public List<recover>  showallreuser(); 
	//17.所有state = 1回收员显示---管理员
	public List<recover>  showallreuser1(); 
	//18.获取订单所属用户，用户回复模板消息
	public String  showuseropenid(int did); 
}
