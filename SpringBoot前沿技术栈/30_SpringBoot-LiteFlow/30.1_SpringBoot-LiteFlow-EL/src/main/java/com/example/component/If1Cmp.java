package com.example.component;

import com.yomahub.liteflow.core.NodeBooleanComponent;
import org.springframework.stereotype.Component;

@Component("x")
public class If1Cmp extends NodeBooleanComponent {
	@Override
	public boolean processBoolean() throws Exception {
	    // do your biz
		return false;
	}
}
