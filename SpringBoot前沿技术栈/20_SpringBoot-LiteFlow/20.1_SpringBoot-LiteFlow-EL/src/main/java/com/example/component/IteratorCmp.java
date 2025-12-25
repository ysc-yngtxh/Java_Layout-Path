package com.example.component;

import com.yomahub.liteflow.core.NodeIteratorComponent;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * @author 游家纨绔
 * @dateTime 2024-10-01 15:30
 * @apiNote TODO
 */
@Component("iterator")
// @LiteflowComponent继承自@Component，如果你在spring体系的环境里，组件里可以任意注入spring的bean进行使用。
public class IteratorCmp extends NodeIteratorComponent {

	@Override
	public Iterator<?> processIterator() throws Exception {
		System.out.println("IteratorCmp executed!");
		List<String> list = Arrays.asList("jack", "mary", "tom");
		return list.iterator();
	}
}
