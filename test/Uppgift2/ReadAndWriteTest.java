package Uppgift2;

import org.junit.Test;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;


public class ReadAndWriteTest {
    ReadAndWrite rw = new ReadAndWrite();
    String pathRead = "src//Uppgift2//members.txt";
    String pathWrite = "src//Uppgift2//testWriteFile.txt";

    @Test
    public void readFileTCreateMemberListTest() {
        //Läs in textfilen och skapa en lista av members
        List<Member> membersList = new ArrayList<>(rw.readFileCreateMemberList(pathRead));

        //Kolla i listan att första namnet, personnumret och medlemskapsdatum är det som förväntas
        assertEquals("Alhambra Aromes", membersList.get(0).getName());
        assertEquals("7703021234", membersList.get(0).getPersonalIdentityNumber());
        assertEquals(LocalDate.of(2024,7,1), membersList.get(0).getMembershipPaidDate());
        //Kolla i listan att andra namnet, personnumret och medlemskapsdatum är det som förväntas
        assertEquals("Bear Belle", membersList.get(1).getName());
        assertEquals("8204021234", membersList.get(1).getPersonalIdentityNumber());
        assertEquals(LocalDate.of(2019,12,2), membersList.get(1).getMembershipPaidDate());
    }

    @Test
    public void writeFileTest() {

        //Skapa en medlem och en DateTimeFormatter
        Member m1 = new Member("9012241234", "Joachim Wiltman",LocalDate.of(2024, 10, 1));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd 'klockan' HH:mm:ss");

        //Testa att skriva medlemmen till en textfil
        rw.writeFile(pathWrite, m1);

        //Läs från filen och se ifall rätt text har skrivits till den
        try(BufferedReader br = new BufferedReader(new FileReader(pathWrite))){

            //Läs första raden och kontrollera att den är samma som dagens datum.
            String s = br.readLine();
            assertEquals(s, (LocalDateTime.now().format(formatter)));

            //Läs andra raden och kontrollera att den är personnumret och namn
            s = br.readLine();
            assertEquals(s, "9012241234, Joachim Wiltman");

        } catch (FileNotFoundException e) {
            System.out.println("Filen hittades inte");
        } catch (IOException e) {
            System.out.println("Något gick fel");
        }
    }
}
