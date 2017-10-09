package com.mit;
import com.mit.model.Team;
import com.mit.model.Player;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.List;




public class OrganizerMachine{

  private Map<String, String> mMenu;
  private BufferedReader mReader;
  private Team mTeam;


  public OrganizerMachine(Team team){

    mReader = new BufferedReader(new InputStreamReader(System.in));
    mMenu = new HashMap<String, String>();
    mTeam = team;

    mMenu.put("Create","Create a new Team");
    mMenu.put("Quit","Exit the program");
    mMenu.put("Add","Add player to a team");


  }

  private String promptAction() throws IOException{
    //System.out.printf("There are %d teams.",  );

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
          break;

          case "add":
          Player player = promtNewPlayer();
          mTeam.addPlayer(player);
          System.out.printf("%s added! %n%n", player);

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

/*
  private String promtTeam() throws IOException{
    System.out.println("Avaliable team: ");
    List<String> team = new ArrayList<>(mTeam.getTeam());
    for(String )


  }

*/


}
