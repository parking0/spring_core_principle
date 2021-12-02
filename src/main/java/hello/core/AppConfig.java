package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {            //애플리케이션의 전체 동작 방식을 구성

    public MemberService memberService(){
        //AppConfig는 생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해서 주입(연결)해준다.
        /*appConfig 객체는 memoryMemberRepository 객체를 생성하고,
         그 참조값을 memberServiceImpl을 생성하면서 생성자로 전달한다.*/
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService(){
        //AppConfig는 생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해서 주입(연결)해준다.
        return new OrderServiceImpl(new MemoryMemberRepository(),new FixDiscountPolicy());
    }

}
