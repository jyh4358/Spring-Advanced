package spring.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import spring.aop.internalcall.aop.CallLogAspect;

@Slf4j
@Import(CallLogAspect.class)
@SpringBootTest
class CallServiceV3Test {

    @Autowired
    CallServiceV3 callServiceV3;

    @Test
    void external() {
        // external() 메서드 내에서 내부 메서드를 호출하는 internal() 메서드는
        // AOP가 적용이 안된다.
        callServiceV3.external();
    }

}