package com.example.component;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;

/**
 * @author 游家纨绔
 * @dateTime 2024-10-01 15:30
 * @apiNote TODO
 */
@LiteflowComponent("c")
// @LiteflowComponent继承自@Component，如果你在spring体系的环境里，组件里可以任意注入spring的bean进行使用。
public class CCmp extends NodeComponent {

	@Override
	public void process() {
		System.out.println("CCmp executed!");
	}
}
