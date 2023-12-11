package dev.api.service;

import dev.api.producer.CouponCreateProducer;
import dev.api.respositroy.AppliedUserRepository;
import dev.api.respositroy.CouponCountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplyService {

    private final CouponCountRepository couponCountRepository;
    private final CouponCreateProducer couponCreateProducer;
    private final AppliedUserRepository appliedUserRepository;

    public void apply(Long userId) {
        Long apply = appliedUserRepository.add(userId);
        if (apply != 1) {
            return;
        }

        Long count = couponCountRepository.increment();
        if (count > 100) {
            return;
        }

        couponCreateProducer.create(userId);
    }
}
