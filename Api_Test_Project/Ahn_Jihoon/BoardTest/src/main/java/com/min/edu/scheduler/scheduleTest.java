package com.min.edu.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class scheduleTest {
	
	@Scheduled(fixedDelay = 5000)
	public void init() { // 5초마다 실행
		System.out.println("---------------------------- TEST ----------------------------");
	}
	
	@Scheduled(cron = "0 00 0 * * ?") // 매일 22시 수행
	public void download() {
		System.out.println("---------------------------- 다운로드 테스트 ----------------------------");
	}
}