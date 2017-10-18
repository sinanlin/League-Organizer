package com.mit;
import com.mit.model.Team;
import com.mit.model.Player;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.Queue;


public class OrganizerMachine{

  private Team mTeam;
  private BufferedReader mReader;
  private List<Team> mTeams;

  private Queue<Team> mTeamQueue;
  private Map<String, String> mMenu;


  public OrganizerMachine(Team team){

    mReader = new BufferedReader(new InputStreamReader(System.in));
    mMenu = new HashMap<String, String>();
    mTeam = team;
    mTeams = new ArrayList<Team>();

    mMenu.put("Create","Create a new Team");
    mMenu.put("Add","Add player");
    mMenu.put("Remove","Remove player from the team");
    mMenu.put("Display","Display all player");
    mMenu.put("Choose","Choose a team");
    mMenu.put("Report","Display height report");
    mMenu.put("Quit","Exit the program");

  }


  public void run(){
    String choice = "";

    do{
      try{
        choice = promptAction();
        switch(choice){
          case "create":
          Team team = promtNewTeam();
          mTeams.add(team);
          break;

          case "add":
          Player player = promtNewPlayer();
          mTeam.addPlayer(player);
          break;


          case "remove":
          Player playerToRemove =  promtNewPlayer();
          mTeam.removePlayer(playerToRemove);
          break;

          case "display":
          mTeam.displayPlayer();
          break;

          case "choose":
          Team ChoosenTeam = promtTeam();
          System.out.printf("The team you chose: %s %n", ChoosenTeam.getTeamName());
          Player ChoosenPlayer = promtPlayerByTeam(ChoosenTeam.getTeamName());
          System.out.printf("The player you chose: %s %n", ChoosenPlayer.getPlayerName());

          break;

          case "report":
          System.out.printf("Which team report you would like to see?%n");
          Team HChoosenTeam = promtTeam();
          System.out.printf("The team you chose: %s %n", HChoosenTeam.getTeamName());
          displayHeightReport(HChoosenTeam.getTeamName());
          break;


          case "quit":
          System.out.println("Thanks for playing!");
          break;

          default:
          System.out.printf("Unknown choice: '%s'. Try again. %n%n",choice);
          break;
        }

      }catch(IOException ioe){
        System.out.println("Ploblem with input");
        ioe.printStackTrace();
      }
    }while(!choice.equals("quit"));

  }

  private String promptAction() throws IOException{
    //System.out.printf("There are %d teams.",  );
    System.out.println("There are "+ mTeams.size()+" team avaliable");

    for(Map.Entry<String, String> option : mMenu.entrySet()){
      System.out.printf("%s - %s %n",
                        option.getKey(),
                        option.getValue());
    }


    System.out.print("What would you like to do: ");
    String choice = mReader.readLine();
    return choice.trim().toLowerCase();

  }

  private Team promtNewTeam() throws IOException{
    System.out.print("Enter the team name: ");
    String teamName = mReader.readLine();
    System.out.print("Enter the coach name: ");
    String coachName = mReader.readLine();
    return new Team(teamName, coachName);
  }

  private Player promtNewPlayer() throws IOException{
    System.out.print("Enter the player name: ");
    String playerName = mReader.readLine();
    System.out.print("Enter the experience: ");
    String playerExperience = mReader.readLine();
    System.out.print("Enter the player height: ");
    String height = mReader.readLine();
    int playerHeight = Integer.parseInt(height.trim());
    System.out.print("Enter the player team: ");
    String playerTeam = mReader.readLine();
    return new Player(playerName, playerExperience, playerHeight, playerTeam);
  }

  private Team promtTeam() throws IOException{
    System.out.println("Avaliable Teams:");

    int index = promtForIndex(mTeams);

    return mTeams.get(index);

  }

  private int promtForIndex(List<Team> teams) throws IOException{
    int counter = 1;

    for(Team team : teams){
      System.out.printf("%d.)  %s %n", counter, team.getTeamName());
      counter++;
    }

    System.out.print("Your choice:  ");
    String optionAsString = mReader.readLine();
    int choice = Integer.parseInt(optionAsString.trim());
    return choice - 1;

  }

  private Player promtPlayerByTeam(String team) throws IOException{
      List<Player> players = mTeam.getPlayerByTeam(team);

      System.out.printf("Avaliable player in the team %s: %n", team);
      int index = promtforPlayerIndex(players);
      return players.get(index);
  }


  private int promtforPlayerIndex(List<Player> players) throws IOException{
    int counter =1;

    for(Player player : players ){
      System.out.printf("%d.)  Name:%s| Height:%s| Experience: %s %n",
                        counter,
                        player.getPlayerName(),
                        player.getPlayerHeight(),
                        player.getPlayerExperience());
      counter++;
    }
    System.out.print("Your choice: ");
    String optionAsString = mReader.readLine();
    int choice = Integer.parseInt(optionAsString.trim());
    return choice - 1;
  }

  private void displayHeightReport(String team){

    List<Player> players = mTeam.getPlayerByTeam(team);
    Map<Integer,String> hReport = new TreeMap<>();

    for(Player player : players){
      hReport.put(player.getPlayerHeight(),player.getPlayerName());
    }

    for(Map.Entry<Integer,String> entry: hReport.entrySet()){
      System.out.printf("Height:%s, Name:%s  %n",
                        entry.getKey(),
                        entry.getValue());
    }
  }

}
