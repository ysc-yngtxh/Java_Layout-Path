package com.example.component;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;

/**
 * @author 游家纨绔
 * @dateTime 2024-10-01 19:37
 * @apiNote TODO
 */
@LiteflowComponent("default")
public class DefaultCmp extends NodeComponent {

    @Override
    public void process() {
        System.out.println("DefaultCmp executed!");
    }
}
