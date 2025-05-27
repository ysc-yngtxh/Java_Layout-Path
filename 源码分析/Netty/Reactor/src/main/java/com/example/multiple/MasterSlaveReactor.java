// package com.example.multiple;
//
// import com.example.single.Reactor;
// import java.io.IOException;
//
// public class MasterSlaveReactor {
//
//     private final Reactor mainReactor;
//     private final Reactor[] subReactors;
//     private final int subReactorCount;
//
//     public MasterSlaveReactor(int port, int subReactorCount) throws IOException {
//         this.subReactorCount = subReactorCount;
//         mainReactor = new Reactor(port, true); // 只处理accept
//         subReactors = new Reactor[subReactorCount];
//         for (int i = 0; i < subReactorCount; i++) {
//             subReactors[i] = new Reactor(false); // 处理read/write
//         }
//     }
//
//     public void start() {
//         new Thread(mainReactor).start();
//         for (Reactor subReactor : subReactors) {
//             new Thread(subReactor).start();
//         }
//     }
//
//     public Reactor nextSubReactor() {
//         // 简单的轮询分配
//         return subReactors[(int)(System.currentTimeMillis() % subReactorCount)];
//     }
// }
