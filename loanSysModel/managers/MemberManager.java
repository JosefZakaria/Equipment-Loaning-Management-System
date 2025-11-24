package loanSysModel.managers;

import loanSysModel.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MemberManager {
    private final List<Member> members = new ArrayList<>();
    private final Random random = new Random();
    private int lastMemberId = 1;

    public void add(Member member) {
        members.add(member);
    }

    public Member get(int index) {
        if (index >= 0 && index < members.size()) {
            return members.get(index);
        }
        return null;
    }

    public int size() {
        return members.size();
    }

    public Member getRandomMember() {
        if (members.isEmpty()) return null;
        int index = random.nextInt(members.size());
        return members.get(index);
    }

    public void addTestMembers() {
        for (int i = 0; i < 25; i++) {
            add(new Member(lastMemberId, "Member " + lastMemberId));
            lastMemberId++;
        }
    }

    public String[] getMemberInfoStrings() {
        if (members.isEmpty()) {
            return new String[]{"No members found", " "};
        }

        String[] info = new String[members.size() + 2];
        info[0] = "Number of members: " + members.size();
        info[1] = "";

        for (int i = 0; i < members.size(); i++) {
            info[i + 2] = members.get(i).toString();
        }

        return info;
    }
}
