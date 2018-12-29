package com.iss.dao;

 

 

import java.util.List;

import com.iss.bean.User;
import com.iss.bean.address;
import com.iss.bean.did;
import com.iss.bean.didrecord;

public interface UserDao {
    //1.用户地址栏管理---显示
	public List<address> ListAddress(int uid);
	//2.用户地址栏管理---修改
	public void ReviseAddress(address add);
	//3.用户地址栏管理---删除
	public void DeleteAddress(int aid);
	//4.用户地址栏管理---添加
	public void SupAddress(address add);
	//5.用户订单的显示
	public List<didrecord> ListDid(int uid);
	//6.用户订单的提交
	public void SendDid(did dd);
	//7.用户订单状态的修改
	public void ReviseDid(int state,int did);
	//8.显示用户总金额
	public String CollectMoney(int uid);
	//9.用户收入显示，显示属性有订单编号，订单时间和金额
	public List<did> MoneyDetail(int uid);
	//10.用户订单金额
	public void AddMoney(int money,int did);
	//11.用户地址栏管理---显示单一地址
	public address oneAddress(int aid);
	//12.微信用户登录确认
	public User checkuser(String openid); 
	//13.如果数据库查不到该用户的openid，就进行插入。
	public void InsertUser(String openid);
}
