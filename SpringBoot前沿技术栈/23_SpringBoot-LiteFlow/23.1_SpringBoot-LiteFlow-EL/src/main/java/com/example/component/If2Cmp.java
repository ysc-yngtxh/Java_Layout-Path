package com.example.component;

import com.yomahub.liteflow.core.NodeBooleanComponent;
import org.springframework.stereotype.Component;

@Component("x2")
public class If2Cmp extends NodeBooleanComponent {
	@Override
	public boolean processBoolean() throws Exception {
	    // do your biz
		return true;
	}
}
