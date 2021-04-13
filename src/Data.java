import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

public class Data
{

    public void registerTeam()
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Write your team name");
        String teamName = scan.nextLine();
        System.out.println("Write name of first player");
        String player1 = scan.nextLine();
        System.out.println("Write name of second player");
        String player2 = scan.nextLine();

        try
        {
            Team team = new Team(teamName);
            Main.teams.add(team);
            Writer output;
            output = new BufferedWriter(new FileWriter("src/Data.txt",true));
            output.append("\n");
            output.append(teamName + ":player 1 - " + player1 +":player 2 - " + player2);
            output.close();
        }
        catch (IOException e)
        {
            System.out.println("Something went wrong");
        }
        System.out.println("Welcome to the tournament " + teamName + " Your team has now been registered");

    }

    public void deleteTeam()
    {
        //Removes team from arraylist
        //todo make it remove from txt file too
        Scanner sc = new Scanner(System.in);
        System.out.println(Main.teams);
        System.out.println("Type name of team to remove");
        String teamName = sc.nextLine();

        Iterator i = Main.teams.iterator();
        Team tm;
        while(i.hasNext())
        {
            tm = (Team) i.next();
            if (tm.toString().equals(teamName))
            {
                i.remove();
                System.out.println("\nThe team " +teamName+ " was removed");
                break;
            }
        }
    }

    public void randomMatchUps(ArrayList<Team> randomTeams) // creates random match-ups of the teams
    {
        ArrayList<Team> teamsList = new ArrayList<>();
        teamsList = randomTeams;
        Collections.shuffle(randomTeams);
        for (int i = 0; i < teamsList.size()-1; i+=2)
        {
            Team team1;
            Team team2;
            team1 = teamsList.get(i);
            team2 = teamsList.get(i+1);
            Match match = new Match(team1,team2);
            Main.matches.add(match);
            System.out.println("A match between " + team1 + " and " + team2 + " has now been created");
        }

    }

    public void tournamentSchedule()
    {
        for(int i = 0; i < Main.matches.size(); i++)
        {
            System.out.println("\nMatch " + (i+1)+ " " +Main.matches.get(i).getTeam1() + " vs " +
                    Main.matches.get(i).getTeam2() + " " + Main.matches.get(i).getTeam1Goals() + "-" +
                    Main.matches.get(i).getTeam2Goals());
        }

    }

    public void allTeams()
    {
        System.out.println("Enrolled teams ");
        for (Team c : Main.teams)
        {
            System.out.println(c);
        }

    }

    public void registerMatchResult(ArrayList<Match> matches) //MANGLER
    {
        Scanner scan = new Scanner(System.in);
        int i = 0;
        while(matches.size() > i)
        {
            Match match = matches.get(i);
            Main.currentTeams = Main.teams;

            System.out.println("Match " + (i+1));
            System.out.println("Set goals for team 1 " + match.getTeam1());
            int input = scan.nextInt();
            match.setTeam1Goals(input);

            System.out.println("Set goals for team 2 " + match.getTeam2());
            int input2 = scan.nextInt();
            match.setTeam2Goals(input2);

            if(match.getTeam1Goals() > match.getTeam2Goals())
            {
                match.getTeam2().setKnockedOut(true);
            }

            else if(match.getTeam1Goals() < match.getTeam2Goals())
            {
                match.getTeam1().setKnockedOut(true);

            }

            else
            {
                System.out.println("KnockOut matches can not be a tie ");
            }

          //Removes teams that lose a knockOut game
           if (match.getTeam1().isKnockedOut() ==  true)
            {
                Main.currentTeams.remove(match.getTeam1());
            }
            else if (match.getTeam2().isKnockedOut() == true)
            {
                Main.currentTeams.remove(match.getTeam2());
            }
            i++;
        }


        System.out.println("Qualified teams: "+ Main.currentTeams + "\n");
        randomMatchUps(Main.currentTeams);

    }


    public void tournamentSim()
    {

        randomMatchUps(Main.teams);
        registerMatchResult(Main.matches);

        randomMatchUps(Main.currentTeams);
        registerMatchResult(Main.quarterFinals);

        randomMatchUps(Main.currentTeams);
        registerMatchResult(Main.semifinals);

        randomMatchUps(Main.currentTeams);
        registerMatchResult(Main.Finals);

    }

}
