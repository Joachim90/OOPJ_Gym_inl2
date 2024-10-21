package Uppgift2;

import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class MemberUtilTest {

    MemberUtil mu = new MemberUtil();


    //Testa så att metoderna returnerar det som förväntas utifrån vad som skickas in i metoden
    @Test
    public void readPersonalIdentityNumberTest() {
        String input = "7703021234, Alhambra Aromes";
        assertEquals("7703021234", mu.readPersonalIdentityNumber(input));
    }
    @Test
    public void readNameTest() {
        String input = "7703021234, Alhambra Aromes";
        assertEquals("Alhambra Aromes", mu.readName(input));
    }
    @Test
    public void readDateOfMembershipTest() {
        String input = "2024-07-01";
        assertEquals(LocalDate.of(2024,7,1), mu.readDateOfMembership(input));
    }



    @Test
    public void isPersonMemberTest() {

        //Skapa en lista av members, lägg till en betalande medlem och en gammal medlem
        List<Member> memberList = new ArrayList<>();
        Member m1 = new Member("Pelle Svensson", "8810101234", LocalDate.of(2024,10,10));
        memberList.add(m1);
        Member m2 = new Member("Kalle Karlsson", "9511051234", LocalDate.of(2022,12,24));
        memberList.add(m2);

        //Se till att rätt text returneras utifrån vad användaren skriver in i Scannern
        //Medlemmar
        String member1 = "Pelle Svensson"; // Detta simulerar vad användaren skriver in i Scannern
        System.setIn(new ByteArrayInputStream(member1.getBytes()));
        assertEquals( "Personens är en betalande medlem, personens närvaro har loggats till den personlige tränarens textfil",mu.isPersonMember(memberList));

        String member2 = "Kalle Karlsson"; // Detta simulerar vad användaren skriver in i Scannern
        System.setIn(new ByteArrayInputStream(member2.getBytes()));
        assertEquals( "Personen är en gammal medlem, årskortet har gått ut",mu.isPersonMember(memberList));

        String member3 = "8810101234"; // Detta simulerar vad användaren skriver in i Scannern
        System.setIn(new ByteArrayInputStream(member3.getBytes()));
        assertEquals( "Personens är en betalande medlem, personens närvaro har loggats till den personlige tränarens textfil",mu.isPersonMember(memberList));

        String member4 = "9511051234"; // Detta simulerar vad användaren skriver in i Scannern
        System.setIn(new ByteArrayInputStream(member4.getBytes()));
        assertEquals( "Personen är en gammal medlem, årskortet har gått ut",mu.isPersonMember(memberList));

        //Ej medlemmar
        String notMember1 = "Olle Olsson"; // Detta simulerar vad användaren skriver in i Scannern
        System.setIn(new ByteArrayInputStream(notMember1.getBytes()));
        assertEquals( "Personen har aldrig varit medlem på gymmet.",mu.isPersonMember(memberList));

        String notMember2 = "9102021234"; // Detta simulerar vad användaren skriver in i Scannern
        System.setIn(new ByteArrayInputStream(notMember2.getBytes()));
        assertEquals( "Personen har aldrig varit medlem på gymmet.",mu.isPersonMember(memberList));

    }


    @Test
    public void membershipActiveTest(){
        List<Member> memberList = new ArrayList<>();
        Member m1 = new Member("Pelle Svensson", "8810101234", LocalDate.of(2024,10,10));
        memberList.add(m1);
        Member m2 = new Member("Kalle Svensson", "9511051234", LocalDate.of(2022,12,24));
        memberList.add(m2);
        String activeMemberReturnText = "Personens är en betalande medlem, personens närvaro har loggats till den personlige tränarens textfil";
        String inactiveMemberReturnText = "Personen är en gammal medlem, årskortet har gått ut";
        assertEquals(activeMemberReturnText, mu.membershipActive(m1));
        assertEquals(inactiveMemberReturnText, mu.membershipActive(m2));
        assertNotEquals(inactiveMemberReturnText, mu.membershipActive(m1));
    }


}
