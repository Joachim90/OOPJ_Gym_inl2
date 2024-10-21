package Uppgift2;

import java.util.ArrayList;
import java.util.List;

public class MainProg {
    MainProg(){
        ReadAndWrite rw = new ReadAndWrite();
        MemberUtil mu = new MemberUtil();
        String pathToRead = "src/Uppgift2/members.txt";
        String message = "";

        //Skapa en lista av medlemmar genom att läsa in texfilen med hjälp av readFileCreateMemberList metoden
        List<Member> memberList = new ArrayList<>(rw.readFileCreateMemberList(pathToRead));

        //Fråga efter namn eller personnummer och skriv ut lämpligt meddelande
        //Skriver till PTns textfil ifall det är en betalande medlem.
        while (true){
            System.out.println("Ange namn på personen som ska träna.\nAvsluta genom att skriva Hejdå");
            message = mu.isPersonMember(memberList);
            System.out.println(message);
        }
    }

    public static void main(String[] args) {
        MainProg mainProg = new MainProg();
    }
}
