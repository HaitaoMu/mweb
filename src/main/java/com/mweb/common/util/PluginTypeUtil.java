package com.mweb.common.util;

import com.mweb.common.constats.PluginType;

public class PluginTypeUtil
{
	public static PluginType parseType(String type)
	{
		for (PluginType item : PluginType.values())
		{
			if (item.toString().equals(type))
			{
				return item;
			}
		}
		return PluginType.UNKONW;
	}
}
