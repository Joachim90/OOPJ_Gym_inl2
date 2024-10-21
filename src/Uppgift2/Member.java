package Uppgift2;

import java.time.LocalDate;

public class Member {
    private String name;
    private String personalIdentityNumber;
    private LocalDate membershipPaidDate;


    //Konstruktor och getters
    public Member(String personalIdentityNumber,String name, LocalDate membershipPaidDate) {
        this.name = name;
        this.personalIdentityNumber = personalIdentityNumber;
        this.membershipPaidDate = membershipPaidDate;
    }
    public String getName() {
        return name;
    }
    public String getPersonalIdentityNumber() {
        return personalIdentityNumber;
    }
    public LocalDate getMembershipPaidDate() {
        return membershipPaidDate;
    }
}
