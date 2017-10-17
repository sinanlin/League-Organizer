package com.mit.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class Team{

  private String mteamName;
  private String mCoach;
  private Set<Player> mPlayer;


  public Team(String teamName, String coach){
    mteamName = teamName;
    mCoach = coach;
    mPlayer = new TreeSet<Player>();
  }

  public void addPlayer(Player player){
    mPlayer.add(player);
  }

  public void removePlayer(Player player){
    mPlayer.remove(player);
  }


  public String getTeamName( ){
    return mteamName;
  }

  public int getTeamPlayerCount(){
    return mPlayer.size();
  }

  public void displayPlayer(){
      for(Player player : mPlayer){
          System.out.printf(
          "NAME:%s | TEAM: %s | HEIGHT: %d | EXPERIENCE: %s %n",
          player.getPlayerName(),
          player.getPlayerTeam(),
          player.getPlayerHeight(),
          player.getPlayerExperience());
      }
  }



//Addding player by team. This should be cache
  private Map<String, List<Player>> byTeam(){
    Map<String,List<Player>> byTeam = new HashMap<>();
    for(Player player : mPlayer){
      List<Player> teamPlayer = byTeam.get(player.getPlayerTeam());
      if(teamPlayer == null){
        teamPlayer = new ArrayList<>();
        byTeam.put(player.getPlayerTeam(), teamPlayer);
      }
      teamPlayer.add(player);
    }
    return byTeam;
  }


  public List<Player> getPlayerByTeam(String team){
    return byTeam().get(team);
  }

  public Set<String> getTeam(){
    return byTeam().keySet();
  }
}
