package co.za.geartronix.models;

public class MemberModel {

    private int memberType;

    public String getSimpleName() {
        String[] simpleNames = new String[]{"Silver member", "Gold member", "Platinum member"};
        return simpleNames[memberType];
    }


    public int getMemberType() {
        return memberType;
    }

    public void setMemberType(int memberType) {
        this.memberType = memberType;
    }
}
