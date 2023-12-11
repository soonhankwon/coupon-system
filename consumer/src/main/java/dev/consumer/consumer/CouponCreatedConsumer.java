package dev.consumer.consumer;

import dev.consumer.domain.Coupon;
import dev.consumer.domain.FailedEvent;
import dev.consumer.repository.CouponRepository;
import dev.consumer.repository.FaildEventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CouponCreatedConsumer {

    private final CouponRepository couponRepository;
    private final FaildEventRepository faildEventRepository;
    private final Logger logger = LoggerFactory.getLogger(CouponCreatedConsumer.class);

    public CouponCreatedConsumer(final CouponRepository couponRepository,
                                 final FaildEventRepository faildEventRepository) {
        this.couponRepository = couponRepository;
        this.faildEventRepository = faildEventRepository;
    }

    @KafkaListener(topics = "coupon_create", groupId = "group_1")
    public void listener(Long userId) {
        try {
            Coupon coupon = new Coupon(userId);
            couponRepository.save(coupon);
        } catch (Exception e) {
            logger.error("failed to create coupon::" + userId);
            faildEventRepository.save(new FailedEvent(userId));
        }
    }
}
