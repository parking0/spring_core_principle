package hello.core.member;

public class MemberServiceImpl implements MemberService {

    //MemberServiceImpl가 memberRepository, MemoryMemberRepository를 모두 의존중. --DIP 위반
    //private final MemberRepository memberRepository = new MemoryMemberRepository();       //AppConfig
    private final MemberRepository memberRepository;            //스프링 컨테이너가 관리하지 X
    public MemberServiceImpl(MemberRepository memberRepository){        //생성자 생성 <= AppConfig
        this.memberRepository=memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {

        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
