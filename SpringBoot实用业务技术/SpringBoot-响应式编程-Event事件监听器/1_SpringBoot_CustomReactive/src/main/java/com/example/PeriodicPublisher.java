package com.example;

import java.util.Random;
import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;
public class PeriodicPublisher {
    static class SimpleSubscriber implements Flow.Subscriber<Long> {
        private Flow.Subscription subscription;
        // Subscriber name
        private String name = "Unknown";
        // Maximum number of items to be processed by this subscriber
        private final long maxCount;
        // keep track of number of items processed
        private long counter;
        public SimpleSubscriber(String name, long maxCount) {
            this.name = name;
            this.maxCount = maxCount <= 0 ? 1 : maxCount;
        }
        public String getName() {
            return name;
        }
        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            this.subscription = subscription;
            System.out.printf("%s subscribed with max count %d.%n", name, maxCount);
            // Request all items in one go
            subscription.request(maxCount);
        }
        @Override
        public void onNext(Long item) {
            counter++;
            System.out.printf("%s onNext %d.%n", name, item);
            if (counter >= maxCount) {
                System.out.printf("Cancelling %s. Processed item count: %d.%n", name, counter);
                // Cancel the subscription
                subscription.cancel();
            }
        }
        @Override
        public void onError(Throwable t) {
            System.out.printf("An error occurred in %s: %s.%n", name, t.getMessage());
        }
        @Override
        public void onComplete() {
            System.out.printf("%s is complete.%n", name);
        }
    }
    final static int MAX_SLEEP_DURATION = 3;
    // Used to generate sleep time
    final static Random sleepTimeGenerator = new Random();
    public static void main(String[] args) {
        SubmissionPublisher<Long> pub = new SubmissionPublisher<>();
        // Create three subscribers
        SimpleSubscriber sub1 = new SimpleSubscriber("S1", 2);
        SimpleSubscriber sub2 = new SimpleSubscriber("S2", 5);
        SimpleSubscriber sub3 = new SimpleSubscriber("S3", 6);
        SimpleSubscriber sub4 = new SimpleSubscriber("S4", 10);
        // Subscriber to the publisher
        pub.subscribe(sub1);
        pub.subscribe(sub2);
        pub.subscribe(sub3);
        // Subscribe the 4th subscriber after 2 seconds
        subscribe(pub, sub4, 2);
        // Start publishing items
        Thread pubThread = publish(pub, 5);
        try {
            // Wait until the publisher is finished
            pubThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static Thread publish(SubmissionPublisher<Long> pub, long count) {
        Thread t = new Thread(() -> {
            for (long i = 1; i <= count; i++) {
                pub.submit(i);
                sleep(i);
            }
            // Close the publisher
            pub.close();
        });
        // Start the thread
        t.start();
        return t;
    }
    private static void sleep(Long item) {
        // Wait for 1 to 3 seconds
        int sleepTime = sleepTimeGenerator.nextInt(MAX_SLEEP_DURATION) + 1;
        try {
            System.out.printf("Published %d. Sleeping for %d sec.%n", item, sleepTime);
            TimeUnit.SECONDS.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private static void subscribe(SubmissionPublisher<Long> pub, Subscriber<Long> sub,
                                  long delaySeconds) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(delaySeconds);
                pub.subscribe(sub);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }            
        }).start();
    }
}