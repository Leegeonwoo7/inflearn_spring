package hello.core.member;

import hello.core.AppConfig;

public class MemberApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        //S AppConfig를 통해 memberService를 가져왔다
        MemberService memberService = appConfig.memberService();

        Member member = new Member(1L, "java", Grade.VIP);
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        System.out.println("findMember = " + findMember.getName());
        System.out.println("member = " + member.getName());
    }
}
