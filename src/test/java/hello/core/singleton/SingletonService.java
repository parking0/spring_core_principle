package hello.core.singleton;

public class SingletonService {

    // static 영역에 객체를 1개만 생성
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        // 객체 인스턴스가 필요하면 오직 getInstance() 메서드를 통해서만 조회할 수 있다
        return instance;
    }

    private SingletonService(){
        //생성자를 private으로 선언해서 외부에서 new를 이용한 객체 생성을 막음
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
