package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    OrderService orderService;
    MemberService memberService;

    @BeforeEach
    void beforeEach(){
        AppConfig appConfig = new AppConfig();
        orderService = appConfig.orderService();
        memberService = appConfig.memberService();
    }

    @Test
    void order(){
        //given
        Member member = new Member(1L, "hong", Grade.VIP);

        //when
        memberService.join(member);
        Order order = orderService.createOrder(1L, "itemA", 10000);
        int calcPrice = order.calculatePrice();

        //then
        Assertions.assertThat(calcPrice).isEqualTo(9000);
    }

}


//OrderService orderService = new OrderServiceImpl();
//MemberService memberService = new MemberServiceImpl();
//
//Long memberId = 1L;
//Member member = new Member(memberId, "hong", Grade.VIP);
//        memberService.join(member);
//
//Order order = orderService.createOrder(memberId, "itemA", 10000);
//        System.out.println(order);
//        System.out.println(order.calculatePrice());