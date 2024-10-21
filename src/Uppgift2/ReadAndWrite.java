package Uppgift2;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReadAndWrite {

    MemberUtil mu = new MemberUtil();

    public List<Member> readFileCreateMemberList(String path) {

        List<Member> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while (br.ready()) {
                String personalInfo = br.readLine();
                String membershipPaid = br.readLine();

                String personalIdentityNumber = mu.readPersonalIdentityNumber(personalInfo);
                String name = mu.readName(personalInfo);

                LocalDate dateOfMembership = mu.readDateOfMembership(membershipPaid);

                Member member = new Member(personalIdentityNumber, name,dateOfMembership);
                list.add(member);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void writeFile(String path, Member member) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd 'klockan' HH:mm:ss");

        //Skriv Datum och tid, samt personnummer och namn till PTns textfil. append = true
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            bw.write(LocalDateTime.now().format(formatter) + "\n" + member.getPersonalIdentityNumber() + ", " + member.getName() + "\n\n");

        }
        catch (IOException e) {
            System.out.println("Kunde inte skriva till filen.");
            e.printStackTrace();
        }
    }

}
