package spring.advanced.app.v4;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring.advanced.trace.TraceStatus;
import spring.advanced.trace.logtrace.LogTrace;
import spring.advanced.trace.template.AbstractTemplate;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {

    private final LogTrace trace;

    public void save(String itemId) {

        AbstractTemplate<String> template = new AbstractTemplate<>(trace) {
            @Override
            protected String call() {
                if (itemId.equals("ex")) {
                    throw new IllegalStateException("예외 발생");
                }
                sleep(1000);
                return "ok";
            }
        };

        template.execute("OOrderRepository.save()");
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
