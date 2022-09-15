package spring.aop.order.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut("execution(* spring.aop.order..*(..))")
    public void allOrder(){}   // pointcut signature

    @Pointcut("execution(* *..*Service.*(..))")
    public void allService(){}

    // 포인트컷을 조합하여 하나의 포인트컷을 만들 수 있다.
    @Pointcut("allOrder() && allService()")
    public void orderAndService(){}
}
