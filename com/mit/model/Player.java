package com.mit.model;


public class Player{

  private String mName;
  private String mExperience;
  private int mHeight;
  private String mTeam;


  public Player(String name, String experience, int height, String team){
    mName = name;
    mExperience = experience;
    mHeight = height;
    mTeam = team;
  }


 public String getPlayerName(){
   return mName;
 }

public String getPlayerExperience(){
  return mExperience;
}

public int getPlayerHeight(){
  return mHeight;
}

public String getPlayerTeam(){
  return mTeam;
}


}
