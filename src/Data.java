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

                //Removes teams that lose a knockOut game
                if (match.getTeam1().isKnockedOut() == true) {
                    Main.currentTeams.remove(match.getTeam1());
                } else if (match.getTeam2().isKnockedOut() == true) {
                    Main.currentTeams.remove(match.getTeam2());
                }
                i++;

        }
        if (Main.currentTeams.size() > 1) {
            System.out.println("Qualified teams: " + Main.currentTeams + "\n");
        } else if (Main.currentTeams.size() <= 1) {
            System.out.println("\n" + "The winner of the tournament is " + Main.currentTeams + "\n");
        }

    }


    public void tournamentSim() {
        Scanner scan = new Scanner(System.in);

            if (Main.teams.size() >= 16 && Main.currentTeams.size() == 0) {
                System.out.println("GruppeSpil");
                Main.currentTeams = Main.teams;
                teamExecute(Main.matches);
                saveGameData();
                return;

            } else if (Main.currentTeams.size() == 8) {
                System.out.println("quarterfinals");
                randomMatchUps(Main.currentTeams, Main.quarterFinals);
                teamExecute(Main.quarterFinals);
                randomMatchUps(Main.currentTeams, Main.semifinals);
                return;

            } else if (Main.currentTeams.size() == 4) {
                System.out.println("semifinals");
                teamExecute(Main.semifinals);
                randomMatchUps(Main.currentTeams, Main.Finals);
                return;
            }


            else if (Main.currentTeams.size() == 2) {
            System.out.println("finals");
            teamExecute(Main.Finals);
            return;
        }

    }
    public void registerMatches(ArrayList<Match> matchList)
    {
        while(true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Match goal register");
            for (int i = 0; i < matchList.size(); i++) {
                System.out.println((i + 1) + "" + matchList.get(i));
            }

            System.out.println("Choose a Match to register goals");
            int input = scan.nextInt();
            Match match = matchList.get(input - 1);

            System.out.println(" Match " + input);

            System.out.println("Set goals for team 1 " + match.getTeam1());
            int input2 = scan.nextInt();
            match.setTeam1Goals(input2);

            System.out.println("Set goals for team 2 " + match.getTeam2());
            int input3 = scan.nextInt();
            match.setTeam2Goals(input3);

            if(input3 == input2)
            {
                System.out.println("A match can not be tie!");
                match.setTeam1Goals(0);
                match.setTeam2Goals(0);
                System.out.println("Match have been reset to 0-0 score");
            }

            System.out.println("do you want to register again y/n");
            char input4 = scan.next().charAt(0);
            if(input4 == 'y')
            {
                System.out.println("Register a match again");
            }
            else if (input4 == 'n')
            {
                return;
            }
            else
            {
                System.out.println("That is not a valid option");
                break;
            }
        }
    }

    public static String getGameDataFromSession() {
        StringBuilder gameData = new StringBuilder();
        for (Team t : Main.currentTeams) {
            String teamData = String.format(t.getTeamName()+"\n");
            gameData.append(teamData);
        }
        return gameData.toString();
    }

    public static void saveGameData() {
        FileWriter writer = null;
        try {
            writer = new FileWriter("src/data.txt");
            writer.write(getGameDataFromSession());
        } catch (IOException e) {
            System.out.println("Couldn't instantiate the FileWriter in saveGameData()");
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (NullPointerException | IOException e) {
                System.out.println("Couldn't close the FileWriter in saveGameData()");
                e.printStackTrace();
            }
        }
    }

}
