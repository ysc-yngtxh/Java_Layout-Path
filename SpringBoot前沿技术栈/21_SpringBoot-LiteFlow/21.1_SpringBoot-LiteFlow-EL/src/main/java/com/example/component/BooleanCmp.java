package com.example.component;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeBooleanComponent;

/**
 * @author 游家纨绔
 * @dateTime 2024-10-01 19:40
 * @apiNote TODO
 */
@LiteflowComponent("y")
public class BooleanCmp extends NodeBooleanComponent {

	@Override
	public boolean processBoolean() throws Exception {
		return true;
	}
}
