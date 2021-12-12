package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration          //설정 정보
public class AppConfig {            //애플리케이션의 전체 동작 방식을 구성

    //AppConfig  --  역할을 세우고 그 안에 구현을 넣어서 => 깔끔하게 만듦 & 중복 제거
    //AppCnfig으로 OrderSerrviceImple과 같은 사용 영역의 어떤 코드도 변경할 필요X
    @Bean               // Spring Container에 등록시키기
    public MemberService memberService(){
        //AppConfig는 생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해서 주입(연결)해준다.
        /*appConfig 객체는 memoryMemberRepository 객체를 생성하고,
         그 참조값을 memberServiceImpl을 생성하면서 생성자로 전달한다.*/
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {   //AppConfig 리팩터링
        return new MemoryMemberRepository();                //ctrl+alt+m
    }

    @Bean
    public OrderService orderService(){
        //AppConfig는 생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해서 주입(연결)해준다.
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {     //AppConfig 리팩터링

        //할인정책을 변경하고 싶을 때 구성 영역만 영향을 받고, 사용 영역은 전혀 영향을 받지 않는다.
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
