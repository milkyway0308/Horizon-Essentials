package DataUtil;

import java.util.HashMap;

public class MetacodeInterpreter {
public String metaCodeInterpreting(String main){
	if(main.contains("{enable interpreting}")){
		main = main.replace("{enable interpreting}", "");
		HashMap<Integer,MetaIndex> index = new HashMap<Integer,MetaIndex>();
		char[] c = main.toCharArray();
		for(int  i = 0;i < c.length;i++)
		{
			if(c[i] == '{')
				index.put(i,MetaIndex.MetaStart);
			else if(c[i] == '}')
				index.put(i, MetaIndex.MetaEnd);
		}
		return main;
	}else{
		return main;
	}
}

}
