package org.example;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class TestReactor {

    @Test
    public void testMonoAndFlux() throws InterruptedException {
        Flux<Integer> fluxJust = Flux.just(1, 2, 3, 4, 5);

        fluxJust.subscribe(e1 -> System.out.println("e1 = " + e1));
        fluxJust.subscribe(e2 -> System.out.println("e2 = " + e2));

        // 每秒产生一个从 0 开始的递增数字
        Flux<Long> flux = Flux.interval(Duration.ofMillis(500));
        flux.subscribe(System.out::println);

        Thread.sleep(3000);

        Mono<Integer> monoJust = Mono.just(1);
    }

    @Test
    public void testDoOn() throws InterruptedException {
        Flux<Integer> flux = Flux.range(0, 10)
                .delayElements(Duration.ofMillis(300))  // 整流，无论什么时候输入的流都每隔一段时间发送 1 个
                .doOnComplete(() -> System.out.println("流已结束"))
                .doOnCancel(() -> System.out.println("流已取消"))
                .doOnError((err) -> System.out.println("流出错 " + err.getMessage()))
                .doOnNext((e) -> System.out.println("doOnNext " + e));

        flux.subscribe(System.out::println);
        Thread.sleep(4000);

        Flux<Integer> range = Flux.range(1, 9);
    }

}
