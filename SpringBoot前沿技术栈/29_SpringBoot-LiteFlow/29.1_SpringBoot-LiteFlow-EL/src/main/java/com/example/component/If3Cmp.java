package com.example.component;

import com.yomahub.liteflow.core.NodeBooleanComponent;
import org.springframework.stereotype.Component;

@Component("x3")
public class If3Cmp extends NodeBooleanComponent {
	@Override
	public boolean processBoolean() throws Exception {
	    // do your biz
		return true;
	}
}
