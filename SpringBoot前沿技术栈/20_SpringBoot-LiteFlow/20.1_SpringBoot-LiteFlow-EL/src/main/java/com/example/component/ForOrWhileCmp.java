package com.example.component;

import com.yomahub.liteflow.core.NodeForComponent;
import org.springframework.stereotype.Component;

/**
 * @author 游家纨绔
 * @dateTime 2024-10-01 15:30
 * @apiNote TODO
 */
@Component("f")
// @LiteflowComponent继承自@Component，如果你在spring体系的环境里，组件里可以任意注入spring的bean进行使用。
public class ForOrWhileCmp extends NodeForComponent {

	@Override
	public int processFor() throws Exception {
		System.out.println("ForOrWhileCmp executed!");
		return 2;
	}
}
