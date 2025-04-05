package com.itheima;

public class Account {
    private String cardId;
    private String userName;
    private char sex;
    private String password;
    private double money;
    private double limit;

    public String getCardId(){
        return cardId;
    }
     public void setCardId(String cardId){
        this.cardId = cardId;
     }

     public String getUserName(){
        return userName + (sex == '男' ? "先生" : '女');
     }

     public void setUserName(String userName){
        this.userName = userName;
     }

     public char getSex(){
        return sex;
     }

     public void setSex(char sex){
        this.sex = sex;
     }

     public void setPassWord(String password){
        this.password = password;
     }

     public void setLimit(double limti){
        this.limit = limit;
     }

}
