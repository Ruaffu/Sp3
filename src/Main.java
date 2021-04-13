import java.awt.image.AreaAveragingScaleFilter;
import java.io.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static  UI ui;
    public static ArrayList<Team> teams = new ArrayList<>();
    public static ArrayList<Match> matches = new ArrayList<>();
    public static ArrayList<Team> currentTeams = new ArrayList<>();
    public static ArrayList<Match> quarterFinals = new ArrayList<>();
    public static ArrayList<Match> Finals = new ArrayList<>();
    public static ArrayList<Match> semifinals = new ArrayList<>();
    public static void main(String[] args){

        teams = readTeamData();
        ui = new UI();
        ui.mainInterface();

    }

    public int getUserInput(int msg){
        System.out.print(msg);
        Scanner scan = new Scanner(System.in);
        return scan.nextInt();
    }

    public static ArrayList<Team> readTeamData() {
        ArrayList<Team> teamList = new ArrayList<>();
        Scanner scanner = null;
        File fr = new File("src/Teams.txt");
        try {

            scanner = new Scanner(fr);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (scanner != null)
        {
            while(scanner.hasNextLine())
            {
                String teamName = scanner.nextLine();
                teamList.add(new Team(teamName));
            }
        }

        return teamList;
    }
}
