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

  private Team mTeam;
  private BufferedReader mReader;
  private List<Team> mTeams;

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




}
