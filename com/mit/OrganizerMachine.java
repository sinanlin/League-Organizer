package com.mit;
import com.mit.model.Team;
import com.mit.model.Player;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;


public class OrganizerMachine{

  private Map<String, String> mMenu;
  private BufferedReader mReader;
  private Team mTeam;
  private List<Team> mTeams;


  public OrganizerMachine(Team team){

    mReader = new BufferedReader(new InputStreamReader(System.in));
    mMenu = new HashMap<String, String>();
    mTeam = team;
    mTeams = new ArrayList<Team>();

    mMenu.put("Create","Create a new Team");
    mMenu.put("Quit","Exit the program");
    mMenu.put("Add","Add player to a team");
    mMenu.put("Remove","Remove player from the team");


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

  public void run(){
    String choice = "";

    do{
      try{
        choice = promptAction();
        switch(choice){
          case "create":
          Team team = promtNewTeam();
          //Fix the double entry;
          mTeams.add(team);
          break;

          case "add":
          Player player = promtNewPlayer();
          String selectedTeam = player.getPlayerTeam();
          System.out.printf("selectedTeam %s",selectedTeam);

          for(Team t : mTeams ){
            System.out.printf("t value %s",t.getTeamName());

            if(t.eqauls(selectedTeam)){
              t.addPlayer(player);
              t.getPlayerName();
            }
            else{
              System.out.println("Team not exist yet");
            }

          }


          break;

          case "remove":
          Player playerToRemove = promtForPlayer();
          mTeam.removePlayer(playerToRemove);
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

  private Team promtNewTeam() throws IOException{
    System.out.print("Enter the team name: ");
    String teamName = mReader.readLine();
    System.out.print("Enter the coach name: ");
    String coachName = mReader.readLine();
    return new Team(teamName, coachName);
  }

/*
  private Team promtForSelectTeam()throws IOException{
    System.out.print("Which team do you want the player to be at? ");
    String teamSelect = mReader.readLine();

    for(Team team : mTeams){
        if(team.getTeamName() == teamSelect){
          return team;
        }
        else{
          System.out.println("This team does not exist yet.");
        }
    }
    return null;
  }
*/

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

  //This promt should get the player's name and return a player in the list. Then
  private Player promtForPlayer() throws IOException{
    System.out.print("What's the name of the player that you want to remove?%n");
    String playerName = mReader.readLine();

    System.out.print("Which team is the player on?");
    String playerTeamName = mReader.readLine();


    for(Player player : mTeam.getPlayerByTeam(playerTeamName)){
      if(player.getPlayerName() == playerName){
        System.out.println("Successfully removed!");
        return player;
      }
      else{
        System.out.println("This player is not in the team");
        return null;
      }
    }

    return null;
  }





}
