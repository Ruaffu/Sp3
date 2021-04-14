import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;
public class UI
{
    Scanner interfaceScan = new Scanner(System.in);
    int userInput;
    Data data = new Data();

    public void mainInterface()
    {

        System.out.println("MAIN INTERFACE");
        System.out.println("1.Team menu");
        System.out.println("2.Match menu");
        System.out.println("3.Tournament menu");
        userInput = interfaceScan.nextInt();

        switch (userInput)
        {
            case 1:
                teamMenu();
                break;
            case 2:
                matchMenu();
                break;
            case 3:
                tournamentMenu();
                break;
            default:
                System.out.println("not a valid option");
        }

    }

    public void teamMenu()
    {
        System.out.println("TEAM MENU");
        System.out.println("1.Register team");
        System.out.println("2.Delete team");
        System.out.println("3.View all teams registered");
        System.out.println("4.Back to menu");
        userInput = interfaceScan.nextInt();

        switch (userInput)
        {
            case 1:
                data.registerTeam();
                mainInterface();
                break;
            case 2:
                data.deleteTeam();
                System.out.println(Main.teams);
                mainInterface();
                break;
            case 3:
                data.allTeams();
                mainInterface();
                break;
            case 4:
                mainInterface();
                break;
            default:
                System.out.println("Not a valid option");

        }
    }

    public void matchMenu()
    {
        System.out.println("MATCH MENU");
        System.out.println("1.Create Matches");
        System.out.println("2.Register match results");
        System.out.println("3.Back to menu");
        userInput = interfaceScan.nextInt();

        switch (userInput)
        {
            case 1:
                data.randomMatchUps(Main.teams);
                mainInterface();
                break;
            case 2:
                data.registerMatchResult();
                mainInterface();
                break;
            case 3:
                mainInterface();
                break;
            default:
                System.out.println("Not a valid option");

        }

    }

    public void tournamentMenu()
    {
        System.out.println("TOURNAMENT MENU");
        System.out.println("1.Tournament placements");
        System.out.println("2.Tournament match schedule");
        System.out.println("3.Back to menu");
        userInput = interfaceScan.nextInt();

        switch (userInput)
        {
            case 1:
                tournamentPlacement();
                mainInterface();
                break;
            case 2:
                data.tournamentSchedule();
                mainInterface();
                break;
            case 3:
                data.tournamentSim();
                break;
            default:
                System.out.println("Not a valid option");

        }


    }



    public void tournamentPlacement()       //MANGLER
    {

    }

}

