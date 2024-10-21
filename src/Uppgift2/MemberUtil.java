package Uppgift2;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MemberUtil {

    private final String pathToWrite = "src/Uppgift2/PT_Logfile.txt";

    //Läs in personnummer, namn och datum för senaste betalning av medlemsskap
    public String readPersonalIdentityNumber(String input){
        return input.split(",")[0].trim();
    }
    public String readName(String input){
        return input.split(",")[1].trim();
    }
    public LocalDate readDateOfMembership(String input){
        return LocalDate.parse(input);
    }

    //Läser av vad användaren skriver in för namn/personnummer.
    //Kollar ifall personen är medlem och returnerar ett meddelande.
    public String isPersonMember(List<Member> memberList){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();
        String message = "Personen har aldrig varit medlem på gymmet.";

        //Om användaren skriver in hejdå stängs programmet av.
        if (input.equalsIgnoreCase("hejdå")){
            System.exit(0);
        }

        //Kontrollera ifall användar-input är samma som någon medlems namn eller personnummer med hjälp av en loop.
        //Isåfall anropas metoden membershipActive för att kontrollera ifall personens medlemskap är gammalt eller ej.
        for (Member member : memberList){
            if (member.getName().equalsIgnoreCase(input) || member.getPersonalIdentityNumber().equals(input)){
                message = membershipActive(member);
                return message;

            }
        }
        return message;
    }


    //Kontrollera ifall medlemmen har betalat sin avgift det senaste året.
    public String membershipActive(Member member){
        ReadAndWrite rw = new ReadAndWrite();

        LocalDate memberShipPaymentDate = member.getMembershipPaidDate();
        LocalDate oneYearAgo = LocalDate.now().minusYears(1);

        //Ifall medlemmens avgift är betald det senaste året så anropas metoden writeFile.
        //Returnera en lämplig text beroende på medlemmens betaldatum.
        if (memberShipPaymentDate.isAfter(oneYearAgo)){
            rw.writeFile(pathToWrite, member);
            return "Personens är en betalande medlem, personens närvaro har loggats till den personlige tränarens textfil";
        }
        return "Personen är en gammal medlem, årskortet har gått ut";
    }


}
