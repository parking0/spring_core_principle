package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService{      //AppConfig 덕분에 실행에만 집중함

    //private final MemberRepository memberRepository=new MemoryMemberRepository();    //<== AppConfig
    private final MemberRepository memberRepository;  //DIP 지키는중. 얘 입장에서는 memberRepository에 대해서 하나도 모름
        /*  클라이언트인 memberServiceImpl 입장에서 보면 의존관계를 마치 외부에서 주입해주는 것 같다고 해서
                DI(Dependency Injection) 우리말로 의존관계 주입이라 한다.
        */

    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();     //DIP 위반
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();   //OCP 위반 - OrderServiceImpl도 바꿔야하기 때문
    //private DiscountPolicy discountPolicy;  //인터페이스에만 의존하도록 설계를 변경. 하지만 이대로 하면 NullPointException
                                            //이 문제를 해결하려면 누군가가 클라이언트인 OrderServiceImpl 에 DiscountPolicy 의 구현 객체를
                                            //대신 생성하고 주입해주어야 한다
    //private DiscountPolicy discountPolicy = new FixDiscountPolicy();    //관심사의 분리
    private final DiscountPolicy discountPolicy;       // AppConfig
                                                        //DIP 지키는중

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {     //생성자 생성
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
         Member member = memberRepository.findById(memberId);
         int discountPrice = discountPolicy.discount(member, itemPrice);

         return new Order(memberId,itemName,itemPrice,discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }

}
