import com.mit.model.Player;
import com.mit.model.Team;
import com.mit.OrganizerMachine;

public class Organizer{

  public static void main(String[] args){


    String teamName = null;
    String coachName = null;

    Team team = new Team(teamName,coachName);

    OrganizerMachine machine = new OrganizerMachine(team);

    machine.run();

  }

}
